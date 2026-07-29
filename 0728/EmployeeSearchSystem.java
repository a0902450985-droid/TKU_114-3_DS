import java.util.Scanner;

public class EmployeeSearchSystem {

    public static void main(String[] args) {
        // 1. & 2. 建立員工陣列，並且資料「必須依照編號由小到大排序」
        // 註【重複編號處理說明】：二分查找適用於主鍵（Unique Key）查詢。
        // 若系統允許重複編號，二分查找僅能命中其中一筆；因此在系統建置時應確保編號唯一性。
        Employee[] employees = {
            new Employee(1001, "Alice", "研發部", "1201"),
            new Employee(1005, "Bob",   "市場部", "1302"),
            new Employee(1010, "Charlie", "財務部", "1405"),
            new Employee(1012, "David", "人事部", "1108"),
            new Employee(1020, "Eve",   "資訊部", "1500")
        };

        // 顯示現有員工資料
        System.out.println("=== 系統現有員工資料（已按編號排序）===");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
        System.out.println("========================================\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要查詢的員工編號：");
        String input = scanner.nextLine().trim();

        // 5. 【空白/無效輸入處理】
        if (input.isEmpty()) {
            System.out.println("\n【錯誤】輸入不可為空白，請輸入有效的數字編號！");
            scanner.close();
            return;
        }

        int targetId;
        try {
            targetId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("\n【錯誤】輸入格式不正確，請輸入純數字編號！");
            scanner.close();
            return;
        }

        // 3. 使用二分查找依編號查詢
        int foundIndex = binarySearch(employees, targetId);

        // 4. 找到後顯示完整員工資料 / 5. 【缺乏（查無資料）處理】
        System.out.println("\n=== 查詢結果 ===");
        if (foundIndex != -1) {
            System.out.println("【成功找到員工】");
            System.out.println(employees[foundIndex]);
        } else {
            System.out.println("【搜尋失敗】查無編號為 " + targetId + " 的員工資料。");
        }

        scanner.close();
    }

    /**
     * 二分查找方法 (Binary Search)
     */
    public static int binarySearch(Employee[] employees, int targetId) {
        int low = 0;
        int high = employees.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (employees[mid].getId() == targetId) {
                return mid; // 找到資料，回傳索引
            } else if (employees[mid].getId() < targetId) {
                low = mid + 1; // 目標在右半邊
            } else {
                high = mid - 1; // 目標在左半邊
            }
        }

        return -1; // 缺乏資料，回傳 -1
    }
}
