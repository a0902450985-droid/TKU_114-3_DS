public class ProductSortPractice {

    public static void main(String[] args) {
        // 5. 測試資料至少 8 筆，且包含相同價格（驗證穩定排序 Stable Sort）
        Product[] products = {
            new Product("P001", "滑鼠", 500, 20),
            new Product("P002", "鍵盤", 1200, 15),
            new Product("P003", "耳機", 800, 10),
            new Product("P004", "螢幕", 4500, 5),
            new Product("P005", "滑鼠墊", 500, 50),   // 價格與 P001 相同
            new Product("P006", "喇叭", 1200, 8),    // 價格與 P002 相同
            new Product("P007", "Webcam", 800, 12),  // 價格與 P003 相同
            new Product("P008", "USB線", 150, 100)
        };

        System.out.println("=== 排序前商品列表 ===");
        printProducts(products);

        // 2. 使用 Insertion Sort 依價格升冪排序
        insertionSortByPrice(products);

        // 4. 排序後顯示所有完整欄位
        System.out.println("\n=== 依價格升冪排序後商品列表 ( Insertion Sort ) ===");
        printProducts(products);
    }

    /**
     * 針對 Product 物件陣列進行 Insertion Sort 升冪排序
     * 3. 價格相同時保持原本順序（嚴格使用 > 才移動，確保 Stability）
     */
    public static void insertionSortByPrice(Product[] products) {
        int n = products.length;

        for (int i = 1; i < n; i++) {
            Product key = products[i]; // 暫存要插入的 Product 物件
            int j = i - 1;

            // 關鍵點：必須是 products[j].getPrice() > key.getPrice()
            // 使用 '>' 而非 '>='，即可保證相同價格時「不發生交換右移」，維持原本相對順序
            while (j >= 0 && products[j].getPrice() > key.getPrice()) {
                products[j + 1] = products[j]; // 移動整個 Product 物件參照
                j--;
            }

            products[j + 1] = key; // 放入合適位置
        }
    }

    public static void printProducts(Product[] products) {
        for (Product p : products) {
            System.out.println(p);
        }
    }
}
