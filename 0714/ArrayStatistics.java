import java.util.Scanner;

public class ArrayStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 輸入資料筆數 (1~50)
        int count = readCount(sc);

        // 2. 建立對應長度的 int[] 陣列
        int[] scores = new int[count];

        // 3. 逐筆輸入 0 到 100 的成績 (含防錯防呆)
        inputScores(sc, scores);

        System.out.println("\n--- 統計結果 ---");

        // 4. 顯示全部成績
        System.out.print("全部成績為: ");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();

        // 5. 顯示總分、平均、最高分、最低分
        int total = calculateTotal(scores);
        double average = (double) total / scores.length;
        int max = findMax(scores);
        int min = findMin(scores);

        System.out.println("總分: " + total);
        // 使用 %.2f 格式化輸出平均值到小數點後兩位
        System.out.printf("平均分數: %.2f\n", average);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);

        // 6. 顯示及格與不及格人數
        int passCount = countPass(scores);
        int failCount = scores.length - passCount;
        System.out.println("及格人數: " + passCount);
        System.out.println("不及格人數: " + failCount);

        // 7. 輸入一個目標成績並顯示第一次出現的索引
        System.out.print("\n請輸入要搜尋的目標成績: ");
        int target = sc.nextInt();
        int index = findIndex(scores, target);

        if (index != -1) {
            System.out.println("目標成績 " + target + " 第一次出現的索引值(Index)為: " + index);
        } else {
            System.out.println("找不到目標成績 " + target + " 於陣列中。");
        }

        sc.close();
    }

    /**
     * 1. 輸入資料筆數，範圍限制在 1 到 50 之間（防呆）
     */
    public static int readCount(Scanner sc) {
        int count;
        while (true) {
            System.out.print("請輸入資料筆數 (1~50): ");
            if (sc.hasNextInt()) {
                count = sc.nextInt();
                if (count >= 1 && count <= 50) {
                    break;
                }
            } else {
                sc.next(); // 消耗掉非整數的輸入，避免死循環
            }
            System.out.println("輸入錯誤，筆數必須在 1 到 50 之間！");
        }
        return count;
    }

    /**
     * 3. 逐筆輸入 0 到 100 的成績，若輸入錯誤需重新輸入
     */
    public static void inputScores(Scanner sc, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                System.out.print("請輸入第 " + (i + 1) + " 筆成績 (0~100): ");
                if (sc.hasNextInt()) {
                    int tempScore = sc.nextInt();
                    if (tempScore >= 0 && tempScore <= 100) {
                        scores[i] = tempScore;
                        break; // 成功取得合法成績，跳出當前 while 迴圈
                    }
                } else {
                    sc.next(); // 消耗掉非整數的輸入
                }
                System.out.println("輸入錯誤，成績範圍必須是 0 到 100！");
            }
        }
    }

    /**
     * 5a. 計算總分
     */
    public static int calculateTotal(int[] scores) {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum;
    }

    /**
     * 5b. 尋找最高分
     */
    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    /**
     * 5c. 尋找最低分
     */
    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        return min;
    }

    /**
     * 6. 計算及格人數（60分及以上）
     */
    public static int countPass(int[] scores) {
        int count = 0;
        for (int score : scores) {
            if (score >= 60) {
                count++;
            }
        }
        return count;
    }

    /**
     * 7. 搜尋目標成績第一次出現的索引，找不到則回傳 -1
     */
    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i; // 找到即刻回傳當前索引
            }
        }
        return -1; // 迴圈跑完都沒找到，回傳 -1
    }
}