public class RecursiveDigitSumPractice {

    // 遞迴計算各個數字總和的方法
    public static int digitSum(int number) {
        // 基本情況（Base Case）：如果數字小於 10（包含 0~9），直接回傳該數字
        if (number < 10) {
            return number;
        }
        
        // 遞迴步驟（Recursive Step）：
        // number % 10 取出最後一個位數，加上對剩餘數字（number / 10）的遞迴呼叫
        return (number % 10) + digitSum(number / 10);
    }

    public static void main(String[] args) {
        // 測試 5 組數據
        int[] testCases = {5729, 0, 8, 12345, 999};

        System.out.println("=== 遞迴各人數總和測試結果 ===");
        for (int test : testCases) {
            System.out.println("digitSum(" + test + ") = " + digitSum(test));
        }
    }
}
