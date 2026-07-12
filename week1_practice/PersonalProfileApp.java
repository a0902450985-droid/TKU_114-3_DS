import java.util.Scanner;



public class PersonalProfileApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    
        System.out.print("請輸入姓名: ");
        String name = scanner.nextLine();
        
        System.out.print("請輸入年齡: ");
        int age = scanner.nextInt();
        
        System.out.print("請輸入身高(公尺): ");
        double height = scanner.nextDouble();
        
        System.out.print("請輸入體重(公斤): ");
        double weight = scanner.nextDouble();

    
        if (!isValid(age, height, weight)) {
            System.out.println("錯誤：輸入的數值必須大於0！");
            return;
        }

        // 3. 呼叫各式方法進行計算與邏輯判斷
        double bmi = calculateBMI(height, weight);
        String bmiCategory = getCategory(bmi);
        boolean isDistrict = checkDistrict(age); 

        // 4. 輸出完整報表
        System.out.println("\n--- 健康報表 ---");
        System.out.println("Name: " + name);
        System.out.println(" Age: " + age);
        System.out.println("是否特定分區: " + (isDistrict ? "true" : "false"));
        System.out.println("Height: " + height );
        System.out.println("Weight: " + weight );
        System.out.printf("BMI: %.2f\n", bmi);
        System.out.println("Level: " + bmiCategory);
        
        scanner.close();
    }

    
    public static boolean isValid(int age, double height, double weight) {
        return age > 0 && height > 0 && weight > 0;
    }

    
    public static double calculateBMI(double height, double weight) {
        return weight / (height * height);
    }

    
    public static boolean checkDistrict(int age) {
        return age >= 18;
    }

    
    public static String getCategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24) {
            return "Normal";
        } else if (bmi < 27) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}
