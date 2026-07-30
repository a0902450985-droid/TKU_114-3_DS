public class Q05_RecursiveArrayReport {
    public static void main(String[] args) {
        int[] data = {12, -3, 25, 8, 25, 40, 5};

        System.out.println("10~30 筆數：" + countInRange(data, 0, 10, 30));
        System.out.println("正數總和：" + sumPositive(data, 0));
        System.out.println("25 最後索引：" + findLast(data, 0, 25));
        System.out.println("99 最後索引：" + findLast(data, 0, 99));
    }

    public static int countInRange(int[] data, int index, int minimum, int maximum) {
        // 基本條件：超過陣列長度或空陣列
        if (index >= data.length) {
            return 0;
        }

        int currentCount = (data[index] >= minimum && data[index] <= maximum) ? 1 : 0;
        return currentCount + countInRange(data, index + 1, minimum, maximum);
    }

    public static int sumPositive(int[] data, int index) {
        // 基本條件：超過陣列長度或空陣列
        if (index >= data.length) {
            return 0;
        }

        int currentValue = (data[index] > 0) ? data[index] : 0;
        return currentValue + sumPositive(data, index + 1);
    }

    public static int findLast(int[] data, int index, int target) {
        // 基本條件：超過陣列長度或空陣列
        if (index >= data.length) {
            return -1;
        }

        // 先遞迴往後找
        int laterResult = findLast(data, index + 1, target);

        // 如果後面有找到，就優先回傳後面的索引（因為要找最後一次出現的位置）
        if (laterResult != -1) {
            return laterResult;
        }

        // 後面沒找到，再檢查當前位置
        if (data[index] == target) {
            return index;
        }

        return -1;
    }
}
