// 節點類別 (Node)
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListSearchRemove {

    // 1. 完成 contains 方法：搜尋特定數值是否存在
    public static boolean contains(Node head, int target) {
        Node current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 1. 完成 removeValue 方法：刪除指定的數值節點，並回傳最新的 head
    public static Node removeValue(Node head, int target) {
        // 邊界條件：空白串列處理
        if (head == null) {
            System.out.println("串列為空，無法刪除 " + target);
            return null;
        }

        // 情況 A：要刪除的是 head 節點
        if (head.data == target) {
            System.out.println("成功刪除 Head 節點: " + target);
            return head.next; // 將 head 指向下一個節點
        }

        // 情況 B & C：刪除中間或尾端的節點
        Node current = head;
        while (current.next != null && current.next.data != target) {
            current = current.next;
        }

        // 情況 D：找不到該數值
        if (current.next == null) {
            System.out.println("找不到數值 " + target + "，未進行刪除。");
            return head;
        }

        // 找到節點並進行跳接刪除
        System.out.println("成功刪除節點: " + target);
        current.next = current.next.next;

        return head;
    }

    // 輔助方法：輸出完整鏈結串列
    public static void printList(Node head) {
        if (head == null) {
            System.out.println("鏈結串列內容: [ 空白串列 ]");
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

    public static void main(String[] args) {
        // 建立初始串列： 10 -> 20 -> 30 -> 40 -> 50
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);

        System.out.println("=== 初始狀態 ===");
        printList(head);

        System.out.println("\n=== 測試 1：包含 (contains) 檢查 ===");
        System.out.println("是否包含 30: " + contains(head, 30)); // true
        System.out.println("是否包含 99: " + contains(head, 99)); // false

        System.out.println("\n=== 測試 2：刪除 Head 節點 (10) ===");
        head = removeValue(head, 10);
        printList(head);

        System.out.println("\n=== 測試 3：刪除中間節點 (30) ===");
        head = removeValue(head, 30);
        printList(head);

        System.out.println("\n=== 測試 4：刪除最後節點 (50) ===");
        head = removeValue(head, 50);
        printList(head);

        System.out.println("\n=== 測試 5：刪除找不到的數值 (99) ===");
        head = removeValue(head, 99);
        printList(head);

        System.out.println("\n=== 測試 6：邊界情況（對空白串列操作） ===");
        Node emptyHead = null;
        emptyHead = removeValue(emptyHead, 10);
        printList(emptyHead);
    }
}
