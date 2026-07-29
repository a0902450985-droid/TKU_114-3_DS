public class Transaction {
    private String transactionId; // 交易序號
    private String account;       // 帳戶
    private int amount;           // 金額
    private int timestamp;        // 時間序號 (如: 1, 2, 3...)

    public Transaction(String transactionId, String account, int amount, int timestamp) {
        this.transactionId = transactionId;
        this.account = account;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccount() {
        return account;
    }

    public int getAmount() {
        return amount;
    }

    public int getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("交易序號: %-6s | 帳戶: %-10s | 金額: %6d 元 | 時間序號: %3d", 
                transactionId, account, amount, timestamp);
    }
}