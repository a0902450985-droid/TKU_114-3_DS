import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int totalItems =0;
        int totalAmount =0;

        while (option !=0) {
            printMenu();
            System.out.print("請輸入選項:");
            option = sc.nextInt();

            int price = getPrice(option);

            if (option>=1&&option<=4) {
                System.out.print("請輸入數量:");
                int quantity = sc.nextInt();
                if (quantity>0) {
                    int subtotal = calulatesSubtal(price, quantity);
                    System.out.println("已購入購物車,小計"+subtotal+"元");
                    totalItems += quantity;
                    totalAmount += subtotal;
                    
                    }
                else  {
                    System.out.println("數量必須大於0!");
                }
            }
            else if (option!=0) {
                System.out.println("輸入錯誤，請重新選擇！");
                }
            }
            
        
            printReceipt(totalItems, totalAmount);
        sc.close();
        
        }
    public static void printMenu(){
        System.out.println("====顯示清單===");
        System.out.println("1.紅茶\t30");
        System.out.println("2.綠茶\t35");
        System.out.println("3.奶茶\t45");
        System.out.println("4.咖啡\t50");
        System.out.println("0.離開");
    }
    public static int getPrice(int option){
        switch (option) {
            case 1:
                return 30;
            case 2:
                return 35;
            case 3:
                return 45;
            case 4:
                return 50;
            default:
                return 0;
        }
    }
    public static int calulatesSubtal(int price,int quantity){
        return price*quantity;
    }

    public static void printReceipt(int totalItems, int totalAmount){
        System.out.println("\n==== 結帳明細 ====");
        System.out.println("最後總數量：" + totalItems + " 杯");
        System.out.println("最後總金額：" + totalAmount + " 元");
        System.out.println("================");
    }
}
