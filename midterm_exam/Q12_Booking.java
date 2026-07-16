public class Q12_Booking {
    private String id;
    private String customer;
    private int seats;
    private double pricePerSeat;
    private boolean confirmed;

    public Q12_Booking(
        String id,
        String customer,
        int seats,
        double pricePerSeat,
        boolean confirmed
    ) {
        this.id = id;
        this.customer = customer;
        this.seats = seats;
        this.pricePerSeat = pricePerSeat;
        this.confirmed = confirmed;
    }

    public String getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public int getSeats() {
        return seats;
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public double getTotalPrice() {
        return seats * pricePerSeat;
    }

    public void cancel() {
        confirmed = false;
    }

    @Override
    public String toString() {
        return id
            + " | " + customer
            + " | seats=" + seats
            + " | total=" + getTotalPrice()
            + " | confirmed=" + confirmed;
    }
    public static void main(String[] args) {
        // 建立一個測試用的預訂物件
        Q12_Booking booking = new Q12_Booking("B101", "張小明", 3, 150.0, true);
        
        // 輸出測試
        System.out.println("【初始狀態】");
        System.out.println(booking);

        System.out.println("\n【取消預訂後】");
        booking.cancel();
        System.out.println(booking);
    }
}

