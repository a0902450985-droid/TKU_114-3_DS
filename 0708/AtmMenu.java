import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int startblance = 1000;
        int option = -1;

        while (option!=0) {
            System.out.println("====功能選單====");
            System.out.println("1.查詢餘額");
            System.out.println("2.存款");
            System.out.println("3.提款");
            System.out.println("0.離開");
            System.out.print("請輸入選項:");
            option= sc.nextInt();

        switch (option){
            case 1:
                System.out.println("現在餘額為:"+startblance+"元");
                break;
            case 2:
                System.out.print("請輸入存款金額:");
                int Cash = sc.nextInt();

                if (Cash>0) {
                    startblance +=Cash;
                    System.out.println("已存入金額"+Cash+"元"+" "+"帳戶總餘額為:"+startblance+"元");
                    
                }else {
                    System.out.println("存入金額必須大於0!"); 
                }
                
                break;
            case 3:
                System.out.print("請輸入提款金額:");
                int money = sc.nextInt();
                if (money<0) {
                    System.out.println("提款金額不可為0!");
                }
                else if(money>startblance){
                    System.out.println("提款金額不可超過帳戶餘額!");
                }
                else{
                    startblance -= money;
                    System.out.println("已領取金額"+money+"元"+" "+"目前帳戶餘額為:"+startblance+"元");
                }
                break;
            case 0:
                System.out.println("操作結束!");
                break;

            default:
                System.out.println("選項錯誤! 無法識別");
                break;
            }
            
        }
        System.out.println("謝謝您的光臨 期待再次使用!");
        sc.close();
    }
}

