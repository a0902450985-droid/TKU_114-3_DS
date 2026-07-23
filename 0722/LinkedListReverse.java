// 節點類別 (Node)
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListReverse {

    // 1. 逆向方法 (In-place Reversal)
    // 2. 不建立第二條串列，直接在原串列上修改指標
    public static Node reverse(Node head) {
        Node prev = null;       // 前一個節點
        Node current = head;    // 當前節點
        Node next = null;       // 下一個節點

        while (current != null) {
            next = current.next; // 暫存下一個節點，避免斷鏈
            current.next = prev; // 將當前節點的反向指標指向前一個節點
            prev = current;      // prev 往前移動
            current = next;      // current 往前移動
        }

        // prev 最後會指向原串列的尾節點，亦即反轉後的新 head
        return prev;
    }

    // 輔助方法：印出完整鏈結串列
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
        
        System.out.println("=== 測試 1：空白串列 (Null List) ===");
        Node emptyHead = null;
        System.out.print("反轉前: ");
        printList(emptyHead);
        emptyHead = reverse(emptyHead);
        System.out.print("反轉後: ");
        printList(emptyHead);

        System.out.println("\n=== 測試 2：單一節點串列 (Single Node) ===");
        Node singleHead = new Node(100);
        System.out.print("反轉前: ");
        printList(singleHead);
        singleHead = reverse(singleHead);
        System.out.print("反轉後: ");
        printList(singleHead);

        System.out.println("\n=== 測試 3：多節點串列 (Multiple Nodes) ===");
        Node multiHead = new Node(10);
        multiHead.next = new Node(20);
        multiHead.next.next = new Node(30);
        multiHead.next.next.next = new Node(40);
        multiHead.next.next.next.next = new Node(50);

        System.out.print("反轉前: ");
        printList(multiHead);
        multiHead = reverse(multiHead);
        System.out.print("反轉後: ");
        printList(multiHead);
    }
}
