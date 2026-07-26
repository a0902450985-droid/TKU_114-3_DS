import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ClinicQueueSystem {

    // 2. 使用佇列 (Queue) 保存等待隊列
    private final Queue<Patient> waitingQueue;
    
    // 3. 用來檢查號碼是否重複的集合 (Set)
    private final Set<Integer> usedNumbers;

    // 4. 統計服務總人數
    private int totalServedCount;

    public ClinicQueueSystem() {
        this.waitingQueue = new ArrayDeque<>();
        this.usedNumbers = new HashSet<>();
        this.totalServedCount = 0;
    }

    /**
     * 2. 掛號 (Enqueue)
     * 3. 號碼不可重複防呆
     */
    public void register(int number, String name, String department) {
        if (usedNumbers.contains(number)) {
            System.out.println("【掛號失敗】號碼 " + number + " 已經存在，無法重複掛號！");
            return;
        }

        Patient patient = new Patient(number, name, department);
        waitingQueue.offer(patient);
        usedNumbers.add(number);
        System.out.println("【掛號成功】" + patient);
    }

    /**
     * 2. 叫號 (Dequeue)
     * 空結構操作安全：無患者時不發生錯誤
     */
    public void callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("【提示】目前沒有等待中的患者，無法叫號！");
            return;
        }

        Patient current = waitingQueue.poll();
        totalServedCount++;
        System.out.println("【叫號看診】請 " + current + " 至診室就診。");
    }

    /**
     * 2. 查看下一位 (Peek)
     */
    public void peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("【目前狀態】隊列為空，沒有下一位等待的患者。");
        } else {
            System.out.println("【下一位預備】" + waitingQueue.peek());
        }
    }

    /**
     * 2. 查看完整等待清單
     */
    public void showWaitingList() {
        System.out.println("\n--- 目前等待清單 ---");
        if (waitingQueue.isEmpty()) {
            System.out.println("（目前無人等待）");
        } else {
            int count = 1;
            for (Patient p : waitingQueue) {
                System.out.println(count++ + ". " + p);
            }
        }
        System.out.println("--------------------\n");
    }

    /**
     * 4. 輸出各科別等待人數與服務總人數
     */
    public void showStatistics() {
        System.out.println("========== 統計數據 ==========");
        
        // 統計各科別等待人數
        Map<String, Integer> deptCountMap = new HashMap<>();
        for (Patient p : waitingQueue) {
            String dept = p.getDepartment();
            deptCountMap.put(dept, deptCountMap.getOrDefault(dept, 0) + 1);
        }

        System.out.println("【各科別等待人數】");
        if (deptCountMap.isEmpty()) {
            System.out.println("  各科別目前皆無等待患者");
        } else {
            for (Map.Entry<String, Integer> entry : deptCountMap.entrySet()) {
                System.out.println("  - " + entry.getKey() + ": " + entry.getValue() + " 人");
            }
        }

        System.out.println("【全院等待總人數】: " + waitingQueue.size() + " 人");
        System.out.println("【已服務總人數】: " + totalServedCount + " 人");
        System.out.println("==============================");
    }

    // 測試主程式
    public static void main(String[] args) {
        ClinicQueueSystem system = new ClinicQueueSystem();

        System.out.println("=== 實習叫號系統測試 ===\n");

        // 測試 1: 空隊列防呆測試
        system.callNext();
        system.peekNext();

        System.out.println();

        // 測試 2: 患者掛號
        system.register(101, "王小明", "內科");
        system.register(102, "李美玲", "外科");
        system.register(103, "張大華", "兒科");
        system.register(104, "陳雅婷", "內科");

        System.out.println();

        // 測試 3: 號碼重複掛號測試 (防呆)
        system.register(101, "重複號碼測試者", "皮膚科");

        System.out.println();

        // 測試 4: 查看等待清單與下一位
        system.showWaitingList();
        system.peekNext();

        System.out.println();

        // 測試 5: 叫號看診
        system.callNext(); // 王小明看診
        system.callNext(); // 李美玲看診

        System.out.println();

        // 測試 6: 新增患者
        system.register(105, "林志豪", "兒科");

        System.out.println();

        // 測試 7: 查看最新清單與統計數據
        system.showWaitingList();
        system.showStatistics();
    }
}
