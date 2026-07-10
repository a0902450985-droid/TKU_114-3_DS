import java.util.Scanner;

public class OrderSystemRefactor_demo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        int totalBill = 0;

        System.out.println("=== 歡迎使用點餐系統 ===");

        
        while (running) {
        
            displayMenu();
            
            System.out.print("請選擇功能或商品 (1-3 商品, 4 結帳, 0 離開): ");
        
            int choice = getValidInput(scanner, 0, 4);

            if (choice == 0) {
                System.out.println("感謝您的光臨，程式已結束。");
                running = false;
            } else if (choice == 4) {
            
                printReceipt(totalBill);
                running = false;
            } else {
                
                int price = getItemPrice(choice);
                
                System.out.print("請輸入購買數量 (1-100): ");
                int quantity = getValidInput(scanner, 1, 100);

            
                int subtotal = calculateSubtotal(price, quantity);
                totalBill += subtotal;

                System.out.println("已加入購物車！本次小計: $" + subtotal + " 元\n");
            }
        }
        scanner.close();
    }

    public static void displayMenu() {
        System.out.println("------------------------");
        System.out.println("1. 美式咖啡   $60");
        System.out.println("2. 熱拿鐵     $75");
        System.out.println("3. 起司蛋糕   $90");
        System.out.println("4. 進入結帳");
        System.out.println("0. 離開系統");
        System.out.println("------------------------");
    }

    public static int getItemPrice(int choice) {
        int price = 0;
        switch (choice) {
            case 1:
                price = 60;
                break;
            case 2:
                price = 75;
                break;
            case 3:
                price = 90;
                break;
            default:
                price = 0;
                break;
        }
        return price;
    }

    
    public static int getValidInput(Scanner scanner, int min, int max) {
        int input;
        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input; 
                }
            } else {
                scanner.next(); 
            }
            System.out.print("輸入錯誤！請輸入範圍內字元 (" + min + "-" + max + "): ");
        }
    }

    
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static void printReceipt(int total) {
        System.out.println("\n============ 收據 ============");
        System.out.println(" 總計消費金額: $" + total + " 元");
        System.out.println(" 謝謝惠顧，歡迎下次光臨！");
        System.out.println("==============================");
    }
}