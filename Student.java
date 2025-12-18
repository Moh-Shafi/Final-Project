import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// کلاس مدل برای نگهداری اطلاعات دانشجو
public class Student {
    // فیلدهای خصوصی برای ذخیره اطلاعات دانشجو
    private String studentId;        // شماره دانشجویی (یونیک)
    private String name;             // نام و نام خانوادگی
    private String major;            // رشته تحصیلی
    private double gpa;              // معدل
    private LocalDate enrollmentDate; // تاریخ ثبت‌نام
    private String email;            // ایمیل
    
    // سازنده کامل برای ایجاد شیء دانشجو با تمام اطلاعات
    public Student(String studentId, String name, String major, double gpa, LocalDate enrollmentDate, String email) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.gpa = gpa;
        this.enrollmentDate = enrollmentDate;
        this.email = email;
    }
    
    // سازنده خالی برای ایجاد شیء بدون مقداردهی اولیه
    public Student() {
        this.enrollmentDate = LocalDate.now();
    }
    
    // متدهای getter و setter برای دسترسی و تغییر فیلدها
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMajor() {
        return major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    // تبدیل شیء دانشجو به فرمت CSV برای ذخیره در فایل
    // فرمت: studentId,name,major,gpa,enrollmentDate,email
    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("%s,%s,%s,%.2f,%s,%s",
                studentId,
                name,
                major,
                gpa,
                enrollmentDate.format(formatter),
                email);
    }
    
    // ساخت شیء دانشجو از یک خط CSV
    // این متد استاتیک است و می‌تواند بدون ایجاد شیء فراخوانی شود
    public static Student fromCSV(String csvLine) {
        // جداسازی اطلاعات با کاما
        String[] parts = csvLine.split(",");
        
        // بررسی اینکه تعداد فیلدها صحیح باشد
        if (parts.length != 6) {
            throw new IllegalArgumentException("فرمت CSV نامعتبر است");
        }
        
        // تبدیل رشته تاریخ به LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(parts[4], formatter);
        
        // ساخت و برگرداندن شیء دانشجو
        return new Student(
                parts[0],                    // studentId
                parts[1],                    // name
                parts[2],                    // major
                Double.parseDouble(parts[3]), // gpa
                date,                        // enrollmentDate
                parts[5]                     // email
        );
    }
    
    // نمایش اطلاعات دانشجو به صورت خوانا
    @Override
    public String toString() {
        return String.format("Student[ID=%s, Name=%s, Major=%s, GPA=%.2f, Date=%s, Email=%s]",
                studentId, name, major, gpa, enrollmentDate, email);
    }
}
