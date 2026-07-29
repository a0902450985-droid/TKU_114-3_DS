public class ContestRankingSystem {

    public static void main(String[] args) {
        // 準備測試資料
        Contestant[] contestants = {
            new Contestant("C001", "Alice", 85, 120),
            new Contestant("C002", "Bob", 95, 150),
            new Contestant("C003", "Charlie", 85, 100), // 與 Alice 同分，但秒數較少
            new Contestant("C004", "David", 90, 110),
            new Contestant("C005", "Eve", 95, 130)      // 與 Bob 同分，但秒數較少
        };

        System.out.println("=== 排序前 ===");
        printContestants(contestants);

        // 使用手寫的插入排序進行排名
        insertionSort(contestants);

        System.out.println("\n=== 最終排名榜 ===");
        printRankings(contestants);
    }

    /**
     * 手寫實作插入排序 (Insertion Sort)
     * 排序規則：
     * 1. 分數高者排前面 (score 降冪)
     * 2. 分數相同時，完成秒數少者排前面 (seconds 升冪)
     */
    public static void insertionSort(Contestant[] array) {
        for (int i = 1; i < array.length; i++) {
            Contestant key = array[i];
            int j = i - 1;

            // 當前一個元素 (array[j]) 應該排在 key 後面時，將其往後移
            while (j >= 0 && shouldSwap(array[j], key)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    /**
     * 判斷 prev 元素是否比 current 元素「優先順序低」（也就是 prev 是否應該移到 current 後面）
     */
    private static boolean shouldSwap(Contestant prev, Contestant current) {
        // 規則 1：前者的分數比後者低 -> 應該往後移
        if (prev.getScore() < current.getScore()) {
            return true;
        }
        // 規則 2：分數相等時，前者的秒數比後者多 -> 應該往後移
        else if (prev.getScore() == current.getScore()) {
            return prev.getSeconds() > current.getSeconds();
        }
        
        return false;
    }

    // 印出原始資料
    public static void printContestants(Contestant[] contestants) {
        for (Contestant c : contestants) {
            System.out.println(c);
        }
    }

    // 印出包含名次的最終結果
    public static void printRankings(Contestant[] contestants) {
        for (int i = 0; i < contestants.length; i++) {
            System.out.printf("第 %d 名 | %s%n", (i + 1), contestants[i]);
        }
    }
}