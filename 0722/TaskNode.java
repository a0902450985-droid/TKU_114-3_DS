public class TaskNode {
    String taskCode;     // 工作代碼
    String description;  // 工作說明
    boolean isCompleted; // 完成狀態 (true: 已完成, false: 未完成)
    TaskNode next;

    public TaskNode(String taskCode, String description) {
        this.taskCode = taskCode;
        this.description = description;
        this.isCompleted = false; // 預設為未完成
        this.next = null;
    }
}