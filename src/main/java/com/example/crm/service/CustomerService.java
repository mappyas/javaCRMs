package com.example.crm.service;

import com.example.crm.entity.Customer;
import com.example.crm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 顧客サービスクラス
 */
@Service
@Transactional
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    /**
     * 全顧客を取得
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    /**
     * IDで顧客を取得
     */
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    
    /**
     * 顧客を保存（新規登録・更新）
     */
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    
    /**
     * 顧客を削除
     */
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    
    /**
     * 名前で検索
     */
    public List<Customer> searchByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }
    
    /**
     * 会社名で検索
     */
    public List<Customer> searchByCompany(String company) {
        return customerRepository.findByCompanyContainingIgnoreCase(company);
    }
    
    /**
     * メールアドレスで検索
     */
    public List<Customer> searchByEmail(String email) {
        return customerRepository.findByEmailContainingIgnoreCase(email);
    }
    
    /**
     * 全項目で検索
     */
    public List<Customer> searchAll(String keyword) {
        return customerRepository.searchAll(keyword);
    }
    
    /**
     * 顧客数を取得
     */
    public long getCustomerCount() {
        return customerRepository.count();
    }
    
    /**
     * 会社別の顧客数を取得
     */
    public List<Object[]> getCustomerCountByCompany() {
        return customerRepository.countByCompany();
    }
    
    /**
     * 最終連絡日を更新
     */
    public void updateLastContactDate(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setLastContactDate(LocalDateTime.now());
            customerRepository.save(customer);
        }
    }
}

