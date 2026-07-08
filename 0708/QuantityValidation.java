import java.util.Scanner;
public class QuantityValidation {
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入商品數量：");
        int product = sc.nextInt();

        while (product<0 || product==0) {
            System.out.print("數量錯誤，請重新輸入：");
            product = sc.nextInt();
        }

        System.out.println("商品數量: " + product);

        sc.close();
    }
}

