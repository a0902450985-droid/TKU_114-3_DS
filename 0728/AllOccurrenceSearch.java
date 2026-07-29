import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllOccurrenceSearch {

    public static void main(String[] args) {
        // 1. 在未排序整數中搜尋指定分數（範例：8 位學生的成績）
        int[] scores = {85, 70, 92, 85, 60, 85, 78, 90};

        // 顯示原始資料
        System.out.print("未排序分數清單：[ ");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + (i < scores.length - 1 ? ", " : " "));
        }
        System.out.println("]\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的目標分數：");
        int targetScore = scanner.nextInt();

        // 用於儲存符合條件的索引列表
        List<Integer> foundIndices = new ArrayList<>();
        int compareCount = 0; // 記錄比較次數

        // 5. 不使用 Java 內建搜尋方法，手動進行全項掃描 (Linear Search)
        for (int i = 0; i < scores.length; i++) {
            compareCount++; // 每次進行比對，次數 +1
            
            // 找到符合的分數
            if (scores[i] == targetScore) {
                foundIndices.add(i); // 記錄索引，不提前 break，繼續搜完所有資料
            }
        }

        System.out.println("\n=== 搜尋結果 ===");

        // 2. 輸出所有符合的索引 & 3. 顯示出現次數及比較次數 & 4. 缺乏時顯示明確訊息
        if (!foundIndices.isEmpty()) {
            System.out.println("【搜尋成功】");
            System.out.println("目標分數 " + targetScore + " 出現在以下索引位置：");
            System.out.print("索引清單：");
            for (int index : foundIndices) {
                System.out.print("[" + index + "] ");
            }
            System.out.println();
            System.out.println("出現總次數： " + foundIndices.size() + " 次");
        } else {
            System.out.println("【搜尋失敗】");
            System.out.println("找不到分數為 " + targetScore + " 的資料。");
            System.out.println("出現總次數： 0 次");
        }

        System.out.println("實際總比較次數： " + compareCount + " 次");

        scanner.close();
    }
}
