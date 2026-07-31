import java.util.ArrayList;

public class RepairAlgorithms {

    // ==================== 1. 依優先等級降冪排序 (Merge Sort - Stable) ====================

    /**
     * 要求 4: 依優先等級降冪；相同等級保持登記順序 (展現 Merge Sort 的穩定性)
     */
    public static void mergeSortByPriorityDesc(RepairTask[] tasks) {
        if (tasks == null || tasks.length <= 1) return;
        RepairTask[] temp = new RepairTask[tasks.length];
        mergeSortByPriorityDesc(tasks, temp, 0, tasks.length - 1);
    }

    private static void mergeSortByPriorityDesc(RepairTask[] tasks, RepairTask[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortByPriorityDesc(tasks, temp, left, mid);
        mergeSortByPriorityDesc(tasks, temp, mid + 1, right);
        mergeByPriorityDesc(tasks, temp, left, mid, right);
    }

    private static void mergeByPriorityDesc(RepairTask[] tasks, RepairTask[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        // 注意: 這裡使用 >= 確保「相同等級時，左邊(較早登記)的先放入」，維持 Stable 順序
        while (i <= mid && j <= right) {
            if (tasks[i].getPriority() >= tasks[j].getPriority()) {
                temp[k++] = tasks[i++];
            } else {
                temp[k++] = tasks[j++];
            }
        }
        while (i <= mid) temp[k++] = tasks[i++];
        while (j <= right) temp[k++] = tasks[j++];

        for (int index = left; index <= right; index++) {
            tasks[index] = temp[index];
        }
    }

    // ==================== 2. 依編號排序 (Binary Search 前置作業) ====================

    public static void mergeSortById(RepairTask[] tasks) {
        if (tasks == null || tasks.length <= 1) return;
        RepairTask[] temp = new RepairTask[tasks.length];
        mergeSortById(tasks, temp, 0, tasks.length - 1);
    }

    private static void mergeSortById(RepairTask[] tasks, RepairTask[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortById(tasks, temp, left, mid);
        mergeSortById(tasks, temp, mid + 1, right);
        mergeById(tasks, temp, left, mid, right);
    }

    private static void mergeById(RepairTask[] tasks, RepairTask[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (tasks[i].getId().compareTo(tasks[j].getId()) <= 0) {
                temp[k++] = tasks[i++];
            } else {
                temp[k++] = tasks[j++];
            }
        }
        while (i <= mid) temp[k++] = tasks[i++];
        while (j <= right) temp[k++] = tasks[j++];

        for (int index = left; index <= right; index++) {
            tasks[index] = temp[index];
        }
    }

    // ==================== 3. 搜尋演算法 (要求 5) ====================

    /**
     * 依編號搜尋 (Binary Search，傳入前需先按 ID 排序)
     */
    public static int binarySearchById(RepairTask[] sortedTasks, String targetId) {
        if (sortedTasks == null || sortedTasks.length == 0) return -1;

        int low = 0;
        int high = sortedTasks.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = targetId.compareTo(sortedTasks[mid].getId());

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

    /**
     * 依設備名稱搜尋 (Sequential Search，可能有多筆相同設備)
     */
    public static ArrayList<RepairTask> searchByDeviceName(ArrayList<RepairTask> allTasks, String deviceName) {
        ArrayList<RepairTask> results = new ArrayList<>();
        if (allTasks == null) return results;

        for (RepairTask task : allTasks) {
            if (task.getDeviceName().equalsIgnoreCase(deviceName)) {
                results.add(task);
            }
        }
        return results;
    }

    /**
     * 檢查編號重複
     */
    public static boolean isDuplicateId(ArrayList<RepairTask> tasks, String id) {
        if (tasks == null) return false;
        for (RepairTask task : tasks) {
            if (task.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}