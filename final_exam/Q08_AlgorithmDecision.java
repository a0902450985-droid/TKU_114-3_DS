public class Q08_AlgorithmDecision {
    public static void main(String[] args) {
        int[] data = new int[64];
        for (int index = 0; index < data.length; index++) {
            data[index] = (index + 1) * 3;
        }

        System.out.println("已排序：" + isSortedAscending(data));
        System.out.println("循序比較次數：" + sequentialChecks(data, 192));
        System.out.println("二分比較次數：" + binaryChecks(data, 192));
        System.out.println("建議：" + chooseSearch(true, data.length, 5));
    }

    public static boolean isSortedAscending(int[] data) {
        if (data == null || data.length <= 1) {
            return true; // 空陣列或單一元素視為已排序
        }

        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] > data[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static int sequentialChecks(int[] data, int target) {
        if (data == null || data.length == 0) {
            return 0; // 空陣列比較次數為 0
        }

        int checks = 0;
        for (int value : data) {
            checks++;
            if (value == target) {
                break;
            }
        }
        return checks;
    }

    public static int binaryChecks(int[] data, int target) {
        if (data == null || data.length == 0) {
            return 0; // 空陣列比較次數為 0
        }

        int left = 0;
        int right = data.length - 1;
        int checks = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            checks++; // 每次比對 data[mid] 與 target 計數一次

            if (data[mid] == target) {
                break;
            } else if (data[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return checks;
    }

    public static String chooseSearch(boolean sorted, int dataSize, int expectedSearches) {
        // 資料已排序、資料量 >= 32 筆，且預計搜尋至少 2 次時建議使用 BINARY
        if (sorted && dataSize >= 32 && expectedSearches >= 2) {
            return "BINARY";
        }
        return "SEQUENTIAL";
    }
}
