import java.util.Scanner;

public class AtmMethodHomework {

    
    public static void printMenu() {
        System.out.println("\n=== ATM 系統選單 ===");
        System.out.println("1. 查詢餘額");
        System.out.println("2. 存款");
        System.out.println("3. 提款");
        System.out.println("0. 離開系統");
        System.out.print("請選擇操作項目: ");
    }

    
    public static int readPositiveAmount(Scanner sc, String message) {
        int amount;
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                amount = sc.nextInt();
                if (amount > 0) {
                    return amount;
                } else {
                    System.out.println("【錯誤】金額必須大於 0，請重新輸入！");
                }
            } else {
                System.out.println("【錯誤】請輸入有效的數字！");
                sc.next();
        }
    }
    }

    
    public static int deposit(int balance, int amount) {
        balance += amount;
        System.out.println("【成功】已成功存款: $" + amount);
        return balance;
    }


    public static int withdraw(int balance, int amount) {
        if (amount > balance) {
            System.out.println("【失敗】提款失敗，目前餘額不足！");
        } else {
            balance -= amount;
            System.out.println("【成功】已成功提款: $" + amount);
        }
        return balance;
    }

    
    public static void printBalance(int balance) {
        System.out.println("--------------------");
        System.out.println("您目前的帳戶餘額為: $" + balance);
        System.out.println("--------------------");
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        
        int balance = 1000; 
        int choice;

        
        do {
            printMenu();
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                
                switch (choice) {
                    case 1:
                        // 查詢餘額
                        printBalance(balance);
                        break;
                    case 2:
                        
                        int depAmount = readPositiveAmount(sc, "請輸入要存款的金額: ");
                        balance = deposit(balance, depAmount);
                        break;
                    case 3:
                        
                        int witAmount = readPositiveAmount(sc, "請輸入要提款的金額: ");
                        balance = withdraw(balance, witAmount);
                        break;
                    case 0:
                        System.out.println("感謝您的使用，再見！");
                        break;
                    default:
                        System.out.println("【錯誤】無此選項，請輸入 0 ~ 3 的數字。");
                }
            } else {
                System.out.println("【錯誤】請輸入正確的選項數字！");
                sc.next();
                choice = -1;
            }
        } while (choice != 0);

        sc.close();
    }
}
