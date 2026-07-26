import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserUndoSystem {

    // 使用 Deque (Double Ended Queue) 作為 Stack 結構儲存瀏覽頁面
    private Deque<String> historyStack;

    public BrowserUndoSystem() {
        this.historyStack = new ArrayDeque<>();
    }

    /**
     * 1. 開啟新頁面 (Push)
     */
    public void visit(String url) {
        historyStack.push(url);
        System.out.println("開啟頁面: " + url);
    }

    /**
     * 2. 返回上一頁 (Pop)
     * 要求 3：當沒有上一頁可返回時，顯示明確提示訊息（維護空結構操作安全）
     */
    public void back() {
        if (historyStack.isEmpty()) {
            System.out.println("【警告】目前沒有歷史紀錄，無法返回上一頁！");
            return;
        }
        
        String popped = historyStack.pop();
        System.out.println("返回上一頁，已離開: " + popped);
    }

    /**
     * 3. 查看目前頁面 (Peek)
     * 維護空結構操作安全
     */
    public void currentPage() {
        if (historyStack.isEmpty()) {
            System.out.println("目前沒有開啟任何頁面（空白頁）。");
        } else {
            System.out.println("目前頁面: " + historyStack.peek());
        }
    }

    // 4. 進行至少 8 次操作測試
    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();

        System.out.println("=== 開始瀏覽器操作測試 ===");

        // 操作 1: 嘗試在空白狀態下查看頁面（邊界條件測試）
        System.out.print("[操作 1] ");
        browser.currentPage();

        // 操作 2: 嘗試在空白狀態下返回上一頁（邊界條件測試）
        System.out.print("[操作 2] ");
        browser.back();

        // 操作 3: 開啟 Google
        System.out.print("[操作 3] ");
        browser.visit("https://www.google.com");

        // 操作 4: 開啟 YouTube
        System.out.print("[操作 4] ");
        browser.visit("https://www.youtube.com");

        // 操作 5: 查看目前頁面
        System.out.print("[操作 5] ");
        browser.currentPage();

        // 操作 6: 開啟 GitHub
        System.out.print("[操作 6] ");
        browser.visit("https://github.com");

        // 操作 7: 返回上一頁 (應離開 GitHub)
        System.out.print("[操作 7] ");
        browser.back();

        // 操作 8: 查看目前頁面 (應顯示 YouTube)
        System.out.print("[操作 8] ");
        browser.currentPage();

        // 操作 9 (額外測試): 再次返回上一頁 (應離開 YouTube)
        System.out.print("[操作 9] ");
        browser.back();

        // 操作 10 (額外測試): 查看目前頁面 (應顯示 Google)
        System.out.print("[操作 10] ");
        browser.currentPage();

        System.out.println("=== 測試完成 ===");
    }
}
