import java.util.Scanner;

public class ScoreReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //請使用者輸入內容
        System.out.print("請輸入姓名:");
        String name =sc.next();
        System.out.print("請輸入Java成績:");
        int score1 = sc.nextInt();
        System.out.print("請輸入English成績:");
        int score2 = sc.nextInt();
        System.out.print("請輸入Math成績:");
        int score3 = sc.nextInt();
        double average = (score1+score2+score3)/3;
         //3科平均分數

        System.out.println("=== 成績報表 ===");
        System.out.println("姓名:"+name);
        System.out.println("Java:"+score1);
        System.out.println("English:"+score2);
        System.out.println("Math:"+score3);
        System.out.println("平均:"+average);

    }
}
