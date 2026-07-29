import java.util.Arrays;

public class SortingDebugReport {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("           課後作業五：排序計畫調試報告             ");
        System.out.println("==================================================\n");

        // 測試 Bug 1: 內層範圍錯誤
        testBug1();

        // 測試 Bug 2: key 未儲存
        testBug2();

        // 測試 Bug 3: 比較方向錯誤
        testBug3();
    }

    // =========================================================================
    // 1. 錯誤版本一：內層範圍錯誤 (Inner Loop Scope Error / ArrayIndexOutOfBounds)
    // =========================================================================

    /**
     * 【錯誤原因註解】
     * 內層 while 條件漏掉了 `j >= 0` 的檢查，導致當要插入的元素是目前最小的值時，
     * j 會一直遞減到 -1，存取 arr[j] 時引發 ArrayIndexOutOfBoundsException。
     */
    public static void bug1_InnerLoopScope(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            // 錯誤：沒有檢查 j >= 0，導致陣列越界
            while (arr[j] > key) { 
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 修正後方法 1
    public static void fix1_InnerLoopScope(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            // 正確：加上 j >= 0 邊界檢查
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void testBug1() {
        System.out.println("--- [測試 1: 內層範圍錯誤] ---");
        int[] testData = {5, 2, 8, 1, 3}; // 測試資料：含最小值，會觸發 j 減到 -1

        System.out.println("原始資料: " + Arrays.toString(testData));
        System.out.print("執行錯誤版本: ");
        try {
            int[] badCopy = Arrays.copyOf(testData, testData.length);
            bug1_InnerLoopScope(badCopy);
            System.out.println(Arrays.toString(badCopy));
        } catch (Exception e) {
            System.out.println("捕捉到例外狀況 -> " + e.toString());
        }

        int[] fixedCopy = Arrays.copyOf(testData, testData.length);
        fix1_InnerLoopScope(fixedCopy);
        System.out.println("修改後結果: " + Arrays.toString(fixedCopy));
        System.out.println();
    }

    // =========================================================================
    // 2. 錯誤版本二：key 未儲存 (Key Unsaved Error)
    // =========================================================================

    /**
     * 【錯誤原因註解】
     * 在開始位移元素前，沒有先用區域變數（int key = arr[i]）將當前元素備份起來。
     * 當第一步執行 `arr[j + 1] = arr[j]` 時，arr[i] 的值就會被前一個元素覆蓋，
     * 導致最後填回 `arr[j + 1] = arr[i]` 時填入的是被覆蓋後的新值，造成元素遺失與重複。
     */
    public static void bug2_KeyUnsaved(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 錯誤：沒有先備份 key = arr[i]
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[i]) { // 直接使用 arr[i]，其值會在過程中被覆蓋
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = arr[i]; // 填回已被覆蓋的值
        }
    }

    // 修正後方法 2
    public static void fix2_KeyUnsaved(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // 正確：先儲存當前元素為 key
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void testBug2() {
        System.out.println("--- [測試 2: key 未儲存錯誤] ---");
        int[] testData = {9, 4, 7, 2, 1};

        System.out.println("原始資料: " + Arrays.toString(testData));

        int[] badCopy = Arrays.copyOf(testData, testData.length);
        bug2_KeyUnsaved(badCopy);
        System.out.println("錯誤版本結果: " + Arrays.toString(badCopy) + " (資料被覆蓋覆寫)");

        int[] fixedCopy = Arrays.copyOf(testData, testData.length);
        fix2_KeyUnsaved(fixedCopy);
        System.out.println("修改後結果:   " + Arrays.toString(fixedCopy));
        System.out.println();
    }

    // =========================================================================
    // 3. 錯誤版本三：比較方向錯誤 (Wrong Comparison Direction Error)
    // =========================================================================

    /**
     * 【錯誤原因註解】
     * 欲進行升冪排序（小到大）時，比較符號誤寫為 `arr[j] < key`。
     * 這會導致當前者比 key 小時才進行後移，最終結果變成了降冪排序（大到小），
     * 不符合預期的升冪需求。
     */
    public static void bug3_WrongDirection(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            // 錯誤：欲升冪排序卻使用了 '<' 符號
            while (j >= 0 && arr[j] < key) { 
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 修正後方法 3
    public static void fix3_WrongDirection(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            // 正確：升冪排序使用 '>' 符號，將較大的元素往後移
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void testBug3() {
        System.out.println("--- [測試 3: 比較方向錯誤] ---");
        int[] testData = {3, 1, 4, 1, 5, 9, 2, 6};

        System.out.println("原始資料: " + Arrays.toString(testData));

        int[] badCopy = Arrays.copyOf(testData, testData.length);
        bug3_WrongDirection(badCopy);
        System.out.println("錯誤版本結果 (誤成降冪): " + Arrays.toString(badCopy));

        int[] fixedCopy = Arrays.copyOf(testData, testData.length);
        fix3_WrongDirection(fixedCopy);
        System.out.println("修改後結果   (正確升冪): " + Arrays.toString(fixedCopy));
        System.out.println();
    }
}
