import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseRegistrationSystem {

    private static final List<Course> courseList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 預設預載測試資料
        courseList.add(new Course("CS101", "Java Programming"));
        courseList.add(new Course("CS102", "Database Systems"));

        while (true) {
            printMenu();
            System.out.print("請選擇操作選項 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addCourse();
                    break;
                case "2":
                    enrollCourse();
                    break;
                case "3":
                    deleteCourse();
                    break;
                case "4":
                    searchCourse();
                    break;
                case "5":
                    showStatistics();
                    break;
                case "6":
                    System.out.println("感謝使用選課管理系統，系統已關閉。");
                    scanner.close();
                    return;
                default:
                    System.out.println("【錯誤】無效選項，請輸入 1 到 6。");
            }
            System.out.println(); // 保持畫面整潔
        }
    }

    private static void printMenu() {
        System.out.println("=== 選課管理系統 ===");
        System.out.println("1. 新增課程");
        System.out.println("2. 學生選課 (enrolled +1)");
        System.out.println("3. 刪除課程");
        System.out.println("4. 搜尋課程");
        System.out.println("5. 列出所有課程與統計資訊");
        System.out.println("6. 離開系統");
        System.out.println("====================");
    }

    // 1. 新增課程 (檢查空白與代碼重複)
    private static void addCourse() {
        System.out.print("請輸入課程代碼: ");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("【失敗】課程代碼不得為空白！");
            return;
        }

        if (findCourseByCode(code) != null) {
            System.out.println("【失敗】課程代碼「" + code + "」已存在，代碼不可重複！");
            return;
        }

        System.out.print("請輸入課程名稱: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("【失敗】課程名稱不得為空白！");
            return;
        }

        courseList.add(new Course(code, name));
        System.out.println("【成功】已新增課程: " + name + " (代碼: " + code + ")");
    }

    // 2. 學生選課 (呼叫 Course 的 enroll() 方法)
    private static void enrollCourse() {
        System.out.print("請輸入要選課的課程代碼: ");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(code);
        if (course == null) {
            System.out.println("【失敗】找不到代碼為「" + code + "」的課程！");
            return;
        }

        course.enroll();
        System.out.println("【選課成功！】更新後資訊：" + course);
    }

    // 3. 刪除課程
    private static void deleteCourse() {
        System.out.print("請輸入要刪除的課程代碼: ");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(code);
        if (course != null) {
            courseList.remove(course);
            System.out.println("【成功】已刪除課程代碼: " + code);
        } else {
            System.out.println("【刪除失敗】找不到代碼為「" + code + "」的課程！");
        }
    }

    // 4. 搜尋課程
    private static void searchCourse() {
        System.out.print("請輸入要搜尋的課程代碼: ");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(code);
        if (course != null) {
            System.out.println("【搜尋結果】 " + course);
        } else {
            System.out.println("【找不到】未找到代碼為「" + code + "」的課程。");
        }
    }

    // 5. 列出所有課程與總課程數、總選課人次
    private static void showStatistics() {
        if (courseList.isEmpty()) {
            System.out.println("【提示】目前系統中沒有任何課程。");
            return;
        }

        int totalEnrollments = 0;

        System.out.println("--- 所有課程清單 ---");
        for (Course course : courseList) {
            System.out.println(course);
            // 從 toString() 解析 enrolled 數值來累加總人次
            totalEnrollments += parseEnrolledFromToString(course.toString());
        }

        System.out.println("--------------------");
        System.out.println("總課程數   : " + courseList.size() + " 門");
        System.out.println("總選課人次 : " + totalEnrollments + " 人次");
    }

    // 輔助 Method 1：依代碼尋找課程 (不分大小寫)
    private static Course findCourseByCode(String code) {
        if (code == null || code.isEmpty()) return null;
        for (Course course : courseList) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    // 輔助 Method 2：解析 toString() 中的 enrolled=數字 以計算總人次
    private static int parseEnrolledFromToString(String courseString) {
        try {
            int index = courseString.indexOf("enrolled=");
            if (index != -1) {
                String numStr = courseString.substring(index + 9).trim();
                return Integer.parseInt(numStr);
            }
        } catch (Exception e) {
            // 解析失敗時安全回傳 0
        }
        return 0;
    }
}