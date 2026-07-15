import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        /*1. 陣列越界錯誤 (執行期錯誤)位置：for (... i <= scores.length; ...)原因：索引最大僅到 length-1，使用 <= 會導致 ArrayIndexOutOfBoundsException。修正：將 <= 改為 <。
        2. 整數除法錯誤 (邏輯錯誤)位置：double average = total / scores.length;原因：兩變數皆為 int，直接相除會無條件捨去小數點。修正：改為 (double) total / scores.length。
        3. Scanner 換行殘留 (行為異常)位置：sc.nextInt() 後直接呼叫 sc.nextLine()原因：nextInt() 殘留換行符 \n 於緩衝區，導致後面的 nextLine() 被直接跳過。修正：在兩者之間插入一行 sc.nextLine(); 來清空緩衝區。
        4. 字串比較錯誤 (邏輯錯誤)位置：if (command == "exit")原因：== 比較的是記憶體位址，而非字串實際內容。修正：改用 command.equals("exit")。
        5. 格式化字串異常 (編譯錯誤)位置：printf 內的格式化字串原因：原文截圖中的格式字元或閉合引號輸入有誤。修正：修正為標準字串 "平均: %.2f\n"。
        斷點偵測重要變數值 (Breakpoint Log)異常中斷時（i = 3 崩潰）：total = 247 ($80 + 75 + 92$)i = 3 （超出 scores 陣列最大索引 2）修正後正常執行時：total = 247scores.length = 3average = 82.33
        /*

 */
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        // 修正 1：將 <= 改為 <，避免陣列越界
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        // 修正 2：強轉 (double) 避免整數除法遺失小數點；修正 5：確保格式化字串正確
        double average = (double) total / scores.length;
        System.out.printf("平均: %.2f\n", average);

        System.out.print("請輸入年齡: ");
        int age = sc.nextInt();
        
        // 修正 3：吃掉 nextInt() 殘留在緩衝區的換行符號，防止下行直接被跳過
        sc.nextLine(); 

        System.out.print("請輸入指令: ");
        String command = sc.nextLine();

        // 修正 4：字串比較必須使用 .equals()，不可使用 ==
        if (command.equals("exit")) {
            System.out.println("系統結束，年齡: " + age);
        }

        sc.close();
    }
}
