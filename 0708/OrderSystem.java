import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int totalnumber = 0;
        int totalAmount =0;

        while (option != 0) {
        System.out.println("=== 顯示選單 ===");
        System.out.println("1.紅茶\t30");
        System.out.println("2.綠茶\t35");
        System.out.println("3.咖啡\t50");
        System.out.println("0.查看\t結帳");

        System.out.print("請輸入商品選項：");
        option = sc.nextInt();

        int price =0;
        String itemname ="";

        switch (option) {
        case 1:
        price =30;
        itemname ="紅茶";
        break;
        case 2:
        price =35;
        itemname = "綠茶";
        break;
        case 3:
        price =50;
        itemname ="咖啡";
        break;
        case 0:
        break;
        default:
        System.out.println("輸入錯誤 請重新輸入:");
        continue;
        }
    
        if (option==1||option==2||option==3) {
                System.out.print("請輸入數量:");
                int quantity =sc.nextInt();
                if (quantity<0) {
                    System.out.println("錯誤 數量必須大於0 點餐取消。");
                }else{
                    int subtotal = price * quantity;
                    System.out.println(""+itemname+"x"+quantity+",小計:"+subtotal+"元");
                    totalnumber += quantity;
                    totalAmount += subtotal;
                }
            }
        }
                System.out.println("\n=================");
        // 7. 選擇 0 時產出總數量與總金額
        System.out.println("點餐結束！您的結帳明細：");
        System.out.println("最後總數量：" +totalnumber+" 杯");
        System.out.println("最後總金額：" + totalAmount + " 元");
        System.out.println("=================");
        sc.close();
}
}