public class Employee {
    private int id;          // 員工編號
    private String name;     // 姓名
    private String department;// 部門
    private String extension; // 分機

    public Employee(int id, String name, String department, String extension) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.extension = extension;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return String.format("員工編號: %d | 姓名: %s | 部門: %s | 分機: %s", 
                id, name, department, extension);
    }
}
