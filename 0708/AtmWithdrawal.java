import java.util.Scanner;

public class AtmWithdrawal {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int Cardpassword = 996733;//卡片正確密碼
    int Balance = 50000;//卡片餘額
    int limitCash =30000;//提款上限
    int i =0;
    Boolean PasswordCorrect =false;
    while (i<3) {
    System.out.print("請輸入提款密碼:");//提款帳號的密碼
    int password =sc.nextInt();
        if (password==Cardpassword) {
            PasswordCorrect= true;
            break;
        }else{
            i++;
            System.out.println("交易錯誤 ，剩餘輸入次數還有"+(3-i)+"次");

        }
    }
        System.out.println("==========");
        if (!PasswordCorrect) {
            System.out.println("交易失敗 卡片輸入3次 已鎖定");//輸入次數太多遭鎖定
        }
        else{
            System.out.print("請輸入提款金額:");
            int Cash = sc.nextInt();
            if (Cash>limitCash) {
                System.out.println("輸入金額超過每日上限金額!");//每日上限
            }else if (Cash>Balance) {
                System.out.println("領取金額超過卡片餘額!");//超過餘額
            }else{
                Balance = Balance-Cash;
        System.out.println("交易成功，請取出您的鈔票!");
        System.out.println("本次提款金額:"+Cash+"元");
        System.out.println("卡片餘額剩餘:"+Balance+"元");}
    }
    System.out.println("感謝您的使用，期待再次為你服務");

    sc.close();}}
        
    

