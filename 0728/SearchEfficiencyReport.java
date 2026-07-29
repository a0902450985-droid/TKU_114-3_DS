import java.util.Arrays;

public class SearchEfficiencyReport {

    public static void main(String[] args) {
        // 1. 建立 16、128、1024 筆已排序資料
        int[] dataSizes = {16, 128, 1024};

        System.out.println("==========================================================================================");
        System.out.println("                               循序搜尋 vs 二分搜尋 效率分析報告                             ");
        System.out.println("==========================================================================================");

        for (int size : dataSizes) {
            // 初始化升序陣列 [1, 2, 3, ..., size]
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = i + 1;
            }

            // 定義測試目標：第一筆、最後一筆、不存在資料
            int firstItem = data[0];
            int lastItem = data[size - 1];
            int nonExistentItem = -999;

            System.out.println("\n------------------------------------------------------------------------------------------");
            System.out.printf("【資料筆數: %d 筆】\n", size);
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.printf("%-15s | %-20s | %-20s\n", "測試案例 (Target)", "循序搜尋比較次數 (Linear)", "二分搜尋比較次數 (Binary)");
            System.out.println("------------------------------------------------------------------------------------------");

            // 測試 1：第一筆資料
            int seqFirst = sequentialSearch(data, firstItem);
            int binFirst = binarySearch(data, firstItem);
            System.out.printf("%-15s | %-25d | %-20d\n", "第一筆 (" + firstItem + ")", seqFirst, binFirst);

            // 測試 2：最後一筆資料
            int seqLast = sequentialSearch(data, lastItem);
            int binLast = binarySearch(data, lastItem);
            System.out.printf("%-15s | %-25d | %-20d\n", "最後一筆 (" + lastItem + ")", seqLast, binLast);

            // 測試 3：不存在資料
            int seqNone = sequentialSearch(data, nonExistentItem);
            int binNone = binarySearch(data, nonExistentItem);
            System.out.printf("%-15s | %-25d | %-20d\n", "不存在 (" + nonExistentItem + ")", seqNone, binNone);
        }

        // 5. 在計畫輸出的最後寫出觀察結果
        printObservations();
    }

    /**
     * 循序搜尋 (Linear Search)
     * @return 實際比較次數
     */
    public static int sequentialSearch(int[] data, int target) {
        int compareCount = 0;
        for (int value : data) {
            compareCount++;
            if (value == target) {
                break;
            }
        }
        return compareCount;
    }

    /**
     * 二分搜尋 (Binary Search)
     * @return 實際比較次數
     */
    public static int binarySearch(int[] data, int target) {
        int compareCount = 0;
        int low = 0;
        int high = data.length - 1;

        while (low <= high) {
            compareCount++;
            int mid = low + (high - low) / 2;

            if (data[mid] == target) {
                break;
            } else if (data[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return compareCount;
    }

    /**
     * 輸出觀察結果與分析
     */
    public static void printObservations() {
        System.out.println("\n==========================================================================================");
        System.out.println("                                     實驗觀察結果與分析報告                                 ");
        System.out.println("==========================================================================================");
        System.out.println("1. 時間複雜度比較 (Time Complexity)：");
        System.out.println("   - 循序搜尋 (Linear Search) 的時間複雜度為 O(N)：");
        System.out.println("     比較次數隨資料量呈「線性成長」。在搜尋最後一筆或不存在資料時（最壞情況），比較次數等於資料筆數 N。");
        System.out.println("   - 二分搜尋 (Binary Search) 的時間複雜度為 O(log N)：");
        System.out.println("     比較次數隨資料量呈「對數成長」。即便資料量擴增 64 倍（從 16 筆至 1024 筆），最壞比較次數僅從 4~5 次增加至 10~11 次。");
        
        System.out.println("\n2. 特殊案例表現分析：");
        System.out.println("   - 搜尋「第一筆資料」：循序搜尋只需 1 次比對，表現優於二分搜尋；二分搜尋仍需進行對數分割（約 log2 N 次）。");
        System.out.println("   - 搜尋「最後一筆 / 不存在資料」：二分搜尋展現極大優勢，在 1024 筆資料中僅需約 10 次比對，而循序搜尋則需 1024 次。");
        
        System.out.println("\n3. 為何不以執行時間 (Execution Time) 為唯一基準：");
        System.out.println("   - 執行時間容易受到 CPU 負載、JVM JIT 編譯器優化、作業系統排程及記憶體快取 (Cache) 等環境干擾。");
        System.out.println("   - 以「實際比較次數 (Operation/Comparison Count)」作為評估標準，能提供不受硬體干擾且具備高度可重複性的客觀指標。");
        System.out.println("==========================================================================================");
    }
}
