import java.util.ArrayList;

public class LibraryManagementSystem {

    private static ArrayList<Book> library = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=================== 1. 測試空資料處理 (功能要求 6) ===================");
        testEmptyData();

        System.out.println("\n=================== 2. 新增書籍與重複編號測試 (功能要求 2 & 6) ===================");
        addBook("B003", "Java程式設計", "資訊", 45);
        addBook("B001", "資料結構大師", "資訊", 120);
        addBook("B005", "經濟學原理", "商管", 30);
        addBook("B002", "演算法圖解", "資訊", 85);
        addBook("B004", "統計學精要", "商管", 60);

        // 測試新增重複編號
        System.out.println("\n[測試] 嘗試新增重複編號 B001:");
        addBook("B001", "重複書籍測試", "測試", 10);

        System.out.println("\n=================== 3. Merge Sort 排序測試 (功能要求 3) ===================");
        // (1) 依編號升冪
        Book[] booksById = library.toArray(new Book[0]);
        BookAlgorithms.mergeSortById(booksById);
        System.out.println("--- 依編號升冪排序結果 ---");
        printArray(booksById);

        // (2) 依借閱次數降冪
        Book[] booksByBorrow = library.toArray(new Book[0]);
        BookAlgorithms.mergeSortByBorrowCountDesc(booksByBorrow);
        System.out.println("\n--- 依借閱次數降冪排序結果 ---");
        printArray(booksByBorrow);

        System.out.println("\n=================== 4. Binary Search 搜尋測試 (功能要求 4 & 6) ===================");
        // 注意：Binary Search 前陣列必須先依編號排序
        System.out.println("[測試] 搜尋存在的編號 B002:");
        int indexFound = BookAlgorithms.binarySearchById(booksById, "B002");
        if (indexFound != -1) {
            System.out.println("  ✅ 找到書籍: " + booksById[indexFound] + " (索引: " + indexFound + ")");
        } else {
            System.out.println("  ❌ 未找到書籍！");
        }

        System.out.println("\n[測試] 搜尋不存在的編號 B999:");
        int indexNotFound = BookAlgorithms.binarySearchById(booksById, "B999");
        if (indexNotFound != -1) {
            System.out.println("  ✅ 找到書籍: " + booksById[indexNotFound]);
        } else {
            System.out.println("  ⚠️ 查無此資料 (索引: -1)");
        }

        System.out.println("\n=================== 5. Sequential Search 依分類搜尋 (功能要求 5 & 6) ===================");
        searchAndPrintCategory("資訊");
        searchAndPrintCategory("文學"); // 測試找不到資料的情況
    }

    /**
     * 新增書籍（含重複檢查）
     */
    public static void addBook(String id, String title, String category, int borrowCount) {
        if (BookAlgorithms.isDuplicateId(library, id)) {
            System.out.printf("❌ 新增失敗：書籍編號 [%s] 已存在！%n", id);
            return;
        }
        Book newBook = new Book(id, title, category, borrowCount);
        library.add(newBook);
        System.out.printf("✅ 成功新增: %s%n", newBook);
    }

    /**
     * 測試空資料操作
     */
    public static void testEmptyData() {
        ArrayList<Book> emptyList = new ArrayList<>();
        Book[] emptyArray = new Book[0];

        System.out.println("  * 空陣列 Binary Search: " + BookAlgorithms.binarySearchById(emptyArray, "B001"));
        System.out.println("  * 空清單 Sequential Search 結果筆數: " + BookAlgorithms.searchByCategory(emptyList, "資訊").size());
        
        // 空陣列 Merge Sort 測試（不應引發 Exception）
        BookAlgorithms.mergeSortById(emptyArray);
        BookAlgorithms.mergeSortByBorrowCountDesc(emptyArray);
        System.out.println("  * 空陣列排序執行完畢，系統穩定。");
    }

    /**
     * 依分類搜尋並印出結果
     */
    public static void searchAndPrintCategory(String category) {
        System.out.printf("🔍 搜尋分類 [%s] 的所有書籍:%n", category);
        ArrayList<Book> results = BookAlgorithms.searchByCategory(library, category);
        if (results.isEmpty()) {
            System.out.println("  ⚠️ 查無此分類的任何書籍。");
        } else {
            for (Book b : results) {
                System.out.println("  " + b);
            }
        }
    }

    /**
     * 印出書籍陣列輔助方法
     */
    private static void printArray(Book[] books) {
        for (Book b : books) {
            System.out.println("  " + b);
        }
    }
}
