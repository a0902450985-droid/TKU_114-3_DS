import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class EventRegistrationSystem {

    // 活動正取名額上限
    private static final int MAX_CAPACITY = 3;

    // 要求 1: ArrayList 保存全部報名資料
    private static ArrayList<Registration> allRegistrations = new ArrayList<>();
    // 要求 2: Queue 保存候補順序 (FIFO)
    private static Queue<Registration> waitlistQueue = new LinkedList<>();
    // 要求 3: Stack 保存最近取消紀錄並支援復原 (LIFO)
    private static Deque<Registration> cancellationStack = new ArrayDeque<>();

    public static void main(String[] args) {
        System.out.println("=================== 1. 報名測試 (名額與候補處理) ===================");
        register("R003", "張小明", "0912-345678");
        register("R001", "陳大天", "0923-456789");
        register("R005", "林美麗", "0934-567890");

        // 超過上限 (MAX_CAPACITY = 3)，將自動進入 Queue 候補
        register("R002", "黃志豪", "0945-678901");
        register("R004", "張小明", "0956-789012"); // 同名但不同編號

        // 測試邊界條件：重複編號
        register("R001", "重複測試", "0900-000000");

        System.out.println("\n=================== 2. Merge Sort 依編號排序 (要求 4) ===================");
        Registration[] arrayForSort = allRegistrations.toArray(new Registration[0]);
        RegistrationAlgorithms.mergeSortById(arrayForSort);

        System.out.println("按編號升冪排序後結果:");
        for (Registration reg : arrayForSort) {
            System.out.println("  " + reg);
        }

        System.out.println("\n=================== 3. 搜尋功能測試 (要求 5) ===================");
        // Binary Search 依編號搜尋 (必須傳入排序後的陣列)
        System.out.println("🔍 [Binary Search] 搜尋編號 R002:");
        int idx = RegistrationAlgorithms.binarySearchById(arrayForSort, "R002");
        if (idx != -1) {
            System.out.println("  ✅ 找到資料: " + arrayForSort[idx]);
        } else {
            System.out.println("  ❌ 查無此編號！");
        }

        // Sequential Search 依姓名搜尋
        System.out.println("\n🔍 [Sequential Search] 搜尋姓名 '張小明':");
        ArrayList<Registration> nameResults = RegistrationAlgorithms.searchByName(allRegistrations, "張小明");
        for (Registration reg : nameResults) {
            System.out.println("  " + reg);
        }

        System.out.println("\n=================== 4. 取消報名與候補遞補測試 ===================");
        // 取消存在的正取資料 (R001) -> 觸發 Queue 遞補
        cancelRegistration("R001");

        // 測試邊界條件：取消不存在的資料
        cancelRegistration("R999");

        System.out.println("\n=================== 5. 復原 (Undo) 取消測試 (要求 3) ===================");
        undoCancellation();

        System.out.println("\n=================== 6. 最終統計與狀態顯示 ===================");
        showStatistics();
    }

    /**
     * 報名功能 (處理額滿與候補)
     */
    public static void register(String id, String name, String phone) {
        // 要求 6: 檢查重複編號
        if (RegistrationAlgorithms.isDuplicateId(allRegistrations, id)) {
            System.out.printf("❌ 報名失敗：報名編號 [%s] 已存在！%n", id);
            return;
        }

        int currentMainCount = getMainRegistrationCount();
        boolean isWaitlist = currentMainCount >= MAX_CAPACITY;

        Registration reg = new Registration(id, name, phone, isWaitlist);
        allRegistrations.add(reg);

        if (isWaitlist) {
            waitlistQueue.offer(reg);
            System.out.println("⚠️ 正取已滿，進入候補隊列: " + reg);
        } else {
            System.out.println("✅ 正取報名成功: " + reg);
        }
    }

    /**
     * 取消報名 (若取消正取且 Queue 中有候補，則自動遞補)
     */
    public static void cancelRegistration(String id) {
        Registration target = null;
        for (Registration reg : allRegistrations) {
            if (reg.getId().equalsIgnoreCase(id)) {
                target = reg;
                break;
            }
        }

        // 要求 6: 取消不存在的資料處理
        if (target == null) {
            System.out.printf("❌ 取消失敗：找不到編號 [%s] 的報名紀錄！%n", id);
            return;
        }

        allRegistrations.remove(target);
        cancellationStack.push(target); // 入棧紀錄
        System.out.println("🗑️ 已取消報名: " + target);

        // 如果取消的是正取，且候補 Queue 有人，進行自動遞補
        if (!target.isWaitlist() && !waitlistQueue.isEmpty()) {
            Registration promoted = waitlistQueue.poll();
            promoted.setWaitlist(false);
            System.out.println("🎉 候補遞補成功！: " + promoted);
        }
    }

    /**
     * 要求 3: 復原最近一次取消的報名
     */
    public static void undoCancellation() {
        if (cancellationStack.isEmpty()) {
            System.out.println("❌ 無可復原的取消紀錄 (Stack 為空)。");
            return;
        }

        Registration restored = cancellationStack.pop();
        int currentMainCount = getMainRegistrationCount();

        // 重新評估復原時應為正取或候補
        if (currentMainCount < MAX_CAPACITY) {
            restored.setWaitlist(false);
            System.out.println("🔄 復原成功！重新取得正取資格: " + restored);
        } else {
            restored.setWaitlist(true);
            waitlistQueue.offer(restored);
            System.out.println("🔄 復原成功！因正取已滿，轉入候補隊列: " + restored);
        }

        allRegistrations.add(restored);
    }

    /**
     * 計算目前正取人數
     */
    private static int getMainRegistrationCount() {
        int count = 0;
        for (Registration reg : allRegistrations) {
            if (!reg.isWaitlist()) {
                count++;
            }
        }
        return count;
    }

    /**
     * 顯示系統當前統計
     */
    public static void showStatistics() {
        System.out.println("\n📊 --- 活動報名統計報告 ---");
        System.out.println("  * 總報名筆數 (ArrayList) : " + allRegistrations.size() + " 筆");
        System.out.println("  * 正取人數             : " + getMainRegistrationCount() + " / " + MAX_CAPACITY + " 人");
        System.out.println("  * 候補等待人數 (Queue) : " + waitlistQueue.size() + " 人");
        System.out.println("  * 取消紀錄筆數 (Stack) : " + cancellationStack.size() + " 筆");
        System.out.println("--------------------------------");
    }
}