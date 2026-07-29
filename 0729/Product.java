public class Product {
    private String id;       // 商品編號
    private String name;     // 商品名稱
    private int price;       // 價格
    private int stock;       // 庫存

    public Product(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return String.format("編號: %-6s | 名稱: %-10s | 價格: %-6d | 庫存: %-4d", 
                            id, name, price, stock);
    }
}
