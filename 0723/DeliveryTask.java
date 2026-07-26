public class DeliveryTask {
    private final String taskId;      // 任務編號
    private final String taskName;    // 任務/報表名稱

    public DeliveryTask(String taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", taskId, taskName);
    }
}