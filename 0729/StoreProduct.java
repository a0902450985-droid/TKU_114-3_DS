public class StoreProduct {
    private String id;
    private String name;
    private int price;
    private int stock;

    public StoreProduct(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Copy Constructor：用於複製商品物件，確保陣列複製時不會改到原始物件 reference
    public StoreProduct(StoreProduct other) {
        this.id = other.id;
        this.name = other.name;
        this.price = other.price;
        this.stock = other.stock;
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
        return String.format("編號: %-5s | 名稱: %-12s | 價格: %5d 元 | 庫存: %3d 件", id, name, price, stock);
    }
}
