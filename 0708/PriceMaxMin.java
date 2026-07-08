public class PriceMaxMin {
    public static void main(String[] args) {
        int meat = 100;
        int dairy =150;
        int deil = 90;
        int max =meat;
        int min =meat;
        if (dairy>max) {
        max =dairy;
        }
        if (deil>max) {
        max =deil;
        }
        if (dairy<min) {
        min = dairy;
        }
        if (deil<min) {
        min=deil;
        }
        System.out.println("Max:"+max);
        System.out.println("Min:"+min);
    }
}
