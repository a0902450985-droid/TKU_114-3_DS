public class TransactionSortingSystem {

    public static void main(String[] args) {
        // 1. 建立測試交易資料（故意包含金額相同的交易，以測試規則 3）
        Transaction[] transactions = {
            new Transaction("TX1001", "User_A", 5000, 3),
            new Transaction("TX1002", "User_B", 12000, 1),
            new Transaction("TX1003", "User_C", 5000, 1), // 與 TX1001 同金額，時間序號較早
            new Transaction("TX1004", "User_D", 20000, 2),
            new Transaction("TX1005", "User_E", 5000, 2), // 與 TX1001, TX1003 同金額，時間序號居中
            new Transaction("TX1006", "User_F", 12000, 4)  // 與 TX1002 同金額，時間序號較晚
        };

        System.out.println("=== 原始交易紀錄 ===");
        printTransactions(transactions);

        // 執行排序
        insertionSort(transactions);

        // 5. 顯示排序領先結果且測試金額相同的情況
        System.out.println("\n=== 排序後交易紀錄 (金額降冪 -> 時間序號升冪) ===");
        printRankedTransactions(transactions);
    }

    /**
     * 手寫插入排序法 (Insertion Sort)
     */
    public static void insertionSort(Transaction[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Transaction key = arr[i];
            int j = i - 1;

            // 當前面的元素 (arr[j]) 應該位移至 key 後面時，執行搬移
            while (j >= 0 && shouldSwap(arr[j], key)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * 排序邏輯判斷：
     * 2. 金額降冪：金額較小者應往後移
     * 3. 金額相同時依時間序號升冪：時間序號較大（較晚）者應往後移
     */
    private static boolean shouldSwap(Transaction prev, Transaction current) {
        // 規則 2：前者的金額比後者小 -> 應往後移 (金額降冪)
        if (prev.getAmount() < current.getAmount()) {
            return true;
        } 
        // 規則 3：金額相同時，前者的時間序號比後者大 -> 應往後移 (時間序號升冪)
        else if (prev.getAmount() == current.getAmount()) {
            return prev.getTimestamp() > current.getTimestamp();
        }

        return false;
    }

    // 印出原始資料
    public static void printTransactions(Transaction[] transactions) {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    // 印出排序後附帶名次的結果
    public static void printRankedTransactions(Transaction[] transactions) {
        for (int i = 0; i < transactions.length; i++) {
            System.out.printf("排序 [%d] | %s%n", (i + 1), transactions[i]);
        }
    }
}
