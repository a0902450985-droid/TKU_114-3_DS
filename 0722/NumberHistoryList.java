// 節點類別 (Node)
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class NumberHistoryList {
    private Node head;

    public NumberHistoryList() {
        this.head = null;
    }

    // 1. 前端新增 (Add First)
    public void addFirst(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        System.out.println("[操作 - 前端新增] 加入數值: " + val);
    }

    // 2. 尾端新增 (Add Last)
    public void addLast(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("[操作 - 尾端新增] 加入數值: " + val);
    }

    // 3. 搜尋 (Search)
    public boolean search(int val) {
        if (head == null) {
            System.out.println("[操作 - 搜尋] 串列為空，找不到數值: " + val);
            return false;
        }

        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == val) {
                System.out.println("[操作 - 搜尋] 成功找到 " + val + "，地位於索引: " + index);
                return true;
            }
            current = current.next;
            index++;
        }

        System.out.println("[操作 - 搜尋] 未找到數值: " + val);
        return false;
    }

    // 4. 刪除 (Remove)
    public boolean remove(int val) {
        if (head == null) {
            System.out.println("[操作 - 刪除] 串列為空，無法刪除 " + val);
            return false;
        }

        // 刪除 head 節點
        if (head.data == val) {
            head = head.next;
            System.out.println("[操作 - 刪除] 成功刪除頂部節點: " + val);
            return true;
        }

        // 刪除中間或尾端節點
        Node current = head;
        while (current.next != null && current.next.data != val) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("[操作 - 刪除] 找不到數值 " + val + "，無法進行刪除");
            return false;
        }

        current.next = current.next.next;
        System.out.println("[操作 - 刪除] 成功刪除節點: " + val);
        return true;
    }

    // 5. 走訪輸出 (Print List)
    public void printList() {
        if (head == null) {
            System.out.println("【串列內容】: [ 空白串列 ]");
            return;
        }

        System.out.print("【串列內容】: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    // 統計功能：大小、總和、頂部 (明確說明空串列情況)
    public void printStats() {
        System.out.println("--- 統計資訊 ---");
        if (head == null) {
            System.out.println("大小 (Size)   : 0 (串列為空)");
            System.out.println("總和 (Sum)    : 0 (無數值)");
            System.out.println("頂部 (Top/Head): 無 (串列目前沒有節點)");
            System.out.println("----------------");
            return;
        }

        int size = 0;
        int sum = 0;
        Node current = head;

        while (current != null) {
            size++;
            sum += current.data;
            current = current.next;
        }

        System.out.println("大小 (Size)   : " + size);
        System.out.println("總和 (Sum)    : " + sum);
        System.out.println("頂部 (Top/Head): " + head.data);
        System.out.println("----------------");
    }

    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();

        System.out.println("=== 測試 1：初始空白串列統計 ===");
        list.printStats();
        list.printList();

        System.out.println("\n=== 測試 2：進行連續操作 (共 10 次操作) ===");

        // 操作 1：前端新增 20
        list.addFirst(20);

        // 操作 2：前端新增 10
        list.addFirst(10);

        // 操作 3：尾端新增 30
        list.addLast(30);

        // 操作 4：尾端新增 40
        list.addLast(40);

        // 操作 5：走訪輸出與統計觀測
        list.printList();
        list.printStats();

        // 操作 6：搜尋存在的資料 (30)
        list.search(30);

        // 操作 7：搜尋不存在的資料 (99)
        list.search(99);

        // 操作 8：刪除 Head 節點 (10)
        list.remove(10);

        // 操作 9：刪除中間節點 (30)
        list.remove(30);

        // 操作 10：刪除不存在的節點 (99)
        list.remove(99);

        System.out.println("\n=== 測試 3：最終狀態檢視 ===");
        list.printList();
        list.printStats();
    }
}