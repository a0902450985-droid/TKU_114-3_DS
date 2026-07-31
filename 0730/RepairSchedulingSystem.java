import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class RepairSchedulingSystem {

    // 要求 3: ArrayList 保存所有工作
    private static ArrayList<RepairTask> allTasks = new ArrayList<>();
    // 要求 1: Queue 保存等待維修工作 (FIFO)
    private static Queue<RepairTask> pendingQueue = new LinkedList<>();
    // 要求 2: Stack 保存已完成工作 (LIFO)
    private static Deque<RepairTask> completedStack = new ArrayDeque<>();

    public static void main(String[] args) {
        System.out.println("=================== 1. 登記維修工作 (新增測試) ===================");
        // 注意：T001 與 T004 優先級皆為 3，用來驗證「相同等級保持登記順序」
        addTask("T001", "伺服器A", 3, "電源供應器異常");
        addTask("T002", "印表機B", 1, "夾紙無法列印");
        addTask("T003", "防火牆C", 5, "無法連線至外網");
        addTask("T004", "核心交換器", 3, "Port 8 燈號異常");
        addTask("T005", "伺服器A", 2, "硬體磁碟警報");

        // 重複編號測試
        addTask("T001", "測試設備", 4, "重複編號測試");

        System.out.println("\n=================== 2. 依優先等級 Merge Sort 排序 (要求 4) ===================");
        RepairTask[] taskArray = allTasks.toArray(new RepairTask[0]);
        RepairAlgorithms.mergeSortByPriorityDesc(taskArray);

        System.out.println("按優先級降冪排序結果 (注意 T001 與 T004 順序保留了登記順序):");
        for (RepairTask task : taskArray) {
            System.out.println("  " + task);
        }

        System.out.println("\n=================== 3. 處理工作與 Stack 復原測試 (要求 1 & 2) ===================");
        // 處理兩筆工作
        processNextTask();
        processNextTask();

        // 顯示目前統計
        showStatistics();

        // 測試復原功能 (Undo)
        System.out.println("\n[測試復原/復原操作]");
        undoLastCompletedTask();

        System.out.println("\n=================== 4. 搜尋功能測試 (要求 5) ===================");
        // 依編號搜尋 (Binary Search)
        System.out.println("🔍 [Binary Search] 搜尋編號 T003:");
        RepairTask[] sortedByIdArray = allTasks.toArray(new RepairTask[0]);
        RepairAlgorithms.mergeSortById(sortedByIdArray); // 先依 ID 排序
        int foundIdx = RepairAlgorithms.binarySearchById(sortedByIdArray, "T003");
        if (foundIdx != -1) {
            System.out.println("  ✅ 找到工作: " + sortedByIdArray[foundIdx]);
        } else {
            System.out.println("  ❌ 未找到該編號！");
        }

        // 依設備名稱搜尋 (Sequential Search)
        System.out.println("\n🔍 [Sequential Search] 搜尋設備名稱 '伺服器A':");
        ArrayList<RepairTask> devResults = RepairAlgorithms.searchByDeviceName(allTasks, "伺服器A");
        if (devResults.isEmpty()) {
            System.out.println("  ⚠️ 查無該設備相關工作。");
        } else {
            for (RepairTask t : devResults) {
                System.out.println("  " + t);
            }
        }

        System.out.println("\n=================== 5. 最終工作統計 (要求 6) ===================");
        // 處理完剩餘所有工作
        while (!pendingQueue.isEmpty()) {
            processNextTask();
        }
        
        // 再次輸出總統計
        showStatistics();
    }

    /**
     * 新增維修工作
     */
    public static void addTask(String id, String deviceName, int priority, String description) {
        if (RepairAlgorithms.isDuplicateId(allTasks, id)) {
            System.out.printf("❌ 登記失敗：工作編號 [%s] 已存在！%n", id);
            return;
        }
        RepairTask task = new RepairTask(id, deviceName, priority, description);
        allTasks.add(task);
        pendingQueue.offer(task);
        System.out.println("✅ 成功登記: " + task);
    }

    /**
     * 處理下一筆等待維修工作 (Queue -> Stack)
     */
    public static void processNextTask() {
        RepairTask task = pendingQueue.poll();
        if (task != null) {
            completedStack.push(task);
            System.out.println("⚙️ 已完成維修: " + task);
        } else {
            System.out.println("⚠️ 目前無待處理的維修工作 (Queue 為空)。");
        }
    }

    /**
     * 要求 2: 支援復原功能 (將 Stack 頂端的已完成工作彈出，放回 Queue 隊頭)
     */
    public static void undoLastCompletedTask() {
        if (completedStack.isEmpty()) {
            System.out.println("❌ 無可復原的已完成工作 (Stack 為空)。");
            return;
        }
        RepairTask restoredTask = completedStack.pop();
        // 放回 pendingQueue 隊頭 (使用 Deque 行為或重新處理)
        ((LinkedList<RepairTask>) pendingQueue).addFirst(restoredTask);
        System.out.println("🔄 成功復原！已將最新完成的工作移回等待隊列: " + restoredTask);
    }

    /**
     * 要求 6: 顯示等待、完成及全部工作統計
     */
    public static void showStatistics() {
        System.out.println("\n📊 --- 維修工作統計報告 ---");
        System.out.println("  * 全部登記工作總數 (ArrayList) : " + allTasks.size() + " 筆");
        System.out.println("  * 等待維修工作數   (Queue)     : " + pendingQueue.size() + " 筆");
        System.out.println("  * 已完成維修工作數 (Stack)     : " + completedStack.size() + " 筆");
        System.out.println("----------------------------");
    }
}