package com.example.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ホームコントローラー
 */
@Controller
public class HomeController {
    
    /**
     * ルートページへのアクセスを顧客一覧にリダイレクト
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/customers";
    }
}

