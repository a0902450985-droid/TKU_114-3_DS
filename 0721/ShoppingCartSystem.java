import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartSystem {

    private static final List<CartItem> cart = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 預設預載 1 筆商品資料
        cart.add(new CartItem("P01", "Apple", 30, 2));

        while (true) {
            printMenu();
            System.out.print("請選擇操作選項 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addOrUpdateItem();
                    break;
                case "2":
                    modifyQuantity();
                    break;
                case "3":
                    removeItem();
                    break;
                case "4":
                    calculateTotal();
                    break;
                case "5":
                    listCart();
                    break;
                case "6":
                    System.out.println("感謝使用購物車系統，結帳再見！");
                    scanner.close();
                    return;
                default:
                    System.out.println("【錯誤】無效選項，請輸入 1 到 6。");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== 購物車系統 ===");
        System.out.println("1. 加入商品至購物車 (重複代碼自動累加)");
        System.out.println("2. 修改商品數量");
        System.out.println("3. 移除商品");
        System.out.println("4. 計算購物車總額");
        System.out.println("5. 查看購物車內容");
        System.out.println("6. 離開系統");
        System.out.println("==================");
    }

    // 1. 新增/重複加入商品
    private static void addOrUpdateItem() {
        System.out.print("請輸入商品代碼: ");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("【失敗】商品代碼不得為空白！");
            return;
        }

        int quantity = readPositiveInt("請輸入購買數量: ");
        if (quantity <= 0) {
            System.out.println("【失敗】新增數量必須大於 0！");
            return;
        }

        // 檢查是否已有相同代碼的商品
        CartItem existingItem = findItemByCode(code);

        if (existingItem != null) {
            // 已存在：增加數量，不建立重複物件
            existingItem.addQuantity(quantity);
            System.out.println("【累加成功】商品「" + existingItem.getName() + "」已存在，數量增加 " 
                    + quantity + "，目前總數: " + existingItem.getQuantity());
        } else {
            // 不存在：輸入名稱與單價，建立新物件
            System.out.print("請輸入商品名稱: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("【失敗】商品名稱不得為空白！");
                return;
            }

            int price = readPositiveInt("請輸入商品單價: ");
            if (price < 0) {
                System.out.println("【失敗】單價不可為負數！");
                return;
            }

            cart.add(new CartItem(code, name, price, quantity));
            System.out.println("【成功】商品「" + name + "」已成功加入購物車！");
        }
    }

    // 2. 修改數量 (數量 <= 0 時不接受更新)
    private static void modifyQuantity() {
        System.out.print("請輸入要修改的商品代碼: ");
        String code = scanner.nextLine().trim();

        CartItem item = findItemByCode(code);
        if (item == null) {
            System.out.println("【失敗】購物車中找不到代碼為「" + code + "」的商品！");
            return;
        }

        System.out.println("當前商品: " + item.getName() + " | 當前數量: " + item.getQuantity());
        int newQuantity = readPositiveInt("請輸入新的數量 (必須 > 0): ");

        // 核心要求：數量 <= 0 不接受更新
        if (item.setQuantity(newQuantity)) {
            System.out.println("【成功】已將「" + item.getName() + "」數量更新為: " + newQuantity);
        } else {
            System.out.println("【更新失敗】數量必須大於 0，未對購物車進行任何更新！");
        }
    }

    // 3. 移除商品
    private static void removeItem() {
        System.out.print("請輸入要移除的商品代碼: ");
        String code = scanner.nextLine().trim();

        CartItem item = findItemByCode(code);
        if (item != null) {
            cart.remove(item);
            System.out.println("【成功】已將「" + item.getName() + "」從購物車移除！");
        } else {
            System.out.println("【失敗】購物車中找不到代碼為「" + code + "」的商品！");
        }
    }

    // 4. 計算總額
    private static void calculateTotal() {
        if (cart.isEmpty()) {
            System.out.println("【提示】購物車是空的，總金額為 $0");
            return;
        }

        int total = 0;
        for (CartItem item : cart) {
            total += item.getSubtotal();
        }

        System.out.println("--- 購物車結算 ---");
        System.out.println("商品總筆數: " + cart.size());
        System.out.println("購物車總金額: $" + total);
    }

    // 5. 列出購物車清單
    private static void listCart() {
        if (cart.isEmpty()) {
            System.out.println("【提示】購物車目前是空的。");
            return;
        }

        System.out.println("--- 購物車內容清單 ---");
        for (CartItem item : cart) {
            System.out.println(item);
        }
    }

    // 輔助 Method 1：依代碼尋找商品 (忽略大小寫)
    private static CartItem findItemByCode(String code) {
        if (code == null || code.isEmpty()) return null;
        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(code)) {
                return item;
            }
        }
        return null;
    }

    // 輔助 Method 2：讀取整數並防呆 (防止輸入非數字崩潰)
    private static int readPositiveInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("【錯誤】請輸入有效的整數！");
            scanner.next(); // 清除無效輸入
            System.out.print(prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // 吃掉換行符
        return value;
    }
}
