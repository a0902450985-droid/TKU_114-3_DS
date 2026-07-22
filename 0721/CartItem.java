public class CartItem {
    private String code;     // 商品代碼
    private String name;     // 商品名稱
    private int price;       // 單價
    private int quantity;    // 數量

    public CartItem(String code, String name, int price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = Math.max(price, 0); // 確保單價不為負
        this.quantity = Math.max(quantity, 1); // 新增時數量至少為 1
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // 增加數量（用於重複加入商品時）
    public void addQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
    }

    // 修改數量（數量 <= 0 時不接收更新）
    public boolean setQuantity(int quantity) {
        if (quantity <= 0) {
            return false; // 不接受 <= 0 的更新
        }
        this.quantity = quantity;
        return true;
    }

    // 計算該商品小計 (單價 * 數量)
    public int getSubtotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("代碼: %-6s | 名稱: %-12s | 單價: %5d | 數量: %3d | 小計: %6d",
                code, name, price, quantity, getSubtotal());
    }
}
