public class Contact {
    private String code;     // 代碼
    private String name;     // 姓名
    private String phone;    // 電話
    private String email;    // 電子郵件

    public Contact(String code, String name, String phone, String email) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("代碼: %-6s | 姓名: %-8s | 電話: %-12s | Email: %s", 
                code, name, phone, email);
    }
}