import java.util.Arrays;
import java.util.Random;

public class SortingExperiment {

    public static void main(String[] args) {
        int size = 100; // 測試資料筆數

        // 1. 準備三組原始資料
        int[] sortedData = generateSortedArray(size);
        int[] reversedData = generateReversedArray(size);
        int[] randomData = generateRandomArray(size);

        System.out.println("==================================================");
        System.out.println("          排序演算法操作統計實驗 (N = " + size + ")");
        System.out.println("==================================================\n");

        // 測試已排序資料
        testAndCompare("1. 已排序資料 (Sorted)", sortedData);

        // 測試逆向排序資料
        testAndCompare("2. 逆向排序資料 (Reversed)", reversedData);

        // 測試隨機排列資料
        testAndCompare("3. 隨機排列資料 (Random)", randomData);

        // 5. 輸出觀察結論
        printConclusions();
    }

    /**
     * 針對同一組原始資料，複製副本後分別進行選擇排序與插入排序並印出結果
     */
    private static void testAndCompare(String label, int[] originalData) {
        System.out.println("--------------------------------------------------");
        System.out.println("【" + label + "】");
        System.out.println("--------------------------------------------------");

        // 4. 所有演算法必須使用相同的原始輸入副本
        int[] dataForSelection = Arrays.copyOf(originalData, originalData.length);
        int[] dataForInsertion = Arrays.copyOf(originalData, originalData.length);

        // 執行選擇排序
        SortMetrics selectionMetrics = selectionSort(dataForSelection);
        System.out.printf("選擇排序 (Selection Sort) -> %s%n", selectionMetrics);

        // 執行插入排序
        SortMetrics insertionMetrics = insertionSort(dataForInsertion);
        System.out.printf("插入排序 (Insertion Sort) -> %s%n", insertionMetrics);
        System.out.println();
    }

    /**
     * 統計數據封裝類別
     */
    static class SortMetrics {
        long comparisons = 0; // 比較次數
        long swaps = 0;       // 交換次數
        long moves = 0;       // 移動/賦值次數

        @Override
        public String toString() {
            return String.format("比較次數: %5d | 交換次數: %5d | 移動次數: %5d", comparisons, swaps, moves);
        }
    }

    /**
     * 2 & 3. 選擇排序實作（含統計）
     */
    public static SortMetrics selectionSort(int[] arr) {
        SortMetrics metrics = new SortMetrics();
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                metrics.comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                // 發生交換
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
                metrics.swaps++;
                metrics.moves += 3; // 每次交換涉及 3 次賦值/移動
            }
        }
        return metrics;
    }

    /**
     * 2 & 3. 插入排序實作（含統計）
     */
    public static SortMetrics insertionSort(int[] arr) {
        SortMetrics metrics = new SortMetrics();
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            metrics.moves++; // 取出 key 算作 1 次移動
            int j = i - 1;

            while (j >= 0) {
                metrics.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j]; // 元素後移
                    metrics.moves++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key; // 放回 key
            metrics.moves++;
        }
        return metrics;
    }

    // 產生已排序陣列 [0, 1, 2, ..., size-1]
    private static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = i;
        return arr;
    }

    // 產生逆向排序陣列 [size, size-1, ..., 1]
    private static int[] generateReversedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = size - i;
        return arr;
    }

    // 產生隨機陣列
    private static int[] generateRandomArray(int size) {
        Random rand = new Random(42); // 固定種子確保可重複測試
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = rand.nextInt(1000);
        return arr;
    }

    /**
     * 5. 輸出觀察結論
     */
    private static void printConclusions() {
        System.out.println("==================================================");
        System.out.println("                  實驗觀察結論                     ");
        System.out.println("==================================================");
        System.out.println("1. 選擇排序 (Selection Sort)：");
        System.out.println("   - 無論輸入資料為何種狀態（已排序、逆向、隨機），比較次數皆固定為 N*(N-1)/2 次。");
        System.out.println("   - 交換次數極少（最多 N-1 次），適合『元素交換成本極高』的環境。");
        System.out.println();
        System.out.println("2. 插入排序 (Insertion Sort)：");
        System.out.println("   - 對『已排序』的資料表現最佳，僅需 N-1 次比較且 0 次交換，時間複雜度降為 O(N)。");
        System.out.println("   - 在『逆向排序』的極端壞情況下，比較與移動次數會達到最大（O(N^2)）。");
        System.out.println("   - 對於『幾乎已排序』或『隨機資料』，整體操作效率普遍優於選擇排序。");
        System.out.println("==================================================");
    }
}