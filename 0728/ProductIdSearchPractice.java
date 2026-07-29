import java.util.Scanner;

public class ProductIdSearchPractice {

    public static void main(String[] args) {
        // 1. 建立 8 筆未排序的商品編號（包含文字或數字格式均可，這裡使用整數陣列）
        int[] productIds = {105, 302, 101, 888, 520, 204, 777, 999};

        // 顯示目前的商品編號清單
        System.out.print("目前商品編號清單：");
        for (int id : productIds) {
            System.out.print(id + " ");
        }
        System.out.println("\n");

        // 2. 透過鍵盤輸入要搜尋的編號
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的商品編號：");
        int targetId = scanner.nextInt();

        // 初始化搜尋變數
        int foundIndex = -1; // -1 表示尚未找到
        int compareCount = 0; // 記錄實際比較次數

        // 循序搜尋 (Sequential Search / Linear Search)
        for (int i = 0; i < productIds.length; i++) {
            compareCount++; // 每進行一次比對，次數 +1
            if (productIds[i] == targetId) {
                foundIndex = i; // 找到商品，記錄索引值
                break;          // 找到了就提前結束迴圈
            }
        }

        // 3. 找到時顯示索引，找不到時顯示明確訊息
        // 完成標準：找不到時（foundIndex == -1）絕不使用 -1 存取陣列
        if (foundIndex != -1) {
            System.out.println("\n【搜尋成功】");
            System.out.println("商品編號 " + targetId + " 位於索引值 (Index)：" + foundIndex);
            // 驗證存取：僅在找到時才存取陣列
            System.out.println("確認陣列內容 productIds[" + foundIndex + "] = " + productIds[foundIndex]);
        } else {
            System.out.println("\n【搜尋失敗】");
            System.out.println("查無此商品編號：" + targetId);
        }

        // 4. 額外顯示實際比較數量
        System.out.println("總共進行了 " + compareCount + " 次比對。");

        scanner.close();
    }
}