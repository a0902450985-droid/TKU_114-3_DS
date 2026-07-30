public class Q06_BinarySearchRange {
    public static void main(String[] args) {
        int[] data = {5, 10, 10, 10, 18, 25, 25, 40};

        System.out.println("10 第一次：" + findFirst(data, 10));
        System.out.println("10 最後一次：" + findLast(data, 10));
        System.out.println("10 出現次數：" + countOccurrences(data, 10));
        System.out.println("99 第一次：" + findFirst(data, 99));
    }

    public static int findFirst(int[] data, int target) {
        int left = 0;
        int right = data.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (data[mid] == target) {
                result = mid;       // 記錄暫存答案
                right = mid - 1;    // 繼續往左邊搜尋更早出現的位置
            } else if (data[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public static int findLast(int[] data, int target) {
        int left = 0;
        int right = data.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (data[mid] == target) {
                result = mid;       // 記錄暫存答案
                left = mid + 1;     // 繼續往右邊搜尋更晚出現的位置
            } else if (data[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public static int countOccurrences(int[] data, int target) {
        int first = findFirst(data, target);
        if (first == -1) {
            return 0; // 找不到目標值，出現 0 次
        }
        int last = findLast(data, target);
        return last - first + 1;
    }
}