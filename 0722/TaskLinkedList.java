public class TaskLinkedList {
    private TaskNode head;

    public TaskLinkedList() {
        this.head = null;
    }

    // 輔助方法：搜尋工作
    public TaskNode searchByCode(String taskCode) {
        TaskNode current = head;
        while (current != null) {
            if (current.taskCode.equalsIgnoreCase(taskCode)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // 功能要求：緊急工作加到前端 (Add First)
    public boolean addUrgentTask(String taskCode, String description) {
        if (searchByCode(taskCode) != null) {
            System.out.println("[新增失敗] 工作代碼已存在: " + taskCode);
            return false;
        }
        TaskNode newNode = new TaskNode(taskCode, description);
        newNode.next = head;
        head = newNode;
        System.out.println("[緊急工作已加至前端] [" + taskCode + "] " + description);
        return true;
    }

    // 功能要求：一般工作加到尾端 (Add Last)
    public boolean addNormalTask(String taskCode, String description) {
        if (searchByCode(taskCode) != null) {
            System.out.println("[新增失敗] 工作代碼已存在: " + taskCode);
            return false;
        }
        TaskNode newNode = new TaskNode(taskCode, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("[一般工作已加至尾端] [" + taskCode + "] " + description);
        return true;
    }

    // 功能要求：將工作標記為已完成
    public boolean markAsCompleted(String taskCode) {
        TaskNode task = searchByCode(taskCode);
        if (task == null) {
            System.out.println("[更新失敗] 找不到工作代碼: " + taskCode);
            return false;
        }
        task.isCompleted = true;
        System.out.println("[狀態更新] 工作 [" + taskCode + "] 已標記為【已完成】");
        return true;
    }

    // 功能要求：刪除所有「已完成」的工作
    public void removeCompletedTasks() {
        if (head == null) {
            System.out.println("[刪除失敗] 串列為空，無可刪除的工作。");
            return;
        }

        int removedCount = 0;

        // 1. 處理 head 節點本身就是「已完成」的情況
        while (head != null && head.isCompleted) {
            System.out.println("[刪除已完成工作] [" + head.taskCode + "] " + head.description);
            head = head.next;
            removedCount++;
        }

        // 2. 處理中間與尾端的「已完成」節點
        TaskNode current = head;
        while (current != null && current.next != null) {
            if (current.next.isCompleted) {
                System.out.println("[刪除已完成工作] [" + current.next.taskCode + "] " + current.next.description);
                current.next = current.next.next; // 跳接刪除
                removedCount++;
            } else {
                current = current.next;
            }
        }

        if (removedCount == 0) {
            System.out.println("[刪除提示] 目前沒有已完成的工作需要清除。");
        }
    }

    // 功能要求：上市（列出）未完成工作
    public void printPendingTasks() {
        if (head == null) {
            System.out.println("【未完成工作清單】: [ 空白串列 ]");
            return;
        }

        System.out.println("--- 待處理（未完成）工作上市清單 ---");
        TaskNode current = head;
        boolean hasPending = false;
        while (current != null) {
            if (!current.isCompleted) {
                System.out.println("  * [" + current.taskCode + "] " + current.description);
                hasPending = true;
            }
            current = current.next;
        }

        if (!hasPending) {
            System.out.println("  (所有工作皆已完成！)");
        }
        System.out.println("----------------------------------");
    }

    // 功能要求：輸出作業工作總數與未完成數量
    public void printStatistics() {
        int total = 0;
        int pending = 0;
        TaskNode current = head;

        while (current != null) {
            total++;
            if (!current.isCompleted) {
                pending++;
            }
            current = current.next;
        }

        System.out.println("=== 工作統計數據 ===");
        System.out.println("工作總數量: " + total);
        System.out.println("未完成數量: " + pending);
        System.out.println("已完成數量: " + (total - pending));
        System.out.println("====================");
    }

    // 輔助方法：列出全部工作（包含狀態）
    public void printAllTasks() {
        if (head == null) {
            System.out.println("【全部工作清單】: [ 空白串列 ]");
            return;
        }

        System.out.println("--- 全部工作列表 ---");
        TaskNode current = head;
        while (current != null) {
            String status = current.isCompleted ? "[已完成]" : "[未完成]";
            System.out.println("  " + status + " [" + current.taskCode + "] " + current.description);
            current = current.next;
        }
        System.out.println("-------------------");
    }
}
