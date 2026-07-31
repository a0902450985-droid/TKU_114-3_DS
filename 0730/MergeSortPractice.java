import java.util.Arrays;

public class MergeSortPractice {

    public static void main(String[] args) {
        System.out.println("=================== 測試 1: 預設題目資料 ===================");
        int[] arr1 = {41, 12, 35, 8, 27, 19, 50, 3};
        runMergeSortTest("測試 1", arr1);

        System.out.println("\n=================== 測試 2: 空陣列 ===================");
        int[] arr2 = {};
        runMergeSortTest("空陣列", arr2);

        System.out.println("\n=================== 測試 3: 單筆資料 ===================");
        int[] arr3 = {99};
        runMergeSortTest("單筆資料", arr3);

        System.out.println("\n=================== 測試 4: 已排序資料 ===================");
        int[] arr4 = {1, 3, 5, 7, 9};
        runMergeSortTest("已排序資料", arr4);

        System.out.println("\n=================== 測試 5: 反向資料 ===================");
        int[] arr5 = {90, 70, 50, 30, 10};
        runMergeSortTest("反向資料", arr5);
    }

    /**
     * 執行 Merge Sort 並顯示測試資訊
     */
    public static void runMergeSortTest(String label, int[] input) {
        int[] arr = Arrays.copyOf(input, input.length);
        System.out.println("[" + label + "] 原始資料: " + Arrays.toString(arr));
        
        if (arr.length > 0) {
            mergeSort(arr, 0, arr.length - 1);
        } else {
            System.out.println("  -> 陣列為空，無需排序。");
        }
        
        System.out.println("[" + label + "] 最終排序結果: " + Arrays.toString(arr));
    }

    /**
     * 遞迴拆分陣列 (Divide)
     * 
     * @param arr 待排序陣列
     * @param left 索引左界
     * @param right 索引右界
     */
    public static void mergeSort(int[] arr, int left, int right) {
        // 遞迴基底（停止條件）：當區間只剩下 1 個元素或不合法時停止
        if (left >= right) {
            return;
        }

        // 顯示拆分範圍 (功能要求 3)
        System.out.printf("拆分區間 [%d..%d]: %s%n", left, right, getSubArrayString(arr, left, right));

        int mid = left + (right - left) / 2;

        // 遞迴拆分左半邊與右半邊
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        // 合併兩半邊
        merge(arr, left, mid, right);
    }

    /**
     * 合併兩個已排序區間 (Merge)
     * 
     * @param arr 原始陣列
     * @param left 左區間起點
     * @param mid 左區間終點 (mid + 1 為右區間起點)
     * @param right 右區間終點
     */
    public static void merge(int[] arr, int left, int mid, int right) {
        // 1. 建立暫存陣列儲存左右兩邊的資料
        int leftLen = mid - left + 1;
        int rightLen = right - mid;

        int[] leftArr = new int[leftLen];
        int[] rightArr = new int[rightLen];

        for (int i = 0; i < leftLen; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < rightLen; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        // 2. 雙指標比較並填回原陣列 arr
        int i = 0, j = 0, k = left;

        while (i < leftLen && j < rightLen) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // 3. 將剩餘元素填回
        while (i < leftLen) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < rightLen) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }

        // 顯示合併後的區間內容 (功能要求 4)
        System.out.printf("  ↳ 合併區間 [%d..%d] 後結果: %s%n", left, right, getSubArrayString(arr, left, right));
    }

    /**
     * 輔助方法：擷取指定區間的陣列字串表示
     */
    private static String getSubArrayString(int[] arr, int start, int end) {
        int[] temp = new int[end - start + 1];
        System.arraycopy(arr, start, temp, 0, temp.length);
        return Arrays.toString(temp);
    }
}
