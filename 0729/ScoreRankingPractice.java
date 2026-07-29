public class ScoreRankingPractice {

    public static void main(String[] args) {
        // 1. 使用至少 8 筆成績（包含重複分數以驗證相同名次邏輯）
        int[] scores = {85, 92, 78, 92, 55, 60, 45, 85, 100};

        System.out.println("原始成績數據：");
        printArray(scores);

        // 2. 使用 Selection Sort 進行降冪排序（高到低）
        selectionSortDescending(scores);

        // 3. 顯示名次、分數及是否及格 (4. 相同分數同名次)
        displayRankings(scores);
    }

    /**
     * 選擇排序法（降冪：由高到低）
     * 不使用 Arrays.sort()
     */
    public static void selectionSortDescending(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            // 尋找剩餘元素中的最大值索引
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            // 將找到的最大值與目前位置交換
            if (maxIndex != i) {
                int temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
        }
    }

    /**
     * 計算名次並列印結果
     */
    public static void displayRankings(int[] scores) {
        System.out.println("\n--- 成績降冪排名結果 ---");
        System.out.printf("%-6s %-8s %-6s%n", "名次", "分數", "是否及格");
        System.out.println("------------------------");

        int currentRank = 1;

        for (int i = 0; i < scores.length; i++) {
            // 處理相同分數同名次邏輯：
            // 如果與前一位分數不同，名次更新為當前位置 (i + 1)
            // 如果與前一位分數相同，保持 currentRank 不變
            if (i > 0 && scores[i] != scores[i - 1]) {
                currentRank = i + 1;
            }

            boolean isPass = scores[i] >= 60;
            String passStatus = isPass ? "及格" : "不及格";

            System.out.printf("第 %-3d 名 | %-6d分 | %s%n", currentRank, scores[i], passStatus);
        }
    }

    public static void printArray(int[] arr) {
        for (int score : arr) {
            System.out.print(score + " ");
        }
        System.out.println();
    }
}
