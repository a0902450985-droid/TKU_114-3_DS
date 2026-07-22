import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NameListManager {

    private static final List<String> nameList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("請選擇操作選項 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addName();
                    break;
                case "2":
                    searchName();
                    break;
                case "3":
                    updateName();
                    break;
                case "4":
                    deleteName();
                    break;
                case "5":
                    listAllNames();
                    break;
                case "6":
                    System.out.println("感謝使用，系統已結束。");
                    scanner.close();
                    return;
                default:
                    System.out.println("【錯誤】無效的選項，請輸入 1 到 6。");
            }
            System.out.println(); // 空行保持介面整潔
        }
    }

    // 列出主選單
    private static void printMenu() {
        System.out.println("=== 名單管理系統 ===");
        System.out.println("1. 新增姓名");
        System.out.println("2. 搜尋姓名");
        System.out.println("3. 修改姓名");
        System.out.println("4. 刪除姓名");
        System.out.println("5. 列出全部姓名");
        System.out.println("6. 離開系統");
        System.out.println("====================");
    }

    // 1. 新增功能
    private static void addName() {
        System.out.print("請輸入要新增的姓名: ");
        String name = scanner.nextLine().trim();

        // 不得加入空白姓名
        if (name.isEmpty()) {
            System.out.println("【失敗】姓名不得為空白！");
            return;
        }

        nameList.add(name);
        System.out.println("【成功】已新增姓名: " + name);
    }

    // 2. 搜尋功能
    private static void searchName() {
        System.out.print("請輸入要搜尋的姓名: ");
        String target = scanner.nextLine().trim();

        if (target.isEmpty()) {
            System.out.println("【錯誤】搜尋關鍵字不得為空白！");
            return;
        }

        int index = findIndexIgnoreCase(target);
        if (index != -1) {
            System.out.println("【找到結果】名單中存在「" + nameList.get(index) + "」（位置第 " + (index + 1) + " 筆）。");
        } else {
            System.out.println("【找不到】名單中無此姓名: " + target);
        }
    }

    // 3. 修改功能
    private static void updateName() {
        System.out.print("請輸入要修改的舊姓名: ");
        String oldName = scanner.nextLine().trim();

        int index = findIndexIgnoreCase(oldName);
        if (index == -1) {
            System.out.println("【修改失敗】找不到姓名: " + oldName);
            return;
        }

        System.out.print("請輸入新的姓名: ");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("【修改失敗】新姓名不得為空白！");
            return;
        }

        String original = nameList.get(index);
        nameList.set(index, newName);
        System.out.println("【成功】已將「" + original + "」修改為「" + newName + "」。");
    }

    // 4. 刪除功能
    private static void deleteName() {
        System.out.print("請輸入要刪除的姓名: ");
        String target = scanner.nextLine().trim();

        int index = findIndexIgnoreCase(target);
        if (index != -1) {
            String removedName = nameList.remove(index);
            System.out.println("【成功】已成功刪除: " + removedName);
        } else {
            System.out.println("【找不到】找不到要刪除的姓名: " + target);
        }
    }

    // 5. 列出全部功能
    private static void listAllNames() {
        if (nameList.isEmpty()) {
            System.out.println("【提示】目前名單為空。");
            return;
        }

        System.out.println("--- 目前所有名單 (" + nameList.size() + " 人) ---");
        for (int i = 0; i < nameList.size(); i++) {
            System.out.println((i + 1) + ". " + nameList.get(i));
        }
    }

    // 輔助 Method：不分大小寫比對姓名，傳回在 ArrayList 中的索引 (找不到傳回 -1)
    private static int findIndexIgnoreCase(String targetName) {
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(targetName)) {
                return i;
            }
        }
        return -1;
    }
}