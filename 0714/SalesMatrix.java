import java.util.Scanner;

public class SalesMatrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 宣告 3 列 4 欄的二維陣列 (3項商品, 4個分區)
        int[][] sales = new int[3][4];
        
        // 1. 輸入每一格銷售數量 (含方法與驗證)
        inputSalesData(sc, sales);
        
        // 2、3、4. 以表格形式顯示完整資料，並包含每列與每欄的加總
        displaySalesTable(sales);
        
        // 5. 確定總銷售量最高的商品
        findTopProduct(sales);
        
        sc.close();
    }

    /**
     * 1. 輸入每一格銷售數量，數值不得小於 0
     */
    public static void inputSalesData(Scanner sc, int[][] sales) {
        System.out.println("=== 請輸入銷售數據 ===");
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                while (true) {
                    System.out.print("請輸入 商品 " + (i + 1) + " 在 分區 " + (j + 1) + " 的銷售量: ");
                    if (sc.hasNextInt()) {
                        int val = sc.nextInt();
                        if (val >= 0) {
                            sales[i][j] = val;
                            break; // 輸入正確，跳出此格的維護迴圈
                        }
                    } else {
                        sc.next(); // 消耗錯誤輸入
                    }
                    System.out.println("【輸入錯誤】銷售量不得小於 0，請重新輸入！");
                }
            }
        }
    }

    /**
     * 2、3、4. 以表格形式顯示完整資料，計算每項商品(列)與各分區(欄)的總和
     */
    public static void displaySalesTable(int[][] sales) {
        System.out.println("\n====================== 銷售矩陣報表 ======================");
        // 輸出表頭
        System.out.printf("%-10s\t%-8s\t%-8s\t%-8s\t%-8s\t%-8s\n", "項目", "分區1", "分區2", "分區3", "分區4", "商品總計");
        System.out.println("-------------------------------------------------------------------------");

        // 用來儲存各分區(欄)總和的陣列，長度為 4
        int[] colTotals = new int[4];

        // 走訪每一列 (商品)
        for (int i = 0; i < sales.length; i++) {
            int rowTotal = 0; // 每一項商品的銷售總體目標加總
            System.out.printf("商品 %d\t", (i + 1));
            
            // 走訪該列的每一欄 (分區)
            for (int j = 0; j < sales[i].length; j++) {
                System.out.printf("%-8d\t", sales[i][j]);
                rowTotal += sales[i][j];      // 累加列總和
                colTotals[j] += sales[i][j];   // 累加欄總和
            }
            // 輸出該列(商品)的總銷售量
            System.out.printf("%-8d\n", rowTotal);
        }

        System.out.println("-------------------------------------------------------------------------");
        // 4. 輸出各分區的銷售重點（欄總和）
        System.out.print("分區總計\t");
        int grandTotal = 0;
        for (int j = 0; j < colTotals.length; j++) {
            System.out.printf("%-8d\t", colTotals[j]);
            grandTotal += colTotals[j];
        }
        // 右下角的交叉總計
        System.out.printf("%-8d\n", grandTotal);
        System.out.println("=========================================================================");
    }

    /**
     * 5. 確定總銷售量最高的商品
     */
    public static void findTopProduct(int[][] sales) {
        int maxSales = -1;
        int topProductIndex = -1;

        for (int i = 0; i < sales.length; i++) {
            int currentProductTotal = 0;
            for (int j = 0; j < sales[i].length; j++) {
                currentProductTotal += sales[i][j];
            }
            
            // 比較找出最高銷售量
            if (currentProductTotal > maxSales) {
                maxSales = currentProductTotal;
                topProductIndex = i;
            }
        }

        System.out.println("\n【統計結果】");
        System.out.println("總銷售量最高的商品為: 商品 " + (topProductIndex + 1) + "，總銷售量為: " + maxSales);
    }
}
