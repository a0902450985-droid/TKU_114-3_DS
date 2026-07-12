import java.util.Scanner;

public class AtmSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 初始狀態設定
        int balance = 1000;       // 初始餘額 1000 元
        int depositCount = 0;     // 成功存款次數
        int withdrawCount = 0;    // 成功提款次數
        int totalDeposit = 0;     // 總存款金額
        int totalWithdraw = 0;    // 總提款金額

        while (true) {
            // 1. 可重複操作選單
            printMenu();
            System.out.print("請選擇功能選項 (0-4): ");
            int option = sc.nextInt();

            // 0. 離開系統並輸出完整摘要
            if (option == 0) {
                printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
                break;
            }

            switch (option) {
                case 1:
                    // 查詢餘額
                    printBalance(balance);
                    break;

                case 2:
                    // 存款項目
                    int depAmount = readPositiveAmount(sc, "請輸入存款金額 (必須大於 0): ");
                    
                    // 更新餘額與統計數據
                    balance = deposit(balance, depAmount);
                    totalDeposit += depAmount;
                    depositCount++;
                    System.out.println("存款成功！已存入 " + depAmount + " 元。\n");
                    break;

                case 3:
                    // 提款項目
                    int withAmount = readPositiveAmount(sc, "請輸入提款金額 (必須大於 0): ");
                    
                    // 檢查餘額是否足夠
                    if (canWithdraw(balance, withAmount)) {
                        balance = withdraw(balance, withAmount);
                        totalWithdraw += withAmount;
                        withdrawCount++;
                        System.out.println("提款成功！已領取 " + withAmount + " 元。\n");
                    } else {
                        System.out.println("交易失敗：提款金額不能超過目前餘額！\n");
                    }
                    break;

                case 4:
                    // 顯示目前操作統計 (未離開前的即時統計)
                    System.out.println("\n--- 目前操作統計 ---");
                    System.out.println("成功存款次數: " + depositCount + " 次，總存入: " + totalDeposit + " 元");
                    System.out.println("成功提款次數: " + withdrawCount + " 次，總領出: " + totalWithdraw + " 元");
                    System.out.println("--------------------\n");
                    break;

                default:
                    System.out.println("無效選項，請重新選擇！\n");
                    break;
            }
        }
        sc.close();
    }

    // ==================== Method 要求實作區 ====================

    /**
     * 顯示選單
     */
    public static void printMenu() {
        System.out.println("====== ATM 帳戶操作系統 ======");
        System.out.println("1. 查詢餘額");
        System.out.println("2. 存款");
        System.out.println("3. 提款");
        System.out.println("4. 顯示目前操作統計");
        System.out.println("0. 離開");
        System.out.println("=============================");
    }

    /**
     * 讀取並驗證必須大於 0 的金額（存款與提款共用此驗證邏輯）
     * 透過傳入的 message 來彈性顯示提示字眼
     */
    public static int readPositiveAmount(Scanner sc, String message) {
        int amount;
        while (true) {
            System.out.print(message);
            amount = sc.nextInt();
            if (amount > 0) {
                return amount;
            }
            System.out.println("錯誤：金額必須大於 0，請重新輸入。");
        }
    }

    /**
     * 執行存款，回傳更新後的餘額
     */
    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    /**
     * 執行提款，回傳更新後的餘額
     */
    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    /**
     * 檢查提款金額是否小於或等於目前餘額
     */
    public static boolean canWithdraw(int balance, int amount) {
        return amount <= balance;
    }

    /**
     * 印出當前餘額
     */
    public static void printBalance(int balance) {
        System.out.println("\n[帳戶餘額] 目前可用餘額為: " + balance + " 元\n");
    }

    /**
     * 離開時輸出完整摘要報表
     */
    public static void printSummary(int balance, int depositCount, int withdrawCount, int totalDeposit, int totalWithdraw) {
        System.out.println("\n============= 感謝使用！操作完整摘要 =============");
        System.out.println("成功存款次數  : " + depositCount + " 次");
        System.out.println("總存款金額    : " + totalDeposit + " 元");
        System.out.println("成功提款次數  : " + withdrawCount + " 次");
        System.out.println("總提款金額    : " + totalWithdraw + " 元");
        System.out.println("----------------------------------------------");
        System.out.println("最終帳戶餘額  : " + balance + " 元");
        System.out.println("=================================================");
    }
}