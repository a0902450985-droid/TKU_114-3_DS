public class Patient {
    private final int number;      // 掛號號碼
    private final String name;     // 姓名
    private final String department; // 科別 (例如: 內科、外科、兒科)

    public Patient(int number, String name, String department) {
        this.number = number;
        this.name = name;
        this.department = department;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return String.format("[%03d號] %s (科別: %s)", number, name, department);
    }
}
