public class Registration {
    private String id;        // 報名編號 (例如: R001)
    private String name;      // 參加者姓名
    private String phone;     // 聯絡電話
    private boolean isWaitlist; // 是否為候補狀態

    public Registration(String id, String name, String phone, boolean isWaitlist) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.isWaitlist = isWaitlist;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isWaitlist() {
        return isWaitlist;
    }

    public void setWaitlist(boolean waitlist) {
        isWaitlist = waitlist;
    }

    @Override
    public String toString() {
        String status = isWaitlist ? "【候補】" : "【正取】";
        return String.format("報名資料[編號: %-6s | 姓名: %-6s | 電話: %-11s | 狀態: %s]",
                id, name, phone, status);
    }
}