package com.example.crm.controller;

import com.example.crm.entity.Customer;
import com.example.crm.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * 顧客コントローラー
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    /**
     * トップページ（顧客一覧）
     */
    @GetMapping({"", "/"})
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("totalCount", customers.size());
        return "customers/list";
    }
    
    /**
     * 顧客詳細
     */
    @GetMapping("/{id}")
    public String viewCustomer(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Customer> customerOpt = customerService.getCustomerById(id);
        if (customerOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "顧客が見つかりません");
            return "redirect:/customers";
        }
        model.addAttribute("customer", customerOpt.get());
        return "customers/detail";
    }
    
    /**
     * 新規登録フォーム表示
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/form";
    }
    
    /**
     * 新規登録処理
     */
    @PostMapping
    public String createCustomer(@Valid @ModelAttribute Customer customer, 
                                 BindingResult result, 
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "customers/form";
        }
        
        customerService.saveCustomer(customer);
        redirectAttributes.addFlashAttribute("successMessage", "顧客を登録しました");
        return "redirect:/customers";
    }
    
    /**
     * 編集フォーム表示
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Customer> customerOpt = customerService.getCustomerById(id);
        if (customerOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "顧客が見つかりません");
            return "redirect:/customers";
        }
        model.addAttribute("customer", customerOpt.get());
        return "customers/form";
    }
    
    /**
     * 更新処理
     */
    @PostMapping("/{id}")
    public String updateCustomer(@PathVariable Long id,
                                 @Valid @ModelAttribute Customer customer,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "customers/form";
        }
        
        customer.setId(id);
        customerService.saveCustomer(customer);
        redirectAttributes.addFlashAttribute("successMessage", "顧客情報を更新しました");
        return "redirect:/customers/" + id;
    }
    
    /**
     * 削除処理
     */
    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            customerService.deleteCustomer(id);
            redirectAttributes.addFlashAttribute("successMessage", "顧客を削除しました");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "削除に失敗しました");
        }
        return "redirect:/customers";
    }
    
    /**
     * 検索処理
     */
    @GetMapping("/search")
    public String searchCustomers(@RequestParam(required = false) String keyword,
                                  @RequestParam(required = false) String searchType,
                                  Model model) {
        List<Customer> customers;
        
        if (keyword == null || keyword.trim().isEmpty()) {
            customers = customerService.getAllCustomers();
        } else {
            switch (searchType != null ? searchType : "all") {
                case "name":
                    customers = customerService.searchByName(keyword);
                    break;
                case "company":
                    customers = customerService.searchByCompany(keyword);
                    break;
                case "email":
                    customers = customerService.searchByEmail(keyword);
                    break;
                default:
                    customers = customerService.searchAll(keyword);
            }
        }
        
        model.addAttribute("customers", customers);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchType", searchType);
        model.addAttribute("totalCount", customers.size());
        return "customers/list";
    }
    
    /**
     * 統計ページ
     */
    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        long totalCount = customerService.getCustomerCount();
        List<Object[]> companyStats = customerService.getCustomerCountByCompany();
        
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("companyStats", companyStats);
        return "customers/statistics";
    }
}

