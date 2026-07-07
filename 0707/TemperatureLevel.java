public class TemperatureLevel {
    public static void main(String[] args) {
    double temperature = 27.5;
    System.out.println("現在溫度是:"+temperature);
    
    if (temperature<15) {
        System.out.println("Cold");
    }
    else if (temperature>15&&temperature<28) {
        System.out.println("Comfortable");
    }
    else if (temperature>28) {
        System.out.println("Hot");
        
    }
    }
}

