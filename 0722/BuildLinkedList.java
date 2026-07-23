// 節點類別 (Node)
class Node {
    int data;
    Node next;

    // 建構子
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class BuildLinkedList {

    // 1. 由 head 走訪並輸出鏈結串列
    public static void printList(Node head) {
        // 處理空白串列 (Edge Case)
        if (head == null) {
            System.out.println("串列為空！");
            return;
        }

        System.out.print("鏈結串列內容: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    // 2. 計算節點總數與數值總和
    public static void calculateCountAndSum(Node head) {
        if (head == null) {
            System.out.println("串列為空，節點數：0，總和：0");
            return;
        }

        int count = 0;
        int sum = 0;
        Node current = head;

        while (current != null) {
            count++;
            sum += current.data;
            current = current.next;
        }

        System.out.println("節點總數: " + count);
        System.out.println("數值總和: " + sum);
    }

    // 3. 查找資料功能
    public static boolean search(Node head, int target) {
        if (head == null) {
            System.out.println("串列為空，無法進行查找。");
            return false;
        }

        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == target) {
                System.out.println("找到資料 " + target + "，位於第 " + index + " 個索引位置。");
                return true;
            }
            current = current.next;
            index++;
        }

        System.out.println("未找到資料: " + target);
        return false;
    }

    public static void main(String[] args) {
        // 要求 1: 建立 10、20、30、40 四個節點
        Node node1 = new Node(10);
        Node node2 = new Node(20);
        Node node3 = new Node(30);
        Node node4 = new Node(40);

        // 要求 2: 正確連接節點
        Node head = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println("=== 測試 1: 正常鏈結串列操作 ===");
        // 要求 3: 由 head 走訪輸出
        printList(head);

        // 要求 4: 計算節點數與總和
        calculateCountAndSum(head);

        System.out.println("\n=== 測試 2: 查找資料功能 ===");
        search(head, 30); // 存在的資料
        search(head, 50); // 不存在的資料

        System.out.println("\n=== 測試 3: 空白串列處理 ===");
        Node emptyHead = null;
        printList(emptyHead);
        calculateCountAndSum(emptyHead);
        search(emptyHead, 10);
    }
}