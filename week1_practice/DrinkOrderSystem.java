import java.util.Scanner;

public class DrinkOrderSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 各商品銷售杯數統計 (計數器)
        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;

        // 總杯數與折扣前總金額 (累加器)
        int totalItems = 0;
        int totalAmount = 0;

        while (true) {
            // 1. 重複顯示商品選單
            printMenu();
            System.out.print("請選擇商品選項 (0-4): ");
            int option = sc.nextInt();

            // 9. 選擇 0 時結帳
            if (option == 0) {
                break;
            }

            // 輸入驗證：選項必須在 1-4 之間
            if (option < 1 || option > 4) {
                System.out.println("無效的選項，請重新選擇！\n");
                continue;
            }

            // 3 & 4. 獲取合法數量 (大於 0)
            int quantity = readValidQuantity(sc);

            // 統計每一種商品賣出杯數
            if (option == 1) blackTeaCount += quantity;
            else if (option == 2) greenTeaCount += quantity;
            else if (option == 3) milkTeaCount += quantity;
            else if (option == 4) coffeeCount += quantity;

            // 5. 計算本次小計
            int price = getPrice(option);
            int subtotal = calculateSubtotal(price, quantity);

            // 6 & 7. 累加總杯數與折扣前總金額
            totalItems += quantity;
            totalAmount += subtotal;

            System.out.println("已加入購物車：" + getItemName(option) + " x " + quantity + " 杯，小計：" + subtotal + " 元\n");
        }

        // 10. 計算折扣後總金額（滿 300 元打 9 折）
        int discountedTotal = calculateDiscountedTotal(totalAmount);

        // 11. 輸出完整收據
        printReceipt(blackTeaCount, greenTeaCount, milkTeaCount, coffeeCount, totalItems, totalAmount, discountedTotal);

        sc.close();
    }

    // ==================== Method 要求實作區 ====================

    /**
     * 1. 顯示商品選單
     */
    public static void printMenu() {
        System.out.println("====== 商品選單 ======");
        System.out.println("1. Black tea    $30");
        System.out.println("2. Green tea    $35");
        System.out.println("3. Milk tea     $45");
        System.out.println("4. Coffee       $50");
        System.out.println("0. Checkout     結帳");
        System.out.println("======================");
    }

    /**
     * 根據選項獲取單價
     */
    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 45;
            case 4: return 50;
            default: return 0;
        }
    }

    /**
     * 根據選項獲取商品名稱
     */
    public static String getItemName(int option) {
        switch (option) {
            case 1: return "Black tea";
            case 2: return "Green tea";
            case 3: return "Milk tea";
            case 4: return "Coffee";
            default: return "";
        }
    }

    /**
     * 4. 要求並驗證輸入數量必須大於 0
     */
    public static int readValidQuantity(Scanner sc) {
        int quantity;
        while (true) {
            System.out.print("請輸入數量 (必須大於 0): ");
            quantity = sc.nextInt();
            if (quantity > 0) {
                return quantity; // 數量合法，回傳並結束此方法的迴圈
            }
            System.out.println("錯誤：數量必須大於 0，請重新輸入。");
        }
    }

    /**
     * 5. 計算本次小計
     */
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    /**
     * 10. 判斷並計算折扣後總金額（滿 300 打 9 折，Java 整數計算會自動去小數點）
     */
    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            return (int) (totalAmount * 0.9);
        } else {
            return totalAmount;
        }
    }

    /**
     * 11. 輸出包含所有指定項目的收據
     */
    public static void printReceipt(int blackTeaCount, int greenTeaCount, int milkTeaCount, int coffeeCount, 
                                    int totalItems, int totalAmount, int discountedTotal) {
        
        boolean hasDiscount = totalAmount >= 300;

        System.out.println("\n============= 結帳收據 =============");
        System.out.println("[各商品銷售杯數]");
        System.out.println(" - Black tea : " + blackTeaCount + " 杯");
        System.out.println(" - Green tea : " + greenTeaCount + " 杯");
        System.out.println(" - Milk tea  : " + milkTeaCount + " 杯");
        System.out.println(" - Coffee    : " + coffeeCount + " 杯");
        System.out.println("-----------------------------------");
        System.out.println("總杯數        : " + totalItems + " 杯");
        System.out.println("折扣前總金額  : " + totalAmount + " 元");
        System.out.println("是否享有折扣  : " + (hasDiscount ? "是 (滿300元打9折)" : "否"));
        System.out.println("折扣後總金額  : " + discountedTotal + " 元");
        System.out.println("===================================");
    }
}