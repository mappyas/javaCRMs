package package;

import java.util.List;
import java.util.Scanner;

/**
 * CRMシステムのメインクラス
 */
public class CRMSystem {
    private CustomerManager manager;
    private Scanner scanner;
    
    public CRMSystem() {
        manager = new CustomerManager();
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        CRMSystem crm = new CRMSystem();
        crm.run();
    }
    
    /**
     * メインループ
     */
    public void run() {
        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║   CRM 顧客管理システム v1.0      ║");
        System.out.println("╚═══════════════════════════════════╝");
        
        boolean running = true;
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    addNewCustomer();
                    break;
                case "2":
                    viewAllCustomers();
                    break;
                case "3":
                    viewCustomerDetail();
                    break;
                case "4":
                    updateCustomer();
                    break;
                case "5":
                    deleteCustomer();
                    break;
                case "6":
                    searchCustomers();
                    break;
                case "7":
                    manager.showStatistics();
                    break;
                case "0":
                    running = false;
                    System.out.println("\nシステムを終了します。ありがとうございました！");
                    break;
                default:
                    System.out.println("✗ 無効な選択です。もう一度お試しください。");
            }
        }
        
        scanner.close();
    }
    
    /**
     * メニュー表示
     */
    private void showMenu() {
        System.out.println("\n┌─────────────────────────────────┐");
        System.out.println("│        メインメニュー           │");
        System.out.println("├─────────────────────────────────┤");
        System.out.println("│ 1. 新規顧客登録                 │");
        System.out.println("│ 2. 全顧客一覧表示               │");
        System.out.println("│ 3. 顧客詳細表示                 │");
        System.out.println("│ 4. 顧客情報更新                 │");
        System.out.println("│ 5. 顧客削除                     │");
        System.out.println("│ 6. 顧客検索                     │");
        System.out.println("│ 7. 統計情報表示                 │");
        System.out.println("│ 0. 終了                         │");
        System.out.println("└─────────────────────────────────┘");
        System.out.print("選択してください: ");
    }
    
    /**
     * 新規顧客登録
     */
    private void addNewCustomer() {
        System.out.println("\n========== 新規顧客登録 ==========");
        
        System.out.print("名前: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("メールアドレス: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("電話番号: ");
        String phone = scanner.nextLine().trim();
        
        System.out.print("会社名: ");
        String company = scanner.nextLine().trim();
        
        System.out.print("住所: ");
        String address = scanner.nextLine().trim();
        
        if (name.isEmpty() || email.isEmpty()) {
            System.out.println("✗ 名前とメールアドレスは必須です");
            return;
        }
        
        manager.addCustomer(name, email, phone, company, address);
    }
    
    /**
     * 全顧客一覧表示
     */
    private void viewAllCustomers() {
        System.out.println("\n========== 全顧客一覧 ==========");
        List<Customer> customers = manager.getAllCustomers();
        
        if (customers.isEmpty()) {
            System.out.println("登録されている顧客はいません。");
            return;
        }
        
        System.out.println("総顧客数: " + customers.size() + "人\n");
        customers.forEach(System.out::println);
    }
    
    /**
     * 顧客詳細表示
     */
    private void viewCustomerDetail() {
        System.out.println("\n========== 顧客詳細表示 ==========");
        System.out.print("顧客ID: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Customer customer = manager.getCustomer(id);
            
            if (customer != null) {
                System.out.println("\n" + customer.toDetailString());
            } else {
                System.out.println("✗ 顧客が見つかりません");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ 無効なIDです");
        }
    }
    
    /**
     * 顧客情報更新
     */
    private void updateCustomer() {
        System.out.println("\n========== 顧客情報更新 ==========");
        System.out.print("更新する顧客ID: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Customer customer = manager.getCustomer(id);
            
            if (customer == null) {
                System.out.println("✗ 顧客が見つかりません");
                return;
            }
            
            System.out.println("\n現在の情報:");
            System.out.println(customer.toDetailString());
            System.out.println("\n新しい情報を入力してください（変更しない場合は空欄でEnter）:\n");
            
            System.out.print("名前 [" + customer.getName() + "]: ");
            String name = scanner.nextLine().trim();
            
            System.out.print("メールアドレス [" + customer.getEmail() + "]: ");
            String email = scanner.nextLine().trim();
            
            System.out.print("電話番号 [" + customer.getPhone() + "]: ");
            String phone = scanner.nextLine().trim();
            
            System.out.print("会社名 [" + customer.getCompany() + "]: ");
            String company = scanner.nextLine().trim();
            
            System.out.print("住所 [" + customer.getAddress() + "]: ");
            String address = scanner.nextLine().trim();
            
            System.out.print("メモ [" + customer.getNotes() + "]: ");
            String notes = scanner.nextLine();
            
            manager.updateCustomer(id, name, email, phone, company, address, notes);
        } catch (NumberFormatException e) {
            System.out.println("✗ 無効なIDです");
        }
    }
    
    /**
     * 顧客削除
     */
    private void deleteCustomer() {
        System.out.println("\n========== 顧客削除 ==========");
        System.out.print("削除する顧客ID: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Customer customer = manager.getCustomer(id);
            
            if (customer != null) {
                System.out.println("\n以下の顧客を削除しますか？");
                System.out.println(customer);
                System.out.print("削除する場合は 'yes' を入力: ");
                String confirm = scanner.nextLine().trim();
                
                if (confirm.equalsIgnoreCase("yes")) {
                    manager.deleteCustomer(id);
                } else {
                    System.out.println("✗ 削除をキャンセルしました");
                }
            } else {
                System.out.println("✗ 顧客が見つかりません");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ 無効なIDです");
        }
    }
    
    /**
     * 顧客検索
     */
    private void searchCustomers() {
        System.out.println("\n========== 顧客検索 ==========");
        System.out.println("1. 全項目検索");
        System.out.println("2. 名前で検索");
        System.out.println("3. 会社名で検索");
        System.out.println("4. メールアドレスで検索");
        System.out.print("選択: ");
        
        String choice = scanner.nextLine().trim();
        System.out.print("検索キーワード: ");
        String keyword = scanner.nextLine().trim();
        
        if (keyword.isEmpty()) {
            System.out.println("✗ キーワードを入力してください");
            return;
        }
        
        List<Customer> results;
        switch (choice) {
            case "1":
                results = manager.searchAll(keyword);
                break;
            case "2":
                results = manager.searchByName(keyword);
                break;
            case "3":
                results = manager.searchByCompany(keyword);
                break;
            case "4":
                results = manager.searchByEmail(keyword);
                break;
            default:
                System.out.println("✗ 無効な選択です");
                return;
        }
        
        System.out.println("\n検索結果: " + results.size() + "件");
        if (!results.isEmpty()) {
            results.forEach(System.out::println);
        } else {
            System.out.println("該当する顧客が見つかりませんでした。");
        }
    }
}

