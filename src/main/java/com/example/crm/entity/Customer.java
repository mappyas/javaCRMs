package com.example.crm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 顧客エンティティクラス
 */
@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "名前は必須です")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "メールアドレスは必須です")
    @Email(message = "有効なメールアドレスを入力してください")
    @Column(nullable = false)
    private String email;
    
    private String phone;
    
    private String company;
    
    private String address;
    
    @Column(name = "registered_date")
    private LocalDateTime registeredDate;
    
    @Column(name = "last_contact_date")
    private LocalDateTime lastContactDate;
    
    @Column(length = 1000)
    private String notes;
    
    // デフォルトコンストラクタ
    public Customer() {
        this.registeredDate = LocalDateTime.now();
        this.lastContactDate = LocalDateTime.now();
        this.notes = "";
    }
    
    // コンストラクタ
    public Customer(String name, String email, String phone, String company, String address) {
        this();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.address = address;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }
    
    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }
    
    public LocalDateTime getLastContactDate() {
        return lastContactDate;
    }
    
    public void setLastContactDate(LocalDateTime lastContactDate) {
        this.lastContactDate = lastContactDate;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    @PrePersist
    protected void onCreate() {
        if (registeredDate == null) {
            registeredDate = LocalDateTime.now();
        }
        if (lastContactDate == null) {
            lastContactDate = LocalDateTime.now();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        lastContactDate = LocalDateTime.now();
    }
}

