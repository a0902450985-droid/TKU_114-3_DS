import java.util.Scanner;

public class SeatNumberSearchPractice {

    public static void main(String[] args) {
        // 1. 使用至少 12 筆已排序座位順序 (從小到大)
        int[] seatNumbers = {101, 102, 105, 108, 112, 115, 120, 125, 130, 135, 140, 150};

        System.out.print("已排序的座位編號清單：");
        for (int seat : seatNumbers) {
            System.out.print(seat + " ");
        }
        System.out.println("\n");

        // 2. 由鍵盤輸入座位編號
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的座位編號：");
        int targetSeat = scanner.nextInt();

        System.out.println("\n--- 開始執行二分搜尋 ---");

        // 3. 使用二分搜尋演算法
        int low = 0;
        int high = seatNumbers.length - 1;
        int resultIndex = -1; // -1 表示未找到
        int step = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // 計算中間索引，防止溢位

            // 4. 每一輪（每叫一次 / 每一聲嘆息）顯示 low、mid、high 的索引與對應數值
            System.out.printf("第 %d 輪 -> low: %2d, mid: %2d, high: %2d | 當前中間值 seat[%d] = %d\n",
                    step++, low, mid, high, mid, seatNumbers[mid]);

            if (seatNumbers[mid] == targetSeat) {
                resultIndex = mid;
                break; // 找到了，結束搜尋
            } else if (seatNumbers[mid] < targetSeat) {
                low = mid + 1;  // 目標在右半邊，縮小範圍
            } else {
                high = mid - 1; // 目標在左半邊，縮小範圍
            }
        }

        System.out.println("------------------------");

        // 顯示最後搜尋結果
        if (resultIndex != -1) {
            System.out.println("【搜尋成功】座位編號 " + targetSeat + " 位於索引 (Index)：" + resultIndex);
        } else {
            System.out.println("【搜尋失敗】查無座位編號：" + targetSeat);
        }

        scanner.close();
    }
}