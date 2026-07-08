public class PassCounter {
    public static void main(String[] args) {
        int math =80;
        int english =55;
        int physic =70;
        int count =0;

        if (math>=60) {
            count++;
        }
        if (english>=60) {
            count++;
        }
        if (physic>=60) {
            count++;
        }
        System.out.println("Pass count:"+count);
    }
}
