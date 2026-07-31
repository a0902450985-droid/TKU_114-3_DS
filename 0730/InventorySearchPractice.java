import java.util.Arrays;

public class InventorySearchPractice {

    public static void main(String[] args) {
        // 1. 建立至少 12 筆未排序庫存編號
        int[] inventory = {1058, 1012, 1003, 1099, 1025, 1041, 1018, 1087, 1033, 1066, 1001, 1075, 1050};

        System.out.println("=== 1. 排序前資料 ===");
        System.out.println("原始庫存列表: " + Arrays.toString(inventory));

        // 2. 使用 Merge Sort 依編號排序
        mergeSort(inventory, 0, inventory.length - 1);

        System.out.println("\n=== 2. 排序後資料 ===");
        System.out.println("已排序庫存列表: " + Arrays.toString(inventory));

        // 3. 測試搜尋（第一筆、最後一筆、中間值與不存在的編號）
        System.out.println("\n=== 3. 搜尋測試 (Binary Search) ===");
        
        int firstItem = inventory[0];                        // 第一筆: 1001
        int lastItem = inventory[inventory.length - 1];     // 最後一筆: 1099
        int middleItem = 1041;                              // 存在的邊界/中間值
        int notExistItem = 9999;                            // 不存在的編號

        searchAndPrint(inventory, firstItem, "測試第一筆資料");
        searchAndPrint(inventory, lastItem, "測試最後一筆資料");
        searchAndPrint(inventory, middleItem, "測試中間存在資料");
        searchAndPrint(inventory, notExistItem, "測試不存在資料");
    }

    /**
     * 二元搜尋法 (Binary Search)
     * 
     * @param arr 已排序的陣列
     * @param target 欲搜尋的庫存編號
     * @return 找到了回傳該元素的索引，若不存在則回傳 -1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // 找到目標，回傳索引
            } else if (arr[mid] < target) {
                left = mid + 1; // 目標在右半邊
            } else {
                right = mid - 1; // 目標在左半邊
            }
        }

        return -1; // 找不到該編號
    }

    /**
     * 執行搜尋並印出詳細結果的輔助方法
     */
    public static void searchAndPrint(int[] arr, int target, String testName) {
        int resultIndex = binarySearch(arr, target);
        System.out.printf("[%s] 搜尋編號 %d -> ", testName, target);
        
        if (resultIndex != -1) {
            System.out.println("找到資料！在排序後陣列的索引位置為: " + resultIndex);
        } else {
            System.out.println("未找到該庫存編號 (索引: -1)");
        }
    }

    /**
     * Merge Sort 遞迴拆分 (Divide)
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    /**
     * Merge Sort 合併處理 (Merge)
     */
    public static void merge(int[] arr, int left, int mid, int right) {
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
    }
}