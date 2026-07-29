import java.util.Arrays;

public class InsertionSortPractice {

    public static void insertionSort(int[] arr, String label) {
        System.out.println("=== " + label + " ===");
        if (arr == null || arr.length <= 1) {
            System.out.println("陣列無需排序： " + Arrays.toString(arr) + "\n");
            return;
        }

        int comparisons = 0;
        int shifts = 0; // 元素右移次數
        int n = arr.length;

        System.out.println("原始陣列: " + Arrays.toString(arr));
        System.out.println("--- 開始 Insertion Sort 追蹤 ---");

        // 從第二個元素 (i = 1) 開始插入處理
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // 暫存目前要插入的值，避免被覆蓋
            int j = i - 1;

            // 在已排序區間中由右向左尋找適合的插入位置
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j]; // 元素向右移動
                    shifts++;
                    j--;
                } else {
                    break; // 找到大於等於 key 的元素即可停止比較
                }
            }

            // 將 key 放入最終的插入位置
            arr[j + 1] = key;

            // 顯示每輪 key、插入位置 (j + 1) 及陣列內容
            System.out.printf("輪次 %d | key: %-2d, 插入位置: %d | 陣列內容: %s%n",
                    i, key, (j + 1), Arrays.toString(arr));
        }

        System.out.println("--- 排序完成 ---");
        System.out.println("最終結果: " + Arrays.toString(arr));
        System.out.println("總比較次數: " + comparisons);
        System.out.println("總右移次數: " + shifts + "\n");
    }

    public static void main(String[] args) {
        // 1. 標準題目資料 {30, 10, 20, 50, 40, 5}
        int[] defaultData = {30, 10, 20, 50, 40, 5};
        insertionSort(defaultData, "測試一：標準測試資料");

        // 4. 測試已排序資料
        int[] sortedData = {5, 10, 20, 30, 40, 50};
        insertionSort(sortedData, "測試二：已排序資料 (Best Case)");

        // 4. 測試反向排序資料
        int[] reverseData = {50, 40, 30, 20, 10, 5};
        insertionSort(reverseData, "測試三：反向排序資料 (Worst Case)");
    }
}