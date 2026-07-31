import java.util.Arrays;

public class MergeArrayPractice {

    public static void main(String[] args) {
        System.out.println("=== 測試 1: 兩長度不同陣列（含負數與重複值） ===");
        int[] arr1 = {-5, -2, 1, 3, 5, 5, 8};
        int[] arr2 = {-2, 0, 3, 4, 9, 12, 15};
        
        int[] result1 = mergeAndRemoveDuplicates(arr1, arr2);
        printArray("結果 1", result1);

        System.out.println("\n=== 測試 2: 其中一個陣列為空 ===");
        int[] arr3 = {};
        int[] arr4 = {-3, 1, 1, 4, 7};
        
        int[] result2 = mergeAndRemoveDuplicates(arr3, arr4);
        printArray("結果 2", result2);
    }

    /**
     * 合併兩個已排序陣列並去除重複項目
     * 
     * @param arr1 已排序陣列 1
     * @param arr2 已排序陣列 2
     * @return 合併且去重後的已排序陣列
     */
    public static int[] mergeAndRemoveDuplicates(int[] arr1, int[] arr2) {
        // 定義三個索引：i 用於 arr1、j 用於 arr2、k 用於 mergedTemp
        int i = 0;
        int j = 0;
        int k = 0;

        // 建立暫存陣列，最大可能長度為兩陣列長度之和
        int[] mergedTemp = new int[arr1.length + arr2.length];

        // 1. 使用三個索引進行雙指標合併
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                k = addUnique(mergedTemp, k, arr1[i]);
                i++;
            } else {
                k = addUnique(mergedTemp, k, arr2[j]);
                j++;
            }
        }

        // 2. 將 arr1 剩餘的元素放入
        while (i < arr1.length) {
            k = addUnique(mergedTemp, k, arr1[i]);
            i++;
        }

        // 3. 將 arr2 剩餘的元素放入
        while (j < arr2.length) {
            k = addUnique(mergedTemp, k, arr2[j]);
            j++;
        }

        // 4. 將有效長度 k 的結果複製至最終陣列（裁切掉去重後的空白位置）
        int[] result = new int[k];
        for (int index = 0; index < k; index++) {
            result[index] = mergedTemp[index];
        }

        return result;
    }

    /**
     * 輔助方法：僅在值與前一個放入的元素不同時才加入陣列（實現去重）
     */
    private static int addUnique(int[] arr, int count, int value) {
        if (count == 0 || arr[count - 1] != value) {
            arr[count] = value;
            return count + 1; // 回傳更新後的有效長度
        }
        return count; // 若重複則不加入，長度不變
    }

    /**
     * 印出陣列內容的輔助方法
     */
    private static void printArray(String label, int[] arr) {
        System.out.print(label + ": [ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
        }
        System.out.println(" ] (長度: " + arr.length + ")");
    }
}