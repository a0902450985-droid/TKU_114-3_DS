import java.util.ArrayDeque;
import java.util.Deque;

public class BracketValidationSystem {

    /**
     * 主驗證邏輯：判斷字串中的括號是否合法對應
     * 支援：小括號 (), 中括號 [], 大括號 {}
     */
    public static boolean isValid(String input) {
        if (input == null) {
            return true;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : input.toCharArray()) {
            // 1. 如果是左括號，壓入 Stack
            if (isLeftBracket(ch)) {
                stack.push(ch);
            } 
            // 2. 如果是右括號，進行比對
            else if (isRightBracket(ch)) {
                // 空結構操作安全：出現右括號但 Stack 是空的 -> 缺少左括號
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();
                // 檢查括號類型是否配對 -> 順序/類型錯誤
                if (!isMatchingPair(top, ch)) {
                    return false;
                }
            }
            // 3. 忽略非括號字元（字母、數字、空白等）
        }

        // 結束後 Stack 必須為空，若不為空代表 -> 缺少右括號
        return stack.isEmpty();
    }

    // 拆分輔助方法：判斷是否為左括號
    private static boolean isLeftBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    // 拆分輔助方法：判斷是否為右括號
    private static boolean isRightBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    // 拆分輔助方法：判斷左右括號是否是一對
    private static boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')') ||
               (left == '[' && right == ']') ||
               (left == '{' && right == '}');
    }

    // 測試主程式
    public static void main(String[] args) {
        System.out.println("=== 括號驗證系統測試 ===\n");

        // 測試案例列表
        String[] testCases = {
            "{ [ a + ( b * c ) ] }",   // 1. 正確的梯級巢狀 + 包含非括號字元
            "a * (b + c) - [d / e]",   // 2. 正確的平行括號 + 包含非括號字元
            "( [ ) ]",                 // 3. 順序錯誤 (Mismatch)
            "function(x) { return [1, 2; }", // 4. 缺少右括號 (Stack 殘留)
            "int x = a) + b;",         // 5. 缺少左括號 (過早 Pop 空 Stack)
            "}"                        // 6. 極端狀況：單一右括號
        };

        for (int i = 0; i < testCases.length; i++) {
            String test = testCases[i];
            boolean result = isValid(test);
            System.out.printf("測試 %d: %-32s -> 驗證結果: %s%n", 
                            (i + 1), "\"" + test + "\"", (result ? "合法 (Valid)" : "非法 (Invalid)"));
        }
    }
}
