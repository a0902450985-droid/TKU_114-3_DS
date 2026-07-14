import java.util.Scanner;

public class ProductArraySystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 初始化商品資料（三個對應格式的一維陣列）
        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        // 用於記錄本次操作摘要的變數
        int totalPurchasedCount = 0; // 累計購買商品總件數
        int totalPurchasedAmount = 0; // 累計購買總金額
        int totalRestockedCount = 0;  // 累計補貨總件數

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("請選擇操作項目 (1~7): ");
            if (!sc.hasNextInt()) {
                System.out.println("【錯誤】請輸入數字 1 到 7 之間的選項！\n");
                sc.next(); // 消耗錯誤輸入
                continue;
            }
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // 1. 顯示全部商品
                    showAllProducts(names, prices, stocks);
                    break;
                case 2:
                    // 2. 依商品編號查詢
                    queryProductById(sc, names, prices, stocks);
                    break;
                case 3:
                    // 3. 購買商品並扣庫存
                    int[] buyResult = purchaseProduct(sc, names, prices, stocks);
                    totalPurchasedCount += buyResult[0];
                    totalPurchasedAmount += buyResult[1];
                    break;
                case 4:
                    // 4. 補充商品庫存
                    totalRestockedCount += restockProduct(sc, names, stocks);
                    break;
                case 5:
                    // 5. 顯示庫存低商品（少於10）
                    showLowStockProducts(names, stocks);
                    break;
                case 6:
                    // 6. 顯示全部庫存總價值
                    showTotalInventoryValue(names, prices, stocks);
                    break;
                case 7:
                    // 7. 結束並顯示操作摘要
                    showSummary(totalPurchasedCount, totalPurchasedAmount, totalRestockedCount);
                    running = false;
                    break;
                default:
                    System.out.println("【錯誤】無此選項，請重新輸入 1~7！\n");
            }
        }
        sc.close();
    }

    /**
     * 顯示功能選單
     */
    public static void displayMenu() {
        System.out.println("========== 商品管理系統 ==========");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品並扣庫存");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示庫存低商品 (庫存 < 10)");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束並顯示操作摘要");
        System.out.println("=================================");
    }

    /**
     * 自訂方法 1：顯示全部商品
     */
    public static void showAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 商品清單 ---");
        System.out.printf("%-6s\t%-15s\t%-8s\t%-8s\n", "編號", "商品名稱", "單價", "庫存量");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            // 商品編號從 1 開始顯示
            System.out.printf("#%-5d\t%-15s\t$%-7d\t%-8d\n", (i + 1), names[i], prices[i], stocks[i]);
        }
        System.out.println();
    }

    /**
     * 自訂方法 2：依商品編號查詢
     */
    public static void queryProductById(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入欲查詢的商品編號 (1~" + names.length + "): ");
        if (sc.hasNextInt()) {
            int id = sc.nextInt();
            // 轉成索引時要減 1
            int index = id - 1;
            if (index >= 0 && index < names.length) {
                System.out.println("\n【查詢結果】");
                System.out.println("商品名稱: " + names[index]);
                System.out.println("商品單價: $" + prices[index]);
                System.out.println("目前庫存: " + stocks[index] + "\n");
                return;
            }
        } else {
            sc.next(); // 消耗非整數輸入
        }
        System.out.println("【錯誤】商品編號不存在！\n");
    }

    /**
     * 自訂方法 3：購買商品並扣庫存
     * 回傳包含 [購買件數, 購買金額] 的陣列，用於主程式更新操作摘要
     */
    public static int[] purchaseProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        int[] result = {0, 0}; // [0]: 購買件數, [1]: 購買金額
        System.out.print("請輸入欲購買的商品編號 (1~" + names.length + "): ");
        if (!sc.hasNextInt()) {
            sc.next();
            System.out.println("【錯誤】無效的商品編號！\n");
            return result;
        }
        int id = sc.nextInt();
        int index = id - 1;

        if (index < 0 || index >= names.length) {
            System.out.println("【錯誤】商品編號不存在！\n");
            return result;
        }

        System.out.print("請輸入欲購買的數量: ");
        if (!sc.hasNextInt()) {
            sc.next();
            System.out.println("【錯誤】數量必須為整數！\n");
            return result;
        }
        int quantity = sc.nextInt();

        // 驗證規則：購買數量必須大於 0 且不能超過庫存
        if (quantity <= 0) {
            System.out.println("【錯誤】購買數量必須大於 0！\n");
        } else if (quantity > stocks[index]) {
            System.out.println("【錯誤】庫存不足！目前庫存僅剩 " + stocks[index] + " 件。\n");
        } else {
            stocks[index] -= quantity; // 扣庫存
            int cost = prices[index] * quantity;
            System.out.println("【購買成功】您已購買 " + names[index] + " * " + quantity + " 件，總共 $" + cost + "\n");
            result[0] = quantity;
            result[1] = cost;
        }
        return result;
    }

    /**
     * 自訂方法 4：補充商品庫存
     * 回傳補貨數量，用於更新操作摘要
     */
    public static int restockProduct(Scanner sc, String[] names, int[] stocks) {
        System.out.print("請輸入欲補貨的商品編號 (1~" + names.length + "): ");
        if (!sc.hasNextInt()) {
            sc.next();
            System.out.println("【錯誤】無效的商品編號！\n");
            return 0;
        }
        int id = sc.nextInt();
        int index = id - 1;

        if (index < 0 || index >= names.length) {
            System.out.println("【錯誤】商品編號不存在！\n");
            return 0;
        }

        System.out.print("請輸入補貨數量: ");
        if (!sc.hasNextInt()) {
            sc.next();
            System.out.println("【錯誤】補貨數量必須為整數！\n");
            return 0;
        }
        int quantity = sc.nextInt();

        // 驗證規則：補貨數量必須大於 0
        if (quantity <= 0) {
            System.out.println("【錯誤】補貨數量必須大於 0！\n");
            return 0;
        } else {
            stocks[index] += quantity; // 增加庫存
            System.out.println("【補貨成功】商品 " + names[index] + " 已成功補貨 " + quantity + " 件，目前新庫存為: " + stocks[index] + "\n");
            return quantity;
        }
    }

    /**
     * 自訂方法 5：顯示庫存低商品 (庫存 < 10)
     */
    public static void showLowStockProducts(String[] names, int[] stocks) {
        System.out.println("\n--- 低庫存商品警告 (少於10件) ---");
        boolean found = false;
        System.out.printf("%-15s\t%-8s\n", "商品名稱", "目前庫存");
        System.out.println("---------------------------------");
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.printf("%-15s\t%-8d\n", names[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("（目前無低庫存商品，所有商品貨源充足！）");
        }
        System.out.println();
    }

    /**
     * 自訂方法 6：顯示全部庫存總價值
     */
    public static void showTotalInventoryValue(String[] names, int[] prices, int[] stocks) {
        int totalValue = 0;
        System.out.println("\n--- 庫存價值分析 ---");
        System.out.printf("%-15s\t%-8s\t%-8s\t%-10s\n", "商品名稱", "單價", "庫存量", "單項總價值");
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            int itemValue = prices[i] * stocks[i];
            totalValue += itemValue;
            System.out.printf("%-15s\t$%-7d\t%-8d\t$%-10d\n", names[i], prices[i], stocks[i], itemValue);
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("【庫存總價值】為: $" + totalValue + "\n");
    }

    /**
     * 自訂方法 7：結束並顯示操作摘要
     */
    public static void showSummary(int buyCount, int buyAmount, int restockCount) {
        System.out.println("\n=================================");
        System.out.println("   系統已關閉，感謝您的使用！");
        System.out.println("=================================");
        System.out.println("【本日操作摘要】");
        System.out.println(" * 累計購買商品件數: " + buyCount + " 件");
        System.out.println(" * 累計購買總消費額: $" + buyAmount);
        System.out.println(" * 累計補充庫存件數: " + restockCount + " 件");
        System.out.println("=================================\n");
    }
}