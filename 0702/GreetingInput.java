import java.util.Scanner;

public class GreetingInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = sc.nextLine();
        
        System.out.print("請輸入年齡：");
        int  age = sc.nextInt();

        System.out.println("Hello, " + name+",年齡:"+age+"歲");

        sc.close();
    }
}

