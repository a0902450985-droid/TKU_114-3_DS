import java.util.Scanner;

/*
 * 測試案例紀錄 (至少 6 組)：
 * 1. 搜尋完整名稱 (忽略大小寫與前後空白)：輸入 "  apple iphone  " -> 應成功找到 Apple iPhone (價格: 32000)
 * 2. 搜尋完整名稱 (不存在商品)：輸入 "Sony TV" -> 應顯示找不到該商品
 * 3. 部分名稱搜尋 (單一結果)：輸入 "Notebook" -> 應找到 ASUS Notebook
 * 4. 部分名稱搜尋 (多筆結果)：輸入 "phone" -> 應找到 Apple iPhone、Samsung Phone 兩筆
 * 5. 部分名稱搜尋 (不匹配)：輸入 "xyz" -> 應顯示找不到任何商品
 * 6. 搜尋關鍵字第一次出現位置：輸入關鍵字 "phone" -> 商品 "Apple iPhone" 應顯示位置在第 6 個字元 (索引值 6)
 */

public class ProductSearchSystem {

    // 使用 0714 的商品資料陣列 (平行陣列，索引值互相對應)
    static String[] productNames = {"Apple iPhone", "ASUS Notebook", "Samsung Phone", "Sony Headphones", "Logitech Mouse"};
    static int[] productPrices = {32000, 28500, 25000, 5500, 1200};
    static int[] productStocks = {50, 15, 30, 40, 100};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            printMenu();
            System.out.print("請選擇功能 (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 吃掉換行符號

            if (choice == 6) {
                System.out.println("系統已結束，謝謝使用！");
                break;
            }

            switch (choice) {
                case 1:
                    showAllProducts();
                    break;
                case 2:
                    System.out.print("請輸入要搜尋的完整商品名稱: ");
                    searchExactName(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("請輸入部分關鍵字: ");
                    searchPartialName(scanner.nextLine());
                    break;
                case 4:
                    showLongestProductName();
                    break;
                case 5:
                    System.out.print("請輸入要搜尋的關鍵字: ");
                    showKeywordIndex(scanner.nextLine());
                    break;
                default:
                    System.out.println("【錯誤】無效的選項，請重新輸入！");
            }
            System.out.println(); // 換行美化
        }
        scanner.close();
    }

    // 列印功能選單
    public static void printMenu() {
        System.out.println("===== 商品名稱搜尋系統 =====");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋 (忽略大小寫、前後空白)");
        System.out.println("3. 部分名稱搜尋 (可顯示多筆結果)");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示商品名稱與搜尋關鍵字第一次出現的位置");
        System.out.println("6. 結束");
    }

    // 1. 顯示全部商品
    public static void showAllProducts() {
        System.out.println("\n--- 商品清單 ---");
        for (int i = 0; i < productNames.length; i++) {
            System.out.printf("名稱: %-16s | 價格: %-6d | 庫存: %-4d\n", productNames[i], productPrices[i], productStocks[i]);
        }
    }

    // 2. 完整名稱搜尋 (忽略大小寫、前後空白)
    public static void searchExactName(String query) {
        String cleanQuery = query.trim(); // 去除前後空白
        boolean found = false;

        for (int i = 0; i < productNames.length; i++) {
            if (productNames[i].equalsIgnoreCase(cleanQuery)) {
                System.out.printf("【找到商品】名稱: %s | 價格: %d | 庫存: %d\n", productNames[i], productPrices[i], productStocks[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("找不到該商品，請檢查輸入是否正確。");
        }
    }

    // 3. 部分名稱搜尋 (可顯示多筆結果)
    public static void searchPartialName(String query) {
        String cleanQuery = query.toLowerCase().trim();
        boolean found = false;

        for (int i = 0; i < productNames.length; i++) {
            // 轉小寫後做包含比對，達成忽略大小寫的部分搜尋
            if (productNames[i].toLowerCase().contains(cleanQuery)) {
                System.out.printf("【找到商品】名稱: %s | 價格: %d | 庫存: %d\n", productNames[i], productPrices[i], productStocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到任何包含該關鍵字的商品。");
        }
    }

    // 4. 顯示名稱最長的商品
    public static void showLongestProductName() {
        int longestIndex = 0;
        for (int i = 1; i < productNames.length; i++) {
            if (productNames[i].length() > productNames[longestIndex].length()) {
                longestIndex = i;
            }
        }
        System.out.println("名稱最長的商品為: " + productNames[longestIndex] + " (長度: " + productNames[longestIndex].length() + ")");
    }

    // 5. 顯示商品名稱與搜尋關鍵字第一次出現的位置
    public static void showKeywordIndex(String query) {
        String cleanQuery = query.toLowerCase().trim();
        boolean found = false;

        for (String name : productNames) {
            int index = name.toLowerCase().indexOf(cleanQuery);
            if (index != -1) {
                System.out.printf("商品: [%s] -> 關鍵字 \"%s\" 首次出現於索引值: %d (第 %d 個字元)\n", name, query, index, index + 1);
                found = true;
            }
        }
        if (!found) {
            System.out.println("所有商品名稱中皆未包含此關鍵字。");
        }
    }
}
