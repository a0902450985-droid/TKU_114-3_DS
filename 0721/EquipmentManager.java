import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EquipmentManager {

    private static final List<Equipment> equipmentList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 預先加入一些測試資料
        equipmentList.add(new Equipment("E01", "Laptop"));
        equipmentList.add(new Equipment("E02", "Projector"));

        while (true) {
            printMenu();
            System.out.print("請選擇操作選項 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addEquipment();
                    break;
                case "2":
                    searchEquipmentById();
                    break;
                case "3":
                    borrowEquipment();
                    break;
                case "4":
                    returnEquipment();
                    break;
                case "5":
                    listAvailableEquipment();
                    break;
                case "6":
                    System.out.println("感謝使用設備管理系統，再見！");
                    scanner.close();
                    return;
                default:
                    System.out.println("【錯誤】無效選項，請輸入 1 到 6。");
            }
            System.out.println(); // 保持畫面整潔
        }
    }

    private static void printMenu() {
        System.out.println("=== 設備物件集合管理系統 ===");
        System.out.println("1. 新增設備");
        System.out.println("2. 依代碼搜尋設備");
        System.out.println("3. 借出設備");
        System.out.println("4. 歸還設備");
        System.out.println("5. 列出所有可借用設備");
        System.out.println("6. 離開系統");
        System.out.println("============================");
    }

    // 1. 新增設備 (含代碼重複檢查)
    private static void addEquipment() {
        System.out.print("請輸入設備代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("【失敗】設備代碼不得為空白！");
            return;
        }

        // 檢查代碼是否已存在（不分大小寫）
        if (findEquipmentById(id) != null) {
            System.out.println("【失敗】設備代碼「" + id + "」已存在，代碼不可重複！");
            return;
        }

        System.out.print("請輸入設備名稱: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("【失敗】設備名稱不得為空白！");
            return;
        }

        equipmentList.add(new Equipment(id, name));
        System.out.println("【成功】已成功新增設備: " + name + " (代碼: " + id + ")");
    }

    // 2. 依代碼搜尋設備
    private static void searchEquipmentById() {
        System.out.print("請輸入要搜尋的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment eq = findEquipmentById(id);
        if (eq != null) {
            System.out.println("【找到設備】 " + eq);
        } else {
            System.out.println("【找不到】未找到代碼為「" + id + "」的設備。");
        }
    }

    // 3. 借出設備
    private static void borrowEquipment() {
        System.out.print("請輸入要借出的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment eq = findEquipmentById(id);
        if (eq == null) {
            System.out.println("【借出失敗】找不到此設備代碼！");
            return;
        }

        if (!eq.isAvailable()) {
            System.out.println("【借出失敗】設備「" + eq.getName() + "」目前已被借出中！");
        } else {
            eq.setAvailable(false);
            System.out.println("【借出成功】您已成功借出「" + eq.getName() + "」。");
        }
    }

    // 4. 歸還設備
    private static void returnEquipment() {
        System.out.print("請輸入要歸還的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment eq = findEquipmentById(id);
        if (eq == null) {
            System.out.println("【歸還失敗】找不到此設備代碼！");
            return;
        }

        if (eq.isAvailable()) {
            System.out.println("【歸還失敗】設備「" + eq.getName() + "」本來就是可借用狀態（未被借出）！");
        } else {
            eq.setAvailable(true);
            System.out.println("【歸還成功】您已成功歸還「" + eq.getName() + "」。");
        }
    }

    // 5. 列出可借用設備
    private static void listAvailableEquipment() {
        System.out.println("--- 目前可借用的設備清單 ---");
        boolean foundAny = false;

        for (Equipment eq : equipmentList) {
            if (eq.isAvailable()) {
                System.out.println(eq);
                foundAny = true;
            }
        }

        if (!foundAny) {
            System.out.println("【提示】目前沒有可借用的設備。");
        }
    }

    // 輔助 Method：透過代碼搜尋設備 (不分大小寫)，找不到回傳 null
    private static Equipment findEquipmentById(String id) {
        if (id == null || id.isEmpty()) return null;
        for (Equipment eq : equipmentList) {
            if (eq.getId().equalsIgnoreCase(id)) {
                return eq;
            }
        }
        return null;
    }
}