import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CounterServiceSystem {

    // 內部類別：用來保存號碼與姓名
    private static class Customer {
        private final int number;
        private final String name;

        public Customer(int number, String name) {
            this.number = number;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("[%03d號] %s", number, name);
        }
    }

    // 1. 使用 Queue (LinkedList 或 ArrayDeque) 儲存等待隊列
    private final Queue<Customer> waitingQueue;
    // 4. 處理記錄另外保存（已叫號/服務完成的紀錄）
    private final List<Customer> servedHistory;
    
    private int nextNumber; // 自動累加的叫號碼牌

    public CounterServiceSystem() {
        this.waitingQueue = new ArrayDeque<>();
        this.servedHistory = new ArrayList<>();
        this.nextNumber = 1;
    }

    /**
     * 2. 支援取號 (Enqueue)
     */
    public void takeNumber(String name) {
        Customer customer = new Customer(nextNumber++, name);
        waitingQueue.offer(customer);
        System.out.println("【取號成功】" + customer + " 已加入等待隊列。");
    }

    /**
     * 2. 支援叫號 (Dequeue)
     * 3. 空隊列叫號不可發生錯誤 (空結構操作安全)
     */
    public void callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("【提示】目前沒有等待中的顧客，無法叫號！");
            return;
        }

        Customer current = waitingQueue.poll();
        servedHistory.add(current); // 4. 保存至已處理記錄
        System.out.println("【叫號服務】請 " + current + " 到櫃檯辦理業務。");
    }

    /**
     * 2. 支援查看下一位 (Peek)
     */
    public void peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("【目前狀態】隊列為空，沒有下一位等待者。");
        } else {
            System.out.println("【下一位預備】" + waitingQueue.peek());
        }
    }

    /**
     * 2. 支援查看等待人數 (Waiting Size / 原文誤譯之等待大學生)
     */
    public void showWaitingCount() {
        System.out.println("【等待人數】目前共有 " + waitingQueue.size() + " 位顧客正在等待。");
    }

    /**
     * 4. 顯示已處理的歷史紀錄
     */
    public void showServedHistory() {
        System.out.println("\n--- 已服務顧客歷史紀錄 ---");
        if (servedHistory.isEmpty()) {
            System.out.println("尚無已服務的顧客紀錄。");
        } else {
            for (int i = 0; i < servedHistory.size(); i++) {
                System.out.println((i + 1) + ". " + servedHistory.get(i));
            }
        }
        System.out.println("---------------------------\n");
    }

    // 測試主程式
    public static void main(String[] args) {
        CounterServiceSystem system = new CounterServiceSystem();

        System.out.println("=== 櫃檯叫號系統測試 ===\n");

        // 測試 1: 空隊列時叫號與查看（防呆測試）
        system.callNext();
        system.peekNext();
        system.showWaitingCount();

        System.out.println();

        // 測試 2: 顧客陸續取號
        system.takeNumber("Alice");
        system.takeNumber("Bob");
        system.takeNumber("Charlie");

        System.out.println();

        // 測試 3: 查看狀態與叫號
        system.showWaitingCount();
        system.peekNext();

        System.out.println();

        // 測試 4: 連續叫號服務
        system.callNext(); // Alice
        system.callNext(); // Bob

        System.out.println();

        // 測試 5: 新顧客加入與查看狀態
        system.takeNumber("David");
        system.showWaitingCount();
        system.peekNext(); // Charlie

        System.out.println();

        // 測試 6: 叫號清空隊列
        system.callNext(); // Charlie
        system.callNext(); // David
        system.callNext(); // 再叫號一次 -> 觸發隊列為空提示

        // 測試 7: 查看歷史紀錄
        system.showServedHistory();
    }
}
