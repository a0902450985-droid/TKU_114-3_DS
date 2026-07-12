import java.util.Scanner;

public class PatternGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // 1. 可重複顯示選單
            printMenu();
            System.out.print("請選擇功能選項 (0-4): ");
            int option = sc.nextInt();

            // 0. 離開
            if (option == 0) {
                System.out.println("程式結束，謝謝使用！");
                break;
            }

            // 2. 使用 switch 判斷選項
            switch (option) {
                case 1:
                    // 選項 1：輸出完整九九乘法表
                    printMultiplicationTable();
                    break;

                case 2:
                    // 選項 2：輸入高度，輸出正三角形星號
                    int height1 = readPositiveInt(sc, "請輸入正三角形高度 (必須大於 0): ");
                    printTriangle(height1);
                    break;

                case 3:
                    // 選項 3：輸入高度，輸出倒三角形星號
                    int height2 = readPositiveInt(sc, "請輸入倒三角形高度 (必須大於 0): ");
                    printReverseTriangle(height2);
                    break;

                case 4:
                    // 選項 4：輸入列數與欄數，輸出數字方陣
                    int rows = readPositiveInt(sc, "請輸入方陣列數 (rows, 必須大於 0): ");
                    int cols = readPositiveInt(sc, "請輸入方陣欄數 (cols, 必須大於 0): ");
                    printNumberGrid(rows, cols);
                    break;

                default:
                    System.out.println("無效選項，請重新選擇！\n");
                    break;
            }
        }
        sc.close();
    }

    // ==================== Method 要求實作區 ====================

    /**
     * 顯示功能選單
     */
    public static void printMenu() {
        System.out.println("====== 圖形與表格產生器 ======");
        System.out.println("1. 九九乘法表");
        System.out.println("2. 正三角形星號");
        System.out.println("3. 倒三角形星號");
        System.out.println("4. 數字方陣");
        System.out.println("0. 離開");
        System.out.println("=============================");
    }

    /**
     * 讀取並驗證大於 0 的整數
     */
    public static int readPositiveInt(Scanner sc, String message) {
        int value;
        while (true) {
            System.out.print(message);
            value = sc.nextInt();
            if (value > 0) {
                return value;
            }
            System.out.println("錯誤：輸入的數值必須大於 0，請重新輸入。");
        }
    }

    /**
     * 1. 輸出九九乘法表表格
     */
    public static void printMultiplicationTable() {
        System.out.println("\n--- 九九乘法表 ---");
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                // 使用 \t 讓表格排版對齊
                System.out.print(j + "*" + i + "=" + (i * j) + "\t");
            }
            System.out.println(); // 換行
        }
        System.out.println();
    }

    /**
     * 2. 輸出正三角形星號
     */
    public static void printTriangle(int height) {
        System.out.println("\n--- 正三角形星號 ---");
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 3. 輸出倒三角形星號
     */
    public static void printReverseTriangle(int height) {
        System.out.println("\n--- 倒三角形星號 ---");
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 4. 輸出數字方陣
     */
    public static void printNumberGrid(int rows, int cols) {
        System.out.println("\n--- 數字方陣 ---");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}