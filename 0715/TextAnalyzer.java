import java.util.Scanner;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. 取得有效輸入
        String input = getValidInput(scanner);

        // 2 & 3. 顯示字元數
        System.out.println("原始字元數: " + input.length());
        String trimmed = input.trim();
        System.out.println("有效字元數: " + trimmed.length());

        // 4 & 5. 切割單字並顯示數量
        String[] words = splitWords(trimmed);
        System.out.println("單字數量: " + words.length);

        // 6. 計算母音總數
        System.out.println("母音總數: " + countVowels(trimmed));

        // 7. 找出最長單字
        System.out.println("最長單字: " + findLongest(words));

        // 8. 關鍵字搜尋 (忽略大小寫)
        System.out.print("請輸入要搜尋的關鍵字: ");
        String keyword = scanner.next();
        System.out.println("關鍵字出現次數: " + countKeyword(words, keyword));

        scanner.close();
    }

    // 方法 1：確保輸入不是空文字
    public static String getValidInput(Scanner scanner) {
        while (true) {
            System.out.print("請輸入一行非空白文字: ");
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                return input;
            }
            System.out.println("【錯誤】不能輸入空值，請重新輸入。");
        }
    }

    // 方法 2：把字串切成單字（自動處理連續空白）
    public static String[] splitWords(String text) {
        // 使用 " +" 代表一個或多個空白，簡單又不會因為連續空白出錯
        return text.split(" +");
    }

    // 方法 3：算母音 (a, e, i, o, u)
    public static int countVowels(String text) {
        int count = 0;
        for (char ch : text.toLowerCase().toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    // 方法 4：找最長的單字
    public static String findLongest(String[] words) {
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    // 方法 5：計算關鍵字次數 (不區分大小寫)
    public static int countKeyword(String[] words, String keyword) {
        int count = 0;
        for (String word : words) {
            if (word.equalsIgnoreCase(keyword)) {
                count++;
            }
        }
        return count;
    }
}
