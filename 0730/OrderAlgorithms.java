import java.util.ArrayList;

public class OrderAlgorithms {

    // ==================== 1. ID 排序與搜尋 (您提供的程式碼) ====================

    public static void mergeSortById(Order[] orders) {
        if (orders == null || orders.length == 0) return;
        Order[] temp = new Order[orders.length];
        mergeSortById(orders, temp, 0, orders.length - 1);
    }

    private static void mergeSortById(Order[] orders, Order[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortById(orders, temp, left, mid);
        mergeSortById(orders, temp, mid + 1, right);
        mergeById(orders, temp, left, mid, right);
    }

    private static void mergeById(Order[] orders, Order[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (orders[i].getId().compareTo(orders[j].getId()) <= 0) {
                temp[k++] = orders[i++];
            } else {
                temp[k++] = orders[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = orders[i++];
        }
        while (j <= right) {
            temp[k++] = orders[j++];
        }
        for (int index = left; index <= right; index++) {
            orders[index] = temp[index];
        }
    }

    public static int binarySearchById(Order[] orders, String targetId) {
        int low = 0;
        int high = orders.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = targetId.compareTo(orders[mid].getId());

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static ArrayList<Order> findByCustomer(ArrayList<Order> orders, String customer) {
        ArrayList<Order> results = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomer().equalsIgnoreCase(customer)) {
                results.add(order);
            }
        }
        return results;
    }

    // ==================== 2. 題目四擴充需求 (依金額降冪與重複檢查) ====================

    /**
     * 功能要求 1：依「金額降冪（由大到小）」進行 Merge Sort
     */
    public static void mergeSortByAmountDescending(Order[] orders) {
        if (orders == null || orders.length == 0) return;
        Order[] temp = new Order[orders.length];
        mergeSortByAmountDescending(orders, temp, 0, orders.length - 1);
    }

    private static void mergeSortByAmountDescending(Order[] orders, Order[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortByAmountDescending(orders, temp, left, mid);
        mergeSortByAmountDescending(orders, temp, mid + 1, right);
        mergeByAmountDescending(orders, temp, left, mid, right);
    }

    private static void mergeByAmountDescending(Order[] orders, Order[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        // >= 實現降冪排序 (金額大的排前面)
        while (i <= mid && j <= right) {
            if (orders[i].getAmount() >= orders[j].getAmount()) {
                temp[k++] = orders[i++];
            } else {
                temp[k++] = orders[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = orders[i++];
        }
        while (j <= right) {
            temp[k++] = orders[j++];
        }
        for (int index = left; index <= right; index++) {
            orders[index] = temp[index];
        }
    }

    /**
     * 功能要求 4：檢查重複訂單編號
     */
    public static boolean isDuplicateId(ArrayList<Order> orders, String id) {
        for (Order order : orders) {
            if (order.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}