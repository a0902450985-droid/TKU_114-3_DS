import java.util.Arrays;
import java.util.Scanner;

public class RangeSearchSystem {

    public static void main(String[] args) {
        // 1. 使用包含重複資料的已排序清單（例如：成績或記分清單）
        int[] scores = {50, 60, 70, 70, 70, 85, 85, 90, 95, 100};

        System.out.println("=== 記分/成績清單（已排序且包含重複資料）===");
        System.out.println(Arrays.toString(scores));
        System.out.println("----------------------------------------------\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的目標分數：");
        int target = scanner.nextInt();

        // 呼叫範圍搜尋方法，取得 [firstIndex, lastIndex]
        int[] range = searchRange(scores, target);

        // 4. 輸出目標值的索引範圍及出現次數 / 5. 找不到時顯示 [-1, -1]
        System.out.println("\n=== 搜尋結果 ===");
        System.out.println("索引範圍結果：[" + range[0] + ", " + range[1] + "]");

        if (range[0] != -1) {
            int count = range[1] - range[0] + 1; // 出現次數計算公式：最後索引 - 第一個索引 + 1
            System.out.println("【搜尋成功】");
            System.out.println("目標值 " + target + " 第一次出現在索引：" + range[0]);
            System.out.println("目標值 " + target + " 最後一次出現在索引：" + range[1]);
            System.out.println("總共出現次數：" + count + " 次");
        } else {
            System.out.println("【搜尋失敗】查無目標值 " + target);
            System.out.println("總共出現次數：0 次");
        }

        scanner.close();
    }

    /**
     * 搜尋目標值的索引範圍 [First, Last]
     */
    public static int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);   // 2. 尋找第一次出現的位置
        
        // 如果連第一次出現的位置都找不到，直接回傳 [-1, -1]
        if (first == -1) {
            return new int[]{-1, -1};
        }

        int last = findBound(nums, target, false);  // 3. 重新尋找最後一次出現的位置

        return new int[]{first, last};
    }

    /**
     * 修改後的二分查找（變形 Binary Search）
     * @param isFirst true 代表尋找第一次出現位置（First Bound）；false 代表尋找最後一次出現位置（Last Bound）
     */
    public static int findBound(int[] nums, int target, boolean isFirst) {
        int low = 0;
        int high = nums.length - 1;
        int boundIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                boundIndex = mid; // 記錄當前找到的索引
                
                if (isFirst) {
                    high = mid - 1; // 繼續往左半邊縮小範圍，找更早出現的位置
                } else {
                    low = mid + 1;  // 繼續往右半邊縮小範圍，找更晚出現的位置
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return boundIndex;
    }
}