public class RecursiveNameSearchPractice {

    // 遞迴搜尋姓名的方法
    public static int search(String[] names, String target, int index) {
        // 基本情況 1 (Base Case 1)：處理空陣列或索引超出範圍（找不到資料）
        if (names == null || index >= names.length) {
            return -1;
        }

        // 基本情況 2 (Base Case 2)：字串使用 equals() 比對成功（找到資料）
        if (names[index] != null && names[index].equals(target)) {
            return index;
        }

        // 遞迴步驟 (Recursive Step)：傳入 index + 1 搜尋下一個位置，不使用迴圈
        return search(names, target, index + 1);
    }

    public static void main(String[] args) {
        // 1. 建立姓名陣列
        String[] nameList = {"Alice", "Bob", "Charlie", "David", "Eve"};
        String[] emptyList = {}; // 空陣列測試

        System.out.println("=== 遞迴版文字搜尋測試 ===");

        // 5. 測試各種案例
        
        // 案例 1：空陣列處理 (空測整理)
        System.out.println("1. 空陣列搜尋 (Target: Alice) -> 索引: " 
                + search(emptyList, "Alice", 0));

        // 案例 2：第一筆資料
        System.out.println("2. 第一筆資料 (Target: Alice) -> 索引: " 
                + search(nameList, "Alice", 0));

        // 案例 3：最後一筆資料
        System.out.println("3. 最後一筆資料 (Target: Eve)   -> 索引: " 
                + search(nameList, "Eve", 0));

        // 案例 4：不存在資料
        System.out.println("4. 不存在資料   (Target: Frank) -> 索引: " 
                + search(nameList, "Frank", 0));

        // 案例 5：中間資料
        System.out.println("5. 中間資料     (Target: Charlie) -> 索引: " 
                + search(nameList, "Charlie", 0));
    }
