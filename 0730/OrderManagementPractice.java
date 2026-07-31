import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class OrderManagementPractice {

    // 主資料清單
    private static ArrayList<Order> mainOrders = new ArrayList<>();
    // 等待 Queue（FIFO 處理訂單）
    private static Queue<Order> pendingQueue = new LinkedList<>();
    // 完成 Stack（LIFO 記錄歷史完成訂單）
    private static Deque<Order> completedStack = new ArrayDeque<>();

    public static void main(String[] args) {
        System.out.println("=================== 1. 新增訂單與重複編號測試 ===================");
        addOrder("ORD003", "Alice", 1500);
        addOrder("ORD001", "Bob", 3200);
        addOrder("ORD004", "Alice", 800);
        addOrder("ORD002", "Charlie", 4500);

        // 測試 4 & 5：防止重複訂單編號
        System.out.println("\n[測試] 嘗試加入重複編號 ORD001:");
        addOrder("ORD001", "David", 2000);

        System.out.println("\n=================== 2. 依金額降冪排序測試 ===================");
        Order[] orderArrayForAmount = mainOrders.toArray(new Order[0]);
        OrderAlgorithms.mergeSortByAmountDescending(orderArrayForAmount);

        System.out.println("金額由高到低排序結果:");
        for (Order o : orderArrayForAmount) {
            System.out.println("  " + o);
        }

        System.out.println("\n=================== 3. 依顧客姓名搜尋測試 ===================");
        searchOrdersForCustomer("Alice");
        searchOrdersForCustomer("Eve"); // 測試 5: 找不到資料

        System.out.println("\n=================== 4. Queue 與 Stack 操作與邊界測試 ===================");
        // 功能要求 3：顯示下一筆待處理訂單
        peekNextOrder();

        // 處理訂單 (進入 Stack)
        processNextOrder();
        processNextOrder();

        // 查看目前的 Queue 與 Stack
        peekNextOrder();
        showCompletedStack();

        System.out.println("\n=================== 5. 邊界測試 (空 Queue & 空 Stack) ===================");
        // 處理剩餘訂單
        processNextOrder();
        processNextOrder();

        // 測試空 Queue (功能要求 5)
        System.out.println("\n[測試] Queue 已空時嘗試獲取與處理下一筆:");
        peekNextOrder();
        processNextOrder();

        // 清空 Stack 並測試空 Stack (功能要求 5)
        completedStack.clear();
        System.out.println("\n[測試] Stack 已空時嘗試讀取:");
        showCompletedStack();
    }

    /**
     * 新增訂單並放入主資料與等待佇列
     */
    public static void addOrder(String id, String customer, int amount) {
        if (OrderAlgorithms.isDuplicateId(mainOrders, id)) {
            System.out.printf("❌ 新增失敗：訂單編號 [%s] 已存在！%n", id);
            return;
        }
        Order newOrder = new Order(id, customer, amount);
        mainOrders.add(newOrder);
        pendingQueue.offer(newOrder);
        System.out.printf("✅ 成功新增: %s%n", newOrder);
    }

    /**
     * 功能要求 3：顯示下一筆待處理訂單 (Peek)
     */
    public static void peekNextOrder() {
        Order next = pendingQueue.peek();
        if (next != null) {
            System.out.println("👀 下一筆待處理訂單: " + next);
        } else {
            System.out.println("⚠️ 待處理佇列 (Queue) 目前為空！");
        }
    }

    /**
     * 處理下一筆訂單：從 Queue 出列並推入 Stack
     */
    public static void processNextOrder() {
        Order processed = pendingQueue.poll();
        if (processed != null) {
            completedStack.push(processed);
            System.out.println("⚙️ 已完成處理: " + processed);
        } else {
            System.out.println("❌ 無可處理的訂單，佇列為空！");
        }
    }

    /**
     * 依顧客姓名搜尋
     */
    public static void searchOrdersForCustomer(String name) {
        System.out.printf("🔍 搜尋顧客 [%s] 的所有訂單:%n", name);
        ArrayList<Order> results = OrderAlgorithms.findByCustomer(mainOrders, name);
        if (results.isEmpty()) {
            System.out.println("  ⚠️ 找不到該顧客的任何訂單。");
        } else {
            for (Order o : results) {
                System.out.println("  " + o);
            }
        }
    }

    /**
     * 顯示 Stack 歷史紀錄
     */
    public static void showCompletedStack() {
        if (completedStack.isEmpty()) {
            System.out.println("⚠️ 完成堆疊 (Stack) 目前為空，無歷史記錄。");
        } else {
            System.out.println("📚 已完成訂單紀錄 (Stack 頂端至底端):");
            for (Order o : completedStack) {
                System.out.println("  " + o);
            }
        }
    }
}