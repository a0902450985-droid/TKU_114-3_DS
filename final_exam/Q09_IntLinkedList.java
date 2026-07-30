import java.util.Arrays;

public class Q09_IntLinkedList {
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public static void main(String[] args) {
        Q09_IntLinkedList list = new Q09_IntLinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(20);

        System.out.println("原串列：" + Arrays.toString(list.toArray()));
        System.out.println("刪除 20：" + list.removeFirstOccurrence(20));
        System.out.println("刪除後：" + Arrays.toString(list.toArray()));

        list.reverse();
        System.out.println("反轉後：" + Arrays.toString(list.toArray()));
        System.out.println("size：" + list.size());
    }

    public void addLast(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public boolean removeFirstOccurrence(int target) {
        if (head == null) {
            return false;
        }

        // 處理刪除頭節點的情況
        if (head.data == target) {
            head = head.next;
            size--;
            return true;
        }

        // 處理刪除中間或尾端節點的情況
        Node current = head;
        while (current.next != null) {
            if (current.next.data == target) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;  // 暫存下一個節點
            current.next = prev;  // 將指標反轉
            prev = current;       // prev 前進
            current = next;       // current 前進
        }

        head = prev; // 更新 head 到新的頭節點
    }

    public int[] toArray() {
        int[] result = new int[size];
        Node current = head;
        int index = 0;

        while (current != null) {
            result[index++] = current.data;
            current = current.next;
        }

        return result;
    }

    public int size() {
        return size;
    }
}
