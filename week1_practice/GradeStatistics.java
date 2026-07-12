import java.util.Scanner;

public class GradeStatistics {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        int sum = 0;
        int count = 0;
        int highest =-1;
        int lowest = 101;
        int passCount = 0;
        int failCount = 0;

        System.out.println("請開始輸入成績（輸入 -1 結束）：");

        while (true) {
            System.out.print("請輸入成績: ");
            int score = scanner.nextInt();

            
            if (score == -1) {
                break;
            }

            
            if (!isValid(score)) {
                System.out.println("錯誤：成績必須在 0 到 100 之間，請重新輸入。");
                continue;
            }

            
            sum += score;
            count++;

            if (score > highest) {
                highest = score;
            }
            if (score < lowest) {
                lowest = score;
            }

            if (score >= 60) {
                passCount++;
            } else {
                failCount++;
            }
        }

        
        if (count == 0) {
            System.out.println("No scores entered.");
        } else {
            
            double average = (double) sum / count;
            String grade = getGrade(average);

            
            System.out.println("\n============= 成績統計報表 =============");
            System.out.println("總分: " + sum);
            System.out.println("總人數 (計數): " + count);
            System.out.printf("平均分數: %.2f\n", average);
            System.out.println("最高分: " + highest);
            System.out.println("最低分: " + lowest);
            System.out.println("及格人數: " + passCount);
            System.out.println("不及格人數: " + failCount);
            System.out.println("平均分數等第: " + grade);
            System.out.println("=======================================");
        }

        scanner.close();
    }

    
    
    public static boolean isValid(int score) {
        return score >= 0 && score <= 100;
    }

    
    public static String getGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}