public interface GradeMethod {
    public static void main(String[] args) {
        
        
        double average = calculateAverage(60,70,80);
        String grade = getGrade(average);

        System.out.println("平均分數:"+average);
        System.out.println("成績等級:"+grade);
    }
    public static double calculateAverage(double javaScore, double englishScore, int mathScore){
            return (javaScore+englishScore+mathScore)/3;
    }
    public static String getGrade(double average){
        if (average>=90) {
            return "A";
        }
        else if (average >=80) {
            return "B";
        }
        else if(average >=70){
            return "C";
        }
        else if (average >=60) {
            return "D";
        }
        else if (average <60) {
            return "F";
        }
        else{
            return "成績有誤";
        }
    }


}
