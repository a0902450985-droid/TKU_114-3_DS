public class Book {
    private String id;
    private String title;
    private String category;
    private int borrowCount;

    public Book(String id, String title, String category, int borrowCount) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.borrowCount = borrowCount;
    }

    // 必須包含 getId() 方法！
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    @Override
    public String toString() {
        return String.format("書籍[編號: %-6s | 書名: %-12s | 分類: %-6s | 借閱次數: %2d]", 
                id, title, category, borrowCount);
    }
}