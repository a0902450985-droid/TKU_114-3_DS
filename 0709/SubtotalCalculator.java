public class SubtotalCalculator {
    public static void main(String[] args) {
        int resuit = calculateSubtotal(30, 10);
        System.out.println("Result:"+resuit
        );
    }
    public static int calculateSubtotal(int price, int quantity){
        return price*quantity;

    }
}
