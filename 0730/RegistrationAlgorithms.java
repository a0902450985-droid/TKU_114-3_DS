import java.util.ArrayList;

public class RegistrationAlgorithms {

    // ==================== 1. Merge Sort: 依報名編號排序 (要求 4) ====================

    public static void mergeSortById(Registration[] regArray) {
        if (regArray == null || regArray.length <= 1) return;
        Registration[] temp = new Registration[regArray.length];
        mergeSortById(regArray, temp, 0, regArray.length - 1);
    }

    private static void mergeSortById(Registration[] regArray, Registration[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortById(regArray, temp, left, mid);
        mergeSortById(regArray, temp, mid + 1, right);
        mergeById(regArray, temp, left, mid, right);
    }

    private static void mergeById(Registration[] regArray, Registration[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (regArray[i].getId().compareTo(regArray[j].getId()) <= 0) {
                temp[k++] = regArray[i++];
            } else {
                temp[k++] = regArray[j++];
            }
        }
        while (i <= mid) temp[k++] = regArray[i++];
        while (j <= right) temp[k++] = regArray[j++];

        for (int index = left; index <= right; index++) {
            regArray[index] = temp[index];
        }
    }

    // ==================== 2. Binary Search: 依編號查詢 (要求 5) ====================

    public static int binarySearchById(Registration[] sortedArray, String targetId) {
        if (sortedArray == null || sortedArray.length == 0) return -1;

        int low = 0;
        int high = sortedArray.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = targetId.compareTo(sortedArray[mid].getId());

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

    // ==================== 3. Sequential Search: 依姓名查詢 (要求 5) ====================

    public static ArrayList<Registration> searchByName(ArrayList<Registration> list, String name) {
        ArrayList<Registration> results = new ArrayList<>();
        if (list == null) return results;

        for (Registration reg : list) {
            if (reg.getName().equalsIgnoreCase(name)) {
                results.add(reg);
            }
        }
        return results;
    }

    // ==================== 4. 檢查編號是否重複 (要求 6) ====================

    public static boolean isDuplicateId(ArrayList<Registration> list, String id) {
        if (list == null) return false;
        for (Registration reg : list) {
            if (reg.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}