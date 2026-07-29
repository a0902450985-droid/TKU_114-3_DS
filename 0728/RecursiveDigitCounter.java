public class RecursiveDigitCounter {

    // 遞迴計算 target 在 number 中出現的次數
    public static int countDigit(int number, int target) {
        // 處理負數情況（取絕對值）
        number = Math.abs(number);

        // 基本情況 (Base Case)：當數字小於 10 時（個位數）
        if (number < 10) {
            return (number == target) ? 1 : 0;
        }

        // 取出當前最後一位數字進行比對
        int lastDigit = number % 10;
        int currentMatch = (lastDigit == target) ? 1 : 0;

        // 遞迴步驟 (Recursive Step)：加上對剩餘數字 (number / 10) 的搜尋結果
        return currentMatch + countDigit(number / 10, target);
    }

    public static void main(String[] args) {
        System.out.println("=== 遞迴統計數字出現次數測試 ===");

        // 3. 示範 target 循環 0 到 9 的測試（使用測試陣列搭配 6 組數據）
        int[][] testCases = {
            {70701, 0},   // 測試 1：重複數字與多個 0 (target = 0)
            {111223, 1},  // 測試 2：連續重複數字 (target = 1)
            {55555, 5},   // 測試 3：全部相同的數字 (target = 5)
            {123456, 8},  // 測試 4：目標不存在 (target = 8)
            {0, 0},       // 測試 5：數字本身為 0 (target = 0)
            {987969, 9}   // 測試 6：多個目標分散在不同位置 (target = 9)
        };

        for (int i = 0; i < testCases.length; i++) {
            int num = testCases[i][0];
            int target = testCases[i][1];
            int result = countDigit(num, target);
            System.out.printf("測試 %d: 數字 %-8d 中，數字 %d 出現了 %d 次\n", 
                            i + 1, num, target, result);
        }

        System.out.println("\n--- 驗證 target 循環 0 到 9 (以數字 1020304050 為例) ---");
        int sampleNumber = 1020304050;
        for (int target = 0; target <= 9; target++) {
            System.out.printf("數字 %d 在 %d 中出現次數: %d\n", 
                            target, sampleNumber, countDigit(sampleNumber, target));
        }
    }
}