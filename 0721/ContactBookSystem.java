import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactBookSystem {

    private static final List<Contact> contactList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 預設預載 1 筆測試資料
        contactList.add(new Contact("C01", "Alice", "0912345678", "alice@example.com"));

        while (true) {
            printMenu();
            System.out.print("請選擇操作選項 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addContact();
                    break;
                case "2":
                    searchContact();
                    break;
                case "3":
                    updatePhone();
                    break;
                case "4":
                    deleteContact();
                    break;
                case "5":
                    listAllContacts();
                    break;
                case "6":
                    System.out.println("感謝使用聯絡人管理系統，再見！");
                    scanner.close();
                    return;
                default:
                    System.out.println("【錯誤】無效選項，請輸入 1 到 6。");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== 聯絡人管理系統 ===");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人 (依代碼或姓名)");
        System.out.println("3. 修改聯絡人電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 列出完整清單");
        System.out.println("6. 離開系統");
        System.out.println("======================");
    }

    // 自訂 Method 1：新增聯絡人
    public static void addContact() {
        System.out.print("請輸入代碼: ");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("【失敗】代碼不得為空白！");
            return;
        }

        if (findContactByCode(code) != null) {
            System.out.println("【失敗】代碼「" + code + "」已存在，代碼不可重複！");
            return;
        }

        System.out.print("請輸入姓名: ");
        String name = scanner.nextLine().trim();

        // 檢查空白姓名不可加入
        if (name.isEmpty()) {
            System.out.println("【失敗】姓名不得為空白！");
            return;
        }

        System.out.print("請輸入電話: ");
        String phone = scanner.nextLine().trim();

        System.out.print("請輸入 Email: ");
        String email = scanner.nextLine().trim();

        contactList.add(new Contact(code, name, phone, email));
        System.out.println("【成功】已成功新增聯絡人: " + name);
    }

    // 自訂 Method 2：搜尋聯絡人
    public static void searchContact() {
        System.out.print("請輸入要搜尋的代碼或姓名: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("【錯誤】關鍵字不得為空白！");
            return;
        }

        boolean found = false;
        for (Contact contact : contactList) {
            if (contact.getCode().equalsIgnoreCase(keyword) || 
                contact.getName().equalsIgnoreCase(keyword)) {
                System.out.println("【找到結果】 " + contact);
                found = true;
            }
        }

        if (!found) {
            System.out.println("【找不到】未找到符合「" + keyword + "」的聯絡人。");
        }
    }

    // 自訂 Method 3：修改電話
    public static void updatePhone() {
        System.out.print("請輸入要修改電話的聯絡人代碼: ");
        String code = scanner.nextLine().trim();

        Contact contact = findContactByCode(code);
        if (contact == null) {
            System.out.println("【修改失敗】找不到代碼為「" + code + "」的聯絡人！");
            return;
        }

        System.out.println("當前聯絡人: " + contact.getName() + " | 原電話: " + contact.getPhone());
        System.out.print("請輸入新電話: ");
        String newPhone = scanner.nextLine().trim();

        contact.setPhone(newPhone);
        System.out.println("【成功】電話已更為: " + newPhone);
    }

    // 自訂 Method 4：刪除聯絡人
    public static void deleteContact() {
        System.out.print("請輸入要刪除的聯絡人代碼: ");
        String code = scanner.nextLine().trim();

        Contact contact = findContactByCode(code);
        if (contact != null) {
            contactList.remove(contact);
            System.out.println("【成功】已成功刪除聯絡人: " + contact.getName());
        } else {
            System.out.println("【刪除失敗】找不到代碼為「" + code + "」的聯絡人！");
        }
    }

    // 自訂 Method 5：列出完整清單
    public static void listAllContacts() {
        if (contactList.isEmpty()) {
            System.out.println("【提示】目前通訊錄為空。");
            return;
        }

        System.out.println("--- 完整聯絡人清單 (" + contactList.size() + " 人) ---");
        for (Contact contact : contactList) {
            System.out.println(contact);
        }
    }

    // 輔助 Method：依據代碼尋找聯絡人（輔助防呆與修改，不分大小寫）
    private static Contact findContactByCode(String code) {
        if (code == null || code.isEmpty()) return null;
        for (Contact contact : contactList) {
            if (contact.getCode().equalsIgnoreCase(code)) {
                return contact;
            }
        }
        return null;
    }
}