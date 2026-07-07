import java.util.Scanner;

import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        System.out.print("請輸入姓名: ");
        String name = sc.next();

        
        System.out.print("請輸入 Java 成績: ");
        double javaScore = sc.nextDouble();

        System.out.print("請輸入英文成績: ");
        double englishScore = sc.nextDouble();

        System.out.print("請輸入數學成績: ");
        double mathScore = sc.nextDouble();

        
        double average = (javaScore + englishScore + mathScore) / 3;

    
        String status = "";
        if (average >= 60) {
            status = "及格";
    }else {
        status = "不及格";
        };

        
        String grade = "";
        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println("--- 資料輸入完成 ---");

        
        int option = -1;

        while (option != 0) {
        
            System.out.println("====== 功能選單 ======");
            System.out.println("1 : 顯示平均份額");
            System.out.println("2 : 顯示及格狀態");
            System.out.println("3 : 顯示等第");
            System.out.println("0 : 離開");
            System.out.print("請選擇操作項目 (0-3): ");
            
            option = sc.nextInt();
            System.out.println("--------------------");

            
            switch (option) {
                case 1:
                    System.out.printf("%s 的三科平均分數為: %.2f\n", name, average);
                    break;
                case 2:
                    System.out.println(name + " 的及格狀態為: " + status);
                    break;
                case 3:
                    System.out.println(name + " 的成績等第為: " + grade);
                    break;
                case 0:
                    System.out.println("感謝使用，系統即將離開");
                    break;
                default:
                    System.out.println("輸入錯誤！請輸入 0 到 3 之間的數字。");
                    break;
            }
            System.out.println();
        }

        sc.close();
    }
}
