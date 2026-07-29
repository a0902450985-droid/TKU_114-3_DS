import java.util.Scanner;

public class ProductSortingSystem {

    public static void main(String[] args) {
        // 1. 建立 10 筆商品原始資料
        StoreProduct[] originalProducts = {
            new StoreProduct("P001", "MacBook Pro", 45000, 15),
            new StoreProduct("P002", "iPhone 15", 28000, 50),
            new StoreProduct("P003", "AirPods Pro", 6000, 120),
            new StoreProduct("P004", "iPad Air", 19900, 30),
            new StoreProduct("P005", "Apple Watch", 12900, 45),
            new StoreProduct("P006", "Gaming Mouse", 2500, 80),
            new StoreProduct("P007", "Mechanical KB", 3500, 25),
            new StoreProduct("P008", "4K Monitor", 15000, 10),
            new StoreProduct("P009", "USB-C Hub", 1200, 200),
            new StoreProduct("P010", "Webcam HD", 1800, 5)
        };

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n========== 商品排序選單 ==========");
            System.out.println("1. 價格升冪 (由低到高)");
            System.out.println("2. 價格降冪 (由高到低)");
            System.out.println("3. 庫存降冪 (由多到少)");
            System.out.println("4. 顯示原始列表");
            System.out.println("0. 離開系統");
            System.out.print("請選擇操作模式: ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("系統已結束。");
                exit = true;
                break;
            }

            if (choice == 4) {
                System.out.println("\n[原始商品列表]");
                printProducts(originalProducts);
                continue;
            }

            if (choice < 1 || choice > 3) {
                System.out.println("無效的選項，請重新輸入！");
                continue;
            }

            // Requirement 4: 每次排序前從原始資料複製一份，避免相互影響
            StoreProduct[] workingArray = cloneArray(originalProducts);

            String sortField = "";
            String sortDirection = "";

            // 根據選擇進行手寫排序
            switch (choice) {
                case 1:
                    sortField = "價格";
                    sortDirection = "升冪 (低 -> 高)";
                    customInsertionSort(workingArray, 1);
                    break;
                case 2:
                    sortField = "價格";
                    sortDirection = "降冪 (高 -> 低)";
                    customInsertionSort(workingArray, 2);
                    break;
                case 3:
                    sortField = "庫存";
                    sortDirection = "降冪 (多 -> 少)";
                    customInsertionSort(workingArray, 3);
                    break;
            }

            // Requirement 5: 顯示排序位置欄及排序方向
            System.out.printf("%n=== 排序結果 [排序欄位: %s | 排序方向: %s] ===%n", sortField, sortDirection);
            printProducts(workingArray);
        }

        scanner.close();
    }

    /**
     * Requirement 4: 深拷貝 (Deep Copy) 陣列，確保原資料不被變更
     */
    private static StoreProduct[] cloneArray(StoreProduct[] source) {
        StoreProduct[] copy = new StoreProduct[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = new StoreProduct(source[i]);
        }
        return copy;
    }

    /**
     * Requirement 3: 手寫實作插入排序 (Insertion Sort)
     * mode: 1 = 價格升冪, 2 = 價格降冪, 3 = 庫存降冪
     */
    public static void customInsertionSort(StoreProduct[] arr, int mode) {
        for (int i = 1; i < arr.length; i++) {
            StoreProduct key = arr[i];
            int j = i - 1;

            while (j >= 0 && shouldSwap(arr[j], key, mode)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * 比較邏輯判斷
     */
    private static boolean shouldSwap(StoreProduct prev, StoreProduct current, int mode) {
        switch (mode) {
            case 1: // 價格升冪：前面價格比後面高，則後移
                return prev.getPrice() > current.getPrice();
            case 2: // 價格降冪：前面價格比後面低，則後移
                return prev.getPrice() < current.getPrice();
            case 3: // 庫存降冪：前面庫存比後面少，則後移
                return prev.getStock() < current.getStock();
            default:
                return false;
        }
    }

    /**
     * 印出商品資料
     */
    public static void printProducts(StoreProduct[] products) {
        for (int i = 0; i < products.length; i++) {
            System.out.printf("[%2d] %s%n", (i + 1), products[i]);
        }
    }
}