public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("=== 測試 1：空白串列邊界測試 ===");
        taskList.printAllTasks();
        taskList.printPendingTasks();
        taskList.printStatistics();
        taskList.removeCompletedTasks();

        System.out.println("\n=== 測試 2：新增一般與緊急工作 ===");
        // 新增一般工作（加到尾端）
        taskList.addNormalTask("T001", "撰寫專案報告");
        taskList.addNormalTask("T002", "備份資料庫");

        // 新增緊急工作（加到前端）
        taskList.addUrgentTask("T000", "修復伺服器系統崩潰 Bug");
        taskList.addUrgentTask("T999", "處理客戶緊急抱怨");

        taskList.printAllTasks();

        System.out.println("\n=== 測試 3：標記工作為已完成 ===");
        taskList.markAsCompleted("T000"); // 標記緊急工作
        taskList.markAsCompleted("T002"); // 標記一般工作

        taskList.printAllTasks();

        System.out.println("\n=== 測試 4：上市未完成工作與統計數據 ===");
        taskList.printPendingTasks();
        taskList.printStatistics();

        System.out.println("\n=== 測試 5：刪除所有已完成工作 ===");
        taskList.removeCompletedTasks();

        System.out.println("\n=== 測試 6：刪除後清單與統計檢視 ===");
        taskList.printAllTasks();
        taskList.printPendingTasks();
        taskList.printStatistics();
    }
}
