public class Equipment {
    private String id;          // 設備代碼
    private String name;        // 設備名稱
    private boolean isAvailable; // 是否可借用 (true: 可借用, false: 已借出)

    public Equipment(String id, String name) {
        this.id = id;
        this.name = name;
        this.isAvailable = true; // 新增設備預設為可借用
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        String statusStr = isAvailable ? "可借用" : "已借出";
        return String.format("代碼: %-6s | 名稱: %-15s | 狀態: %s", id, name, statusStr);
    }
}