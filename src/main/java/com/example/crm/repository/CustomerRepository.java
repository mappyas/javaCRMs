package com.example.crm.repository;

import com.example.crm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 顧客リポジトリインターフェース
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // 名前で検索（部分一致）
    List<Customer> findByNameContainingIgnoreCase(String name);
    
    // 会社名で検索（部分一致）
    List<Customer> findByCompanyContainingIgnoreCase(String company);
    
    // メールアドレスで検索（部分一致）
    List<Customer> findByEmailContainingIgnoreCase(String email);
    
    // 全項目検索
    @Query("SELECT c FROM Customer c WHERE " +
           "LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.company) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "c.phone LIKE CONCAT('%', :keyword, '%')")
    List<Customer> searchAll(@Param("keyword") String keyword);
    
    // 会社名でグループ化して件数を取得
    @Query("SELECT c.company, COUNT(c) FROM Customer c GROUP BY c.company ORDER BY COUNT(c) DESC")
    List<Object[]> countByCompany();
}

