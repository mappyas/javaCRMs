import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 顧客情報を表すクラス
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId = 1;
    
    private int id;
    private String name;
    private String email;
    private String phone;
    private String company;
    private String address;
    private LocalDateTime registeredDate;
    private LocalDateTime lastContactDate;
    private String notes;
    
    // コンストラクタ
    public Customer(String name, String email, String phone, String company, String address) {
        this.id = nextId++;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.address = address;
        this.registeredDate = LocalDateTime.now();
        this.lastContactDate = LocalDateTime.now();
        this.notes = "";
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getCompany() {
        return company;
    }
    
    public String getAddress() {
        return address;
    }
    
    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }
    
    public LocalDateTime getLastContactDate() {
        return lastContactDate;
    }
    
    public String getNotes() {
        return notes;
    }
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setLastContactDate(LocalDateTime lastContactDate) {
        this.lastContactDate = lastContactDate;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public static void setNextId(int nextId) {
        Customer.nextId = nextId;
    }
    
    // 顧客情報を整形して表示
    public String toDetailString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("顧客ID: ").append(id).append("\n");
        sb.append("名前: ").append(name).append("\n");
        sb.append("メール: ").append(email).append("\n");
        sb.append("電話番号: ").append(phone).append("\n");
        sb.append("会社名: ").append(company).append("\n");
        sb.append("住所: ").append(address).append("\n");
        sb.append("登録日: ").append(registeredDate.format(formatter)).append("\n");
        sb.append("最終連絡日: ").append(lastContactDate.format(formatter)).append("\n");
        sb.append("メモ: ").append(notes).append("\n");
        sb.append("=========================================");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return String.format("[ID:%d] %s - %s (%s)", id, name, company, email);
    }
}

