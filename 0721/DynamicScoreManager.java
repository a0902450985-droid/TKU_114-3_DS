import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DynamicScoreManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> scores = new ArrayList<>();

        System.out.println("=== 課堂成績動態管理系統 ===");
        System.out.println("請輸入成績（0-100），輸入 -1 結束輸入：");

        // 1. 讀取輸入與防呆處理
        while (true) {
            System.out.print("請輸入成績: ");
            if (!scanner.hasNextInt()) {
                System.out.println("【錯誤】請輸入有效的整數！");
                scanner.next(); // 清除無效輸入
                continue;
            }

            int score = scanner.nextInt();

            if (score == -1) {
                break; // 遇到 -1 結束輸入
            }

            if (score < 0 || score > 100) {
                System.out.println("【錯誤】成績範圍必須在 0 到 100 之間！");
            } else {
                scores.add(score);
            }
        }

        // 2. 輸出結果與呼叫各獨立 Method
        System.out.println("\n========== 統計結果 ==========");
        int count = scores.size();
        System.out.println("總筆數: " + count);

        if (count == 0) {
            System.out.println("沒有輸入任何有效的成績。");
        } else {
            System.out.printf("平均分數: %.2f\n", calculateAverage(scores));
            System.out.println("最高分: " + findMax(scores));
            System.out.println("最低分: " + findMin(scores));
            System.out.println("及格名單 (>=60): " + getPassingScores(scores));
        }

        scanner.close();
    }

    // ================= 統計與篩選 Method 區 =================

    /**
     * 計算平均分數
     */
    public static double calculateAverage(List<Integer> scores) {
        if (scores.isEmpty()) return 0.0;
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }

    /**
     * 尋找最高分
     */
    public static int findMax(List<Integer> scores) {
        int max = scores.get(0);
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    /**
     * 尋找最低分
     */
    public static int findMin(List<Integer> scores) {
        int min = scores.get(0);
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }

    /**
     * 篩選出及格的成績名單 (>= 60)
     */
    public static List<Integer> getPassingScores(List<Integer> scores) {
        List<Integer> passingScores = new ArrayList<>();
        for (int score : scores) {
            if (score >= 60) {
                passingScores.add(score);
            }
        }
        return passingScores;
    }
}