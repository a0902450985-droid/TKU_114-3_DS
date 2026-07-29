import java.util.Arrays;

public class SelectionSortPractice {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            System.out.println("陣列為空或只有一個元素，無需排序。");
            System.out.println("陣列內容: " + Arrays.toString(arr));
            System.out.println("總比較次數: 0");
            System.out.println("總交換次數: 0\n");
            return;
        }

        int comparisons = 0;
        int swaps = 0;
        int n = arr.length;

        System.out.println("原始陣列: " + Arrays.toString(arr));
        System.out.println("--- 開始 Selection Sort 追蹤 ---");

        // Outer loop: start 代表目前要確定位置的索引
        for (int start = 0; start < n - 1; start++) {
            int minIndex = start;

            // Inner loop: 尋找未排序區間中的最小值索引
            for (int j = start + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // 若找到更小的值，則進行交換
            if (minIndex != start) {
                int temp = arr[start];
                arr[start] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }

            // 每一輪顯示 start、選中的索引及陣列內容
            System.out.printf("輪次 %d | start: %d, 選中(最小值)索引: %d | 陣列內容: %s%n",
                    (start + 1), start, minIndex, Arrays.toString(arr));
        }

        System.out.println("--- 排序完成 ---");
        System.out.println("最終排序結果: " + Arrays.toString(arr));
        System.out.println("總比較次數: " + comparisons);
        System.out.println("總交換次數: " + swaps + "\n");
    }

    public static void main(String[] args) {
        // 1. 主要測試資料 {42, 18, 35, 7, 29, 14}
        System.out.println("=== 測試一：標準陣列 ===");
        int[] numbers = {42, 18, 35, 7, 29, 14};
        selectionSort(numbers);

        // 5. 測試邊界條件：空陣列
        System.out.println("=== 測試二：空陣列 ===");
        int[] emptyArray = {};
        selectionSort(emptyArray);

        // 5. 測試邊界條件：單一元素陣列
        System.out.println("=== 測試三：單一元素陣列 ===");
        int[] singleElementArray = {99};
        selectionSort(singleElementArray);
    }
}