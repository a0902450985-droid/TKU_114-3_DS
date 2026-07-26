import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorUndoSystem {

    // 當前編輯器儲存的文字內容
    private StringBuilder currentText;
    
    // 使用 Stack 儲存歷史狀態（修改前保存狀態）
    private Deque<String> historyStack;

    public TextEditorUndoSystem() {
        this.currentText = new StringBuilder();
        this.historyStack = new ArrayDeque<>();
    }

    /**
     * 輔助方法：在進行任何變更（修改）前，將當前狀態備份至 Stack
     */
    private void saveState() {
        historyStack.push(currentText.toString());
    }

    /**
     * 1. 新增文字 (Append Text)
     */
    public void addText(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        // 2. 修改前保存狀態
        saveState();
        currentText.append(text);
        System.out.println("【新增文字】: \"" + text + "\"");
    }

    /**
     * 1. 刪除最後幾個字元 (Delete Last N Characters)
     */
    public void deleteLast(int count) {
        if (count <= 0 || currentText.length() == 0) {
            System.out.println("【提示】無法執行刪除操作（數量不合法或文字為空）。");
            return;
        }

        // 2. 修改前保存狀態
        saveState();

        int length = currentText.length();
        int actualDeleteCount = Math.min(count, length); // 防呆：避免刪除數量超出文字長度
        currentText.delete(length - actualDeleteCount, length);
        
        System.out.println("【刪除文字】刪除了最後 " + actualDeleteCount + " 個字元。");
    }

    /**
     * 1. 撤銷 (Undo)
     * 3. 沒有歷史記錄時撤銷要顯示訊息 (空結構操作安全)
     */
    public void undo() {
        if (historyStack.isEmpty()) {
            System.out.println("【警告】沒有歷史紀錄可以撤銷！");
            return;
        }

        // 恢復至上一次保存的狀態
        String previousState = historyStack.pop();
        this.currentText = new StringBuilder(previousState);
        System.out.println("【執行撤銷】已復原至上一次的狀態。");
    }

    /**
     * 1. 顯示目前內容
     */
    public void displayText() {
        System.out.println(">> 目前文字內容: \"" + currentText.toString() + "\"");
    }

    // 測試主程式
    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        System.out.println("=== 文字編輯器撤銷系統測試 ===\n");

        // 測試 1: 空白狀態下嘗試撤銷（邊界條件測試）
        System.out.println("--- 測試防呆與初始狀態 ---");
        editor.displayText();
        editor.undo(); // 應提示沒有歷史紀錄

        System.out.println("\n--- 開始進行文字編輯與備份 ---");
        // 操作 1: 新增 "Hello"
        editor.addText("Hello");
        editor.displayText();

        // 操作 2: 新增 " World"
        editor.addText(" World");
        editor.displayText();

        // 操作 3: 新增 "!"
        editor.addText("!");
        editor.displayText();

        // 操作 4: 刪除最後 1 個字元 (刪除 "!")
        editor.deleteLast(1);
        editor.displayText();

        // 操作 5: 新增 " Java"
        editor.addText(" Java");
        editor.displayText();

        System.out.println("\n--- 4. 驗證連續三次撤銷 (Undo) ---");

        // 撤銷 1: 復原新增 " Java" -> 應恢復為 "Hello World"
        System.out.println("[第 1 次撤銷]");
        editor.undo();
        editor.displayText();

        // 撤銷 2: 復原刪除 "!" -> 應恢復為 "Hello World!"
        System.out.println("[第 2 次撤銷]");
        editor.undo();
        editor.displayText();

        // 撤銷 3: 復原新增 "!" -> 應恢復為 "Hello World"
        System.out.println("[第 3 次撤銷]");
        editor.undo();
        editor.displayText();

        // 額外測試: 再連續撤銷至空
        System.out.println("\n--- 額外測試：繼續撤銷至最原始狀態 ---");
        editor.undo(); // 復原新增 " World" -> "Hello"
        editor.displayText();

        editor.undo(); // 復原新增 "Hello" -> ""
        editor.displayText();

        editor.undo(); // 已無紀錄 -> 顯示警告訊息
    }
}
