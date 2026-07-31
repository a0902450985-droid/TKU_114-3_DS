public class RepairTask {
    private String id;          // 工作編號
    private String deviceName;  // 設備名稱
    private int priority;       // 優先等級 (數字越大優先度越高)
    private String description; // 維修內容說明

    public RepairTask(String id, String deviceName, int priority, String description) {
        this.id = id;
        this.deviceName = deviceName;
        this.priority = priority;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("維修工作[編號: %-6s | 設備: %-10s | 優先級: %2d | 說明: %s]",
                id, deviceName, priority, description);
    }
}