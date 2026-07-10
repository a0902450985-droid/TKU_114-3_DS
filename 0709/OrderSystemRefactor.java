import java.util.Scanner;

public class OrderSystemRefactor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int totalItems = 0;
        int totalAmount = 0;

        while (option != 0) {
            printMenu();
            System.out.print("請輸入選項:");
            option = sc.nextInt();

            if (option == 0) {
                break;
            }
            
            int price = getPrice(option);
            if (price > 0) {
                int quantity = readValidQuantity(sc);
                if (quantity > 0) {
                    int subtotal = calculateSubtotal(price, quantity);
                
                    System.out.println("已加入購物車，小計：" + subtotal + " 元");
                    
                    totalItems += quantity;
                    totalAmount += subtotal;
                }
            } else {
            
                System.out.println("輸入錯誤，請重新選擇！");
            }
        } // while 結束
        
        printReceipt(totalItems, totalAmount);
        sc.close();
    }


    public static void printMenu(){
        System.out.println("\n===點餐系統===");
        System.out.println("1. 紅茶\t$30");
        System.out.println("2. 綠茶\t$35");
        System.out.println("3. 咖啡\t$50");
        System.out.println("0. 查看結帳");
    }
    
    public static int getPrice(int option){
        switch (option) {
            case 1:
                return 30;
            case 2:
                return 35;
            case 3:
                return 50;
            default:
                return 0;
        }
    }
    
    public static int readValidQuantity(Scanner sc){
        System.out.print("請輸入數量:");
        int qty = sc.nextInt();
        if (qty <= 0) {
            System.out.println("錯誤：數量必須大於 0 !本次點餐取消。");
            return -1;
        }
        return qty;
    }
    
    public static int calculateSubtotal(int price, int quantity){
         return price * quantity;
    }
    
    public static void printReceipt(int totalItems, int totalAmount){
        System.out.println("\n==== 結帳明細 ====");
        System.out.println("最後總數量：" + totalItems + " 杯");
        System.out.println("最後總金額：" + totalAmount + " 元");
        System.out.println("==================");
    }
}

