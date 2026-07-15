import java.util.Scanner;

public class ProductDataManager {
    // 設定最大容量
    private static final int MAX_CAPACITY = 20;
    
    // 1 & 2. 宣告商品名稱、價格與庫存陣列
    private static String[] names = new String[MAX_CAPACITY];
    private static int[] prices = new int[MAX_CAPACITY];
    private static int[] stocks = new int[MAX_CAPACITY];
    private static int productCount = 0; // 記錄當前商品數量

    public static void main(String[] args) {
        // 預備資料
        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        // 1. 初始化資料並解析
        for (String record : records) {
            addProduct(record);
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== 商品文字資料管理器已啟動 ===");
        
        while (running) {
            System.out.println("\n--------------------------------");
            System.out.println("1. 顯示商品列表 (表格形式)");
            System.out.println("2. 搜尋商品");
            System.out.println("3. 顯示低庫存商品");
            System.out.println("4. 顯示庫存總價值");
            System.out.println("5. 新增商品資料 (驗證格式)");
            System.out.println("6. 執行測試案例 (自動測試 8 組)");
            System.out.println("0. 離開系統");
            System.out.print("請選擇操作 (0-6): ");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    displayProducts();
                    break;
                case "2":
                    System.out.print("請輸入商品名稱關鍵字: ");
                    String keyword = scanner.nextLine();
                    searchProduct(keyword);
                    break;
                case "3":
                    displayLowStock(10); // 假設庫存低於 10 為低庫存
                    break;
                case "4":
                    calculateTotalValue();
                    break;
                case "5":
                    System.out.print("請輸入新增商品資料 (格式: 名稱,價格,庫存): ");
                    String input = scanner.nextLine();
                    addProduct(input);
                    break;
                case "6":
                    runTestCases();
                    break;
                case "0":
                    running = false;
                    System.out.println("感謝使用，程式已結束。");
                    break;
                default:
                    System.out.println("錯誤：無效的選項，請重新輸入。");
            }
        }
        scanner.close();
    }

    // 1 & 7 & 8. 解析、驗證並加入商品的 method
    public static boolean addProduct(String record) {
        if (productCount >= MAX_CAPACITY) {
            System.out.println("錯誤：管理器容量已滿，無法新增。");
            return false;
        }

        // 檢查是否為空字串或 null
        if (record == null || record.trim().isEmpty()) {
            System.out.println("錯誤：輸入資料不能為空。");
            return false;
        }

        // 使用 split() 解析資料
        String[] parts = record.split(",");
        
        // 驗證格式：必須恰好分割成 3 個部分
        if (parts.length != 3) {
            System.out.println("格式錯誤：[" + record + "] 格式應為 '名稱,價格,庫存' (逗號分隔三個欄位)");
            return false;
        }

        String name = parts[0].trim();
        if (name.isEmpty()) {
            System.out.println("格式錯誤：商品名稱不能為空。");
            return false;
        }

        try {
            // 轉型並捕捉潛在的 NumberFormatException
            int price = Integer.parseInt(parts[1].trim());
            int stock = Integer.parseInt(parts[2].trim());

            if (price < 0 || stock < 0) {
                System.out.println("數字錯誤：價格或庫存不能為負數。");
                return false;
            }

            // 存入陣列
            names[productCount] = name;
            prices[productCount] = price;
            stocks[productCount] = stock;
            productCount++;
            
            System.out.println("成功新增商品: " + name);
            return true;

        } catch (NumberFormatException e) {
            // 8. 顯示轉換錯誤原因，但不中斷程式
            System.out.println("數字轉換錯誤：價格或庫存必須是整數！(詳細原因: " + e.getMessage() + ")");
            return false;
        }
    }

    // 3. 顯示解析後的商品表格
    public static void displayProducts() {
        System.out.println("\n================ 商品列表 ================");
        System.out.printf("%-5s %-15s %-10s %-10s\n", "ID", "商品名稱", "價格 (元)", "庫存數量");
        System.out.println("----------------------------------------");
        for (int i = 0; i < productCount; i++) {
            System.out.printf("[%-3d] %-15s %-10d %-10d\n", i + 1, names[i], prices[i], stocks[i]);
        }
        System.out.println("========================================");
    }

    // 4. 完整名稱與部分名稱搜尋
    public static void searchProduct(String keyword) {
        System.out.println("\n--- 搜尋結果 (關鍵字: \"" + keyword + "\") ---");
        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            // 忽略大小寫進行部分匹配
            if (names[i].toLowerCase().contains(keyword.toLowerCase())) {
                System.out.printf("找到商品 -> 名稱: %s, 價格: %d 元, 庫存: %d\n", names[i], prices[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到任何包含 \"" + keyword + "\" 的商品。");
        }
    }

    // 5. 顯示低庫存商品
    public static void displayLowStock(int threshold) {
        System.out.println("\n--- 低庫存警告商品 (庫存 < " + threshold + ") ---");
        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (stocks[i] < threshold) {
                System.out.printf("⚠️ 商品: %-12s | 庫存僅剩: %d\n", names[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("讚！所有商品庫存水位均在安全線上。");
        }
    }

    // 6. 顯示庫存總價值
    public static void calculateTotalValue() {
        long totalValue = 0;
        for (int i = 0; i < productCount; i++) {
            totalValue += (long) prices[i] * stocks[i];
        }
        System.out.println("\n========================================");
        System.out.println(" 目前全店庫存總價值: " + totalValue + " 元");
        System.out.println("========================================");
    }

    // 9. 附上至少 8 組測試案例
    public static void runTestCases() {
        System.out.println("\n========= 開始執行自動測試案例 =========");
        
        String[] testCases = {
            "Speaker,1500,6",          // Case 1: 正確資料（應該成功）
            "Microphone, 3200 , 15",   // Case 2: 帶有前後空白（應該成功，有 trim）
            "iPad,18900",              // Case 3: 格式錯誤，缺少庫存（應該被攔截且不崩潰）
            "iPhone,32000,10,Extra",   // Case 4: 格式錯誤，多出欄位（應該被攔截且不崩潰）
            "PowerBank,abc,15",        // Case 5: 價格非數字（應該捕捉 NumberFormatException 且不崩潰）
            "Charger,500,xyz",         // Case 6: 庫存非數字（應該捕捉 NumberFormatException 且不崩潰）
            ",600,10",                 // Case 7: 商品名稱為空字串（應該被攔截）
            "Router,-500,10"           // Case 8: 價格為負數（邏輯錯誤攔截）
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("\n[測試案例 " + (i + 1) + "] 輸入: \"" + testCases[i] + "\"");
            boolean success = addProduct(testCases[i]);
            System.out.println("測試結果: " + (success ? "✅ 成功寫入" : "❌ 攔截成功/寫入失敗"));
        }
        System.out.println("\n========= 測試案例執行完畢 =========");
    }
}
