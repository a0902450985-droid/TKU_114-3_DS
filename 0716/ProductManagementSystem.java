import java.util.Scanner;

public class ProductManagementSystem {
    // 1. 宣告長度為 10 的商品陣列
    private static Product[] products = new Product[10];
    private static int productCount = 0; // 記錄目前陣列中的商品數量
    private static Scanner scanner = new Scanner(System.in);

    // 用於紀錄第 9 項「操作摘要」的統計變數
    private static int totalOperations = 0;
    private static long totalSalesRevenue = 0; // 改用 long 配合 int 價格計算

    public static void main(String[] args) {
        // 初始化加入 5 項商品，保留 5 個空間
        initializeProducts();

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("請選擇操作 (1-9): ");
            String choice = scanner.nextLine().trim();
            System.out.println("----------------------------------------------");
            
            switch (choice) {
                case "1":
                    displayAllProducts();
                    break;
                case "2":
                    searchProductByName();
                    break;
                case "3":
                    addNewProduct();
                    break;
                case "4":
                    sellProduct();
                    break;
                case "5":
                    restockProduct();
                    break;
                case "6":
                    updateProductPrice();
                    break;
                case "7":
                    displayLowStockProducts();
                    break;
                case "8":
                    displayTotalInventoryValue();
                    break;
                case "9":
                    displaySummaryAndExit();
                    running = false;
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入！");
            }
            System.out.println("----------------------------------------------");
        }
    }

    // ==========================================
    // 主程式輔助方法 (符合至少建立 8 個的要求)
    // ==========================================

    // 輔助方法 1: 初始化預設商品 (配合你的 Product 建構子)
    private static void initializeProducts() {
        products[0] = new Product("iPhone 15", 29900, 15);
        products[1] = new Product("iPad Air", 19900, 3); // 測試低庫存 (stock < 10)
        products[2] = new Product("MacBook Pro", 54900, 12);
        products[3] = new Product("Apple Watch", 13500, 5); // 測試低庫存 (stock < 10)
        products[4] = new Product("AirPods Pro", 7490, 20);
        productCount = 5;
    }

    // 輔助方法 2: 顯示功能選單
    private static void showMenu() {
        System.out.println("\n===== 商品管理系統 =====");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("9. 結束並顯示操作摘要");
    }

    // 輔助方法 3: 顯示所有商品 (功能 1)
    private static void displayAllProducts() {
        System.out.println("[目前商品清單]");
        for (int i = 0; i < productCount; i++) {
            System.out.printf("[%d] %s%n", i + 1, products[i].toString());
        }
    }

    // 輔助方法 4: 搜尋商品 (功能 2)
    private static void searchProductByName() {
        System.out.print("請輸入要搜尋的商品完整名稱: ");
        String name = scanner.nextLine();
        
        int index = findProductIndex(name);
        if (index != -1) {
            System.out.println("找到商品資訊：");
            System.out.println(products[index].toString());
        } else {
            System.out.println("找不到此商品，請確認名稱是否正確。");
        }
        totalOperations++;
    }

    // 輔助方法 5: 新增商品 (功能 3)
    private static void addNewProduct() {
        if (productCount >= products.length) {
            System.out.println("錯誤：商品庫存位置已滿（上限 10 項），無法新增！");
            return;
        }

        System.out.print("請輸入新商品名稱: ");
        String name = scanner.nextLine();

        // 規則：不可新增重複名稱 (忽略大小寫與前後空白)
        if (findProductIndex(name) != -1) {
            System.out.println("錯誤：此商品名稱已存在，不可重複新增！");
            return;
        }

        System.out.print("請輸入價格: ");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.print("請輸入初始庫存: ");
        int stock = Integer.parseInt(scanner.nextLine());

        products[productCount] = new Product(name, price, stock);
        productCount++;
        System.out.println("商品新增成功！");
        totalOperations++;
    }

    // 輔助方法 6: 出售商品 (功能 4，呼叫 product.sell)
    private static void sellProduct() {
        System.out.print("請輸入要出售的商品名稱: ");
        String name = scanner.nextLine();

        int index = findProductIndex(name);
        if (index == -1) {
            System.out.println("錯誤：找不到該商品！");
            return;
        }

        System.out.print("請輸入出售數量: ");
        int amount = Integer.parseInt(scanner.nextLine());

        Product product = products[index];
        int pricePerUnit = product.getPrice(); // 取得單價
        
        if (product.sell(amount)) {
            long saleAmount = (long) pricePerUnit * amount;
            totalSalesRevenue += saleAmount;
            System.out.printf("出售成功！賣出 %d 件，獲得金額: %d 元%n", amount, saleAmount);
        } else {
            System.out.println("出售失敗：庫存不足或輸入數量無效！");
        }
        totalOperations++;
    }

    // 輔助方法 7: 補充庫存 (功能 5，改為呼叫你的 product.restock)
    private static void restockProduct() {
        System.out.print("請輸入要補充庫存的商品名稱: ");
        String name = scanner.nextLine();

        int index = findProductIndex(name);
        if (index == -1) {
            System.out.println("錯誤：找不到該商品！");
            return;
        }

        System.out.print("請輸入補充數量: ");
        int amount = Integer.parseInt(scanner.nextLine());

        if (products[index].restock(amount)) {
            System.out.println("庫存補充成功！目前庫存: " + products[index].getStock());
        } else {
            System.out.println("錯誤：補充數量必須大於 0！");
        }
        totalOperations++;
    }

    // 輔助方法 8: 修改價格 (功能 6，呼叫 product.setPrice)
    private static void updateProductPrice() {
        System.out.print("請輸入要修改價格的商品名稱: ");
        String name = scanner.nextLine();

        int index = findProductIndex(name);
        if (index == -1) {
            System.out.println("錯誤：找不到該商品！");
            return;
        }

        System.out.print("請輸入新價格: ");
        int newPrice = Integer.parseInt(scanner.nextLine());

        if (products[index].setPrice(newPrice)) {
            System.out.println("價格修改成功！");
        } else {
            System.out.println("錯誤：價格必須大於 0！");
        }
        totalOperations++;
    }

    // 輔助方法 9: 顯示低庫存商品 (功能 7，直接呼叫你的 product.isLowStock)
    private static void displayLowStockProducts() {
        System.out.println("[低庫存商品清單 (庫存少於 10 件)]");
        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (products[i].isLowStock()) {
                System.out.println(products[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
        totalOperations++;
    }

    // 輔助方法 10: 顯示總價值 (功能 8，直接呼叫你的 product.getInventoryValue)
    private static void displayTotalInventoryValue() {
        long totalValue = 0;
        for (int i = 0; i < productCount; i++) {
            totalValue += products[i].getInventoryValue();
        }
        System.out.println("目前所有商品的庫存總價值為: " + totalValue + " 元");
        totalOperations++;
    }

    // 輔助方法 11: 結束並顯示摘要 (功能 9)
    private static void displaySummaryAndExit() {
        System.out.println("\n=============== 操作摘要 ===============");
        System.out.println("感謝使用商品管理系統！本次執行摘要如下：");
        System.out.println("1. 最終系統內商品總數: " + productCount + " 項");
        System.out.println("2. 本次執行期間有效操作次數: " + totalOperations + " 次");
        System.out.println("3. 本次交易總銷售額: " + totalSalesRevenue + " 元");
        System.out.println("========================================");
    }

    // 輔助方法 12: 尋找商品索引值 (核心搜尋邏輯，忽略大小寫與前後空白)
    private static int findProductIndex(String targetName) {
        if (targetName == null) return -1;
        String cleanTarget = targetName.trim();
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(cleanTarget)) {
                return i;
            }
        }
        return -1;
    }
}