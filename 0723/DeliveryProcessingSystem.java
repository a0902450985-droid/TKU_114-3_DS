import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class DeliveryProcessingSystem {

    // 1. Queue 儲存待處理工作 (待辦佇列)
    private final Queue<DeliveryTask> taskQueue;

    // 1. Stack 儲存完成紀錄 (完成堆疊，後完成的會在最上面)
    private final Deque<DeliveryTask> completedStack;

    public DeliveryProcessingSystem() {
        this.taskQueue = new ArrayDeque<>();
        this.completedStack = new ArrayDeque<>();
    }

    /**
     * 2. 新增工作/報表 (Enqueue)
     */
    public void addTask(String taskId, String taskName) {
        DeliveryTask task = new DeliveryTask(taskId, taskName);
        taskQueue.offer(task);
        System.out.println("【新增工作】已加入佇列: " + task);
    }

    /**
     * 2. 完成下一個報表/工作 (Dequeue -> Push to Stack)
     * 空結構操作安全防呆
     */
    public void completeNextTask() {
        if (taskQueue.isEmpty()) {
            System.out.println("【提示】目前沒有等待處理的工作！");
            return;
        }

        DeliveryTask completedTask = taskQueue.poll();
        completedStack.push(completedTask); // 移入 Stack 紀錄
        System.out.println("【完成工作】已成功處理並歸檔: " + completedTask);
    }

    /**
     * 2. 檢視下一個報表 (Peek Queue)
     */
    public void peekNextTask() {
        if (taskQueue.isEmpty()) {
            System.out.println("【目前狀態】待處理佇列為空。");
        } else {
            System.out.println("【下一項預備工作】" + taskQueue.peek());
        }
    }

    /**
     * 2. 檢視最近完成的多個報表 (Peek/Iterate Stack)
     */
    public void showRecentCompletedTasks(int count) {
        System.out.println("\n--- 最近完成的 " + count + " 項工作紀錄 (最新在最前) ---");
        if (completedStack.isEmpty()) {
            System.out.println("（目前尚無已完成的工作紀錄）");
        } else {
            int displayed = 0;
            for (DeliveryTask task : completedStack) {
                if (displayed >= count) break;
                System.out.println((displayed + 1) + ". " + task);
                displayed++;
            }
        }
        System.out.println("----------------------------------------------\n");
    }

    /**
     * 3. 將當前隊首工作延後/回到等待佇列尾端 (Dequeue -> Enqueue)
     */
    public void requeueCurrentTask() {
        if (taskQueue.isEmpty()) {
            System.out.println("【提示】目前佇列為空，無法重排工作！");
            return;
        }

        DeliveryTask task = taskQueue.poll();
        taskQueue.offer(task);
        System.out.println("【延後處理】工作 " + task + " 已重新移至佇列尾端。");
    }

    /**
     * 4. 輸出等待數、完成數和所有處理記錄
     */
    public void showSystemSummary() {
        System.out.println("========== 系統整體摘要與處理記錄 ==========");
        System.out.println("【等待處理總數 (Queue Size)】: " + taskQueue.size());
        System.out.println("【已完成總數 (Stack Size)】  : " + completedStack.size());

        System.out.println("\n[待處理佇列內容 (由前至後)]:");
        if (taskQueue.isEmpty()) {
            System.out.println("  (無待處理工作)");
        } else {
            int idx = 1;
            for (DeliveryTask t : taskQueue) {
                System.out.println("  " + idx++ + ". " + t);
            }
        }

        System.out.println("\n[所有已完成歷史紀錄 (由新至舊)]:");
        if (completedStack.isEmpty()) {
            System.out.println("  (無已完成紀錄)");
        } else {
            int idx = 1;
            for (DeliveryTask t : completedStack) {
                System.out.println("  " + idx++ + ". " + t);
            }
        }
        System.out.println("============================================\n");
    }

    // 測試主程式
    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        System.out.println("=== 產品工作流程系統測試 ===\n");

        // 測試 1: 空結構防呆
        system.completeNextTask();
        system.peekNextTask();

        System.out.println();

        // 測試 2: 新增多個工作報表
        system.addTask("TASK-01", "月度銷售報表");
        system.addTask("TASK-02", "用戶增長分析");
        system.addTask("TASK-03", "伺服器日誌稽核");
        system.addTask("TASK-04", "財務結算報告");

        System.out.println();

        // 測試 3: 檢視下一個與完成工作
        system.peekNextTask();
        system.completeNextTask(); // 完成 TASK-01

        System.out.println();

        // 測試 4: 將工作 (TASK-02) 移回到佇列尾端
        System.out.println("--- 測試需求 3：將工作回到等待佇列尾端 ---");
        system.requeueCurrentTask(); // TASK-02 移至尾端，現在隊頭變成 TASK-03

        System.out.println();

        // 測試 5: 繼續完成工作
        system.peekNextTask();      // 應顯示 TASK-03
        system.completeNextTask(); // 完成 TASK-03
        system.completeNextTask(); // 完成 TASK-04

        System.out.println();

        // 測試 6: 檢視最近完成的多個報表 (需求 2)
        system.showRecentCompletedTasks(2);

        // 測試 7: 輸出完整摘要 (需求 4)
        system.showSystemSummary();
    }
}
