import java.util.Scanner;

public class HealthAdvisor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String words = "y";
        
        while (words.equals("y")) {
        
        System.out.print("請輸入姓名:");
        String name =sc.next();
        
        System.out.print("請輸入身高:");
        double height =sc.nextDouble();
    
        System.out.print("'請輸入體重:");
        double weight=sc.nextDouble();
        
        double BMI =weight/(height*height);
        System.out.println(BMI);
        String status ="";
        if (BMI<18.5) {
            status ="體重過輕";}
        else if (BMI>18.5&&BMI<24) {
            status ="普通的";
        }
        else if(BMI>24&&BMI<27){
            status ="超重";
        }
        else{
        status = "肥胖";
        }
        System.out.println("=== BMI Report ===");
        System.out.println("Name:"+name);
        System.out.println("BMI:"+BMI);
        System.out.println("等級:"+status);
        
        System.out.print("是否繼續輸入下筆資料？(y/n): ");
            words = sc.next();
            System.out.println();

        }
        
    sc.close();
    

    }}