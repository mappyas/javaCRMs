import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 顧客管理クラス - CRUD操作を提供
 */
public class CustomerManager {
    private Map<Integer, Customer> customers;
    private static final String DATA_FILE = "customers.dat";
    
    public CustomerManager() {
        customers = new HashMap<>();
        loadCustomers();
    }
    
    /**
     * 顧客を追加
     */
    public void addCustomer(String name, String email, String phone, String company, String address) {
        Customer customer = new Customer(name, email, phone, company, address);
        customers.put(customer.getId(), customer);
        saveCustomers();
        System.out.println("✓ 顧客を登録しました: " + customer.getName());
    }
    
    /**
     * 顧客を取得
     */
    public Customer getCustomer(int id) {
        return customers.get(id);
    }
    
    /**
     * 全顧客を取得
     */
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }
    
    /**
     * 顧客情報を更新
     */
    public boolean updateCustomer(int id, String name, String email, String phone, 
                                   String company, String address, String notes) {
        Customer customer = customers.get(id);
        if (customer != null) {
            if (name != null && !name.isEmpty()) customer.setName(name);
            if (email != null && !email.isEmpty()) customer.setEmail(email);
            if (phone != null && !phone.isEmpty()) customer.setPhone(phone);
            if (company != null && !company.isEmpty()) customer.setCompany(company);
            if (address != null && !address.isEmpty()) customer.setAddress(address);
            if (notes != null) customer.setNotes(notes);
            customer.setLastContactDate(LocalDateTime.now());
            saveCustomers();
            System.out.println("✓ 顧客情報を更新しました");
            return true;
        }
        System.out.println("✗ 顧客が見つかりません");
        return false;
    }
    
    /**
     * 顧客を削除
     */
    public boolean deleteCustomer(int id) {
        Customer customer = customers.remove(id);
        if (customer != null) {
            saveCustomers();
            System.out.println("✓ 顧客を削除しました: " + customer.getName());
            return true;
        }
        System.out.println("✗ 顧客が見つかりません");
        return false;
    }
    
    /**
     * 名前で検索
     */
    public List<Customer> searchByName(String keyword) {
        return customers.values().stream()
            .filter(c -> c.getName().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    /**
     * 会社名で検索
     */
    public List<Customer> searchByCompany(String keyword) {
        return customers.values().stream()
            .filter(c -> c.getCompany().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    /**
     * メールアドレスで検索
     */
    public List<Customer> searchByEmail(String keyword) {
        return customers.values().stream()
            .filter(c -> c.getEmail().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    /**
     * 全ての条件で検索
     */
    public List<Customer> searchAll(String keyword) {
        return customers.values().stream()
            .filter(c -> c.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        c.getCompany().toLowerCase().contains(keyword.toLowerCase()) ||
                        c.getEmail().toLowerCase().contains(keyword.toLowerCase()) ||
                        c.getPhone().contains(keyword))
            .collect(Collectors.toList());
    }
    
    /**
     * 顧客数を取得
     */
    public int getCustomerCount() {
        return customers.size();
    }
    
    /**
     * 顧客データを保存
     */
    private void saveCustomers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(customers);
            // 次のIDも保存
            int maxId = customers.values().stream()
                .mapToInt(Customer::getId)
                .max()
                .orElse(0);
            Customer.setNextId(maxId + 1);
        } catch (IOException e) {
            System.err.println("データ保存エラー: " + e.getMessage());
        }
    }
    
    /**
     * 顧客データを読み込み
     */
    @SuppressWarnings("unchecked")
    private void loadCustomers() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                customers = (Map<Integer, Customer>) ois.readObject();
                // 次のIDを復元
                int maxId = customers.values().stream()
                    .mapToInt(Customer::getId)
                    .max()
                    .orElse(0);
                Customer.setNextId(maxId + 1);
                System.out.println("✓ " + customers.size() + "件の顧客データを読み込みました");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("データ読み込みエラー: " + e.getMessage());
                customers = new HashMap<>();
            }
        }
    }
    
    /**
     * 統計情報を表示
     */
    public void showStatistics() {
        System.out.println("\n========== 統計情報 ==========");
        System.out.println("総顧客数: " + customers.size());
        
        Map<String, Long> companyStats = customers.values().stream()
            .collect(Collectors.groupingBy(Customer::getCompany, Collectors.counting()));
        
        System.out.println("\n会社別顧客数:");
        companyStats.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .forEach(e -> System.out.println("  " + e.getKey() + ": " + e.getValue() + "人"));
        
        System.out.println("==============================\n");
    }
}

