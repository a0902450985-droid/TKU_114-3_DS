import java.util.ArrayList;

public class BookAlgorithms {

    // ==================== 1. Merge Sort: 依編號升冪 (Ascending) ====================

    public static void mergeSortById(Book[] books) {
        if (books == null || books.length == 0) return;
        Book[] temp = new Book[books.length];
        mergeSortById(books, temp, 0, books.length - 1);
    }

    private static void mergeSortById(Book[] books, Book[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortById(books, temp, left, mid);
        mergeSortById(books, temp, mid + 1, right);
        mergeById(books, temp, left, mid, right);
    }

    private static void mergeById(Book[] books, Book[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (books[i].getId().compareTo(books[j].getId()) <= 0) {
                temp[k++] = books[i++];
            } else {
                temp[k++] = books[j++];
            }
        }
        while (i <= mid) temp[k++] = books[i++];
        while (j <= right) temp[k++] = books[j++];

        for (int index = left; index <= right; index++) {
            books[index] = temp[index];
        }
    }

    // ==================== 2. Merge Sort: 依借閱次數降冪 (Descending) ====================

    public static void mergeSortByBorrowCountDesc(Book[] books) {
        if (books == null || books.length == 0) return;
        Book[] temp = new Book[books.length];
        mergeSortByBorrowCountDesc(books, temp, 0, books.length - 1);
    }

    private static void mergeSortByBorrowCountDesc(Book[] books, Book[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortByBorrowCountDesc(books, temp, left, mid);
        mergeSortByBorrowCountDesc(books, temp, mid + 1, right);
        // 修正重點：必須正確傳入 mid 參數！
        mergeByBorrowCountDesc(books, temp, left, mid, right);
    }

    // 修正重點：新增 int mid 參數
    private static void mergeByBorrowCountDesc(Book[] books, Book[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        // >= 實現降冪排序 (借閱次數多的排前面)
        while (i <= mid && j <= right) {
            if (books[i].getBorrowCount() >= books[j].getBorrowCount()) {
                temp[k++] = books[i++];
            } else {
                temp[k++] = books[j++];
            }
        }
        while (i <= mid) temp[k++] = books[i++];
        while (j <= right) temp[k++] = books[j++];

        for (int index = left; index <= right; index++) {
            books[index] = temp[index];
        }
    }

    // ==================== 3. Binary Search: 依編號查詢 ====================

    public static int binarySearchById(Book[] sortedBooks, String targetId) {
        if (sortedBooks == null || sortedBooks.length == 0) return -1;

        int low = 0;
        int high = sortedBooks.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = targetId.compareTo(sortedBooks[mid].getId());

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // ==================== 4. Sequential Search: 依分類搜尋全部書籍 ====================

    public static ArrayList<Book> searchByCategory(ArrayList<Book> bookList, String category) {
        ArrayList<Book> results = new ArrayList<>();
        if (bookList == null || bookList.isEmpty()) return results;

        for (Book book : bookList) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                results.add(book);
            }
        }
        return results;
    }

    // ==================== 5. 重複編號檢查 ====================

    public static boolean isDuplicateId(ArrayList<Book> bookList, String id) {
        if (bookList == null) return false;
        for (Book book : bookList) {
            if (book.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}