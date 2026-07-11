import java.util.Scanner;

public class OrderSystem_demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int totalQuantity =0;
        int totalAmount =0;

        while (option!=0) {
            System.out.println("=== 顯示選單 ===");
        System.out.println("1.紅茶\t30");
        System.out.println("2.綠茶\t35");
        System.out.println("3.咖啡\t50");
        System.out.println("0.查看\t結帳");

        System.out.print("請選擇選項:");
        option  = sc.nextInt();

        String itemname = "";
        int price =0;
        
                switch (option) {
                    case 1:
                        price =30;
                        itemname ="紅茶";break;
                    case 2:
                        price =35;
                        itemname = "綠茶";break;
                    case 3:
                        price =50;
                        itemname ="咖啡";break;
                    case 0:
                        break;
                
                    default:
                        System.out.println("輸入錯誤,重新輸入");
                        continue;}
                if (option==1||option==2||option==3) {
                    System.out.print("輸入數量:");
                    int quantity = sc.nextInt();
                    if (quantity<0) {
                        System.out.println("數量錯誤,請重新輸入:");
                        quantity =sc.nextInt();
                    }
                    else{
                        int subtotal = price*quantity;
                        System.out.println(""+itemname+"x"+quantity+",小計:"+subtotal+"元");
                        totalQuantity += quantity;
                        totalAmount += subtotal;

                    }
                
                
                }
        }
        System.out.println();
        System.out.println("點餐結束！您的結帳明細：");
        System.out.println("最後總數量：" +totalQuantity+" 杯");
        System.out.println("最後總金額：" + totalAmount + " 元");
        System.out.println("=================");
        sc.close();
        }


    }

