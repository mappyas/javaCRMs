package com.example.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CRM アプリケーション メインクラス
 */
@SpringBootApplication
public class CrmApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
        
        System.out.println("\n========================================");
        System.out.println("  CRM システムが起動しました！");
        System.out.println("  http://localhost:8080/customers");
        System.out.println("========================================\n");
    }
}

