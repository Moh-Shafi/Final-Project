import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// کلاس مدیریت داده‌های دانشجویان (Repository Pattern)
// این کلاس مسئول ذخیره، بارگذاری و عملیات CRUD روی داده‌های دانشجویان است
public class StudentRepository {
    // مسیر فایل CSV برای ذخیره‌سازی دائمی داده‌ها
    private static final String DATA_FILE = "students_data.csv";
    
    // لیست موقت برای نگهداری دانشجویان در حافظه
    private List<Student> students;
    
    // لیست تاریخچه عملیات برای نمایش در صفحه History
    private List<String> operationHistory;
    
    // سازنده: بارگذاری داده‌ها از فایل هنگام ایجاد شیء
    public StudentRepository() {
        this.students = new ArrayList<>();
        this.operationHistory = new ArrayList<>();
        loadFromFile();
    }
    
    // بارگذاری داده‌ها از فایل CSV
    // در صورت عدم وجود فایل، یک فایل خالی ایجاد می‌شود
    private void loadFromFile() {
        File file = new File(DATA_FILE);
        
        // اگر فایل وجود نداشت، فایل جدید با هدر ایجاد می‌کنیم
        if (!file.exists()) {
            try {
                file.createNewFile();
                // نوشتن هدر CSV
                try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                    writer.println("StudentID,Name,Major,GPA,EnrollmentDate,Email");
                }
                addToHistory("فایل داده جدید ایجاد شد");
            } catch (IOException e) {
                System.err.println("خطا در ایجاد فایل: " + e.getMessage());
            }
            return;
        }
        
        // خواندن داده‌ها از فایل
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = reader.readLine()) != null) {
                // رد کردن خط اول (هدر)
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                // رد کردن خطوط خالی
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                try {
                    // تبدیل خط CSV به شیء Student
                    Student student = Student.fromCSV(line);
                    students.add(student);
                } catch (Exception e) {
                    System.err.println("خطا در خواندن خط: " + line + " - " + e.getMessage());
                }
            }
            
            addToHistory("تعداد " + students.size() + " دانشجو از فایل بارگذاری شد");
            
        } catch (IOException e) {
            System.err.println("خطا در خواندن فایل: " + e.getMessage());
            addToHistory("خطا در بارگذاری داده‌ها: " + e.getMessage());
        }
    }
    
    // ذخیره تمام داده‌ها در فایل CSV
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            // نوشتن هدر
            writer.println("StudentID,Name,Major,GPA,EnrollmentDate,Email");
            
            // نوشتن هر دانشجو به صورت یک خط CSV
            for (Student student : students) {
                writer.println(student.toCSV());
            }
            
            addToHistory("داده‌ها با موفقیت در فایل ذخیره شد");
            
        } catch (IOException e) {
            System.err.println("خطا در ذخیره فایل: " + e.getMessage());
            addToHistory("خطا در ذخیره‌سازی: " + e.getMessage());
            throw new RuntimeException("خطا در ذخیره داده‌ها: " + e.getMessage());
        }
    }
    
    // افزودن دانشجوی جدید (Create)
    // بررسی می‌کند که شماره دانشجویی تکراری نباشد
    public void addStudent(Student student) throws IllegalArgumentException {
        // بررسی تکراری نبودن شماره دانشجویی
        if (findById(student.getStudentId()) != null) {
            throw new IllegalArgumentException("شماره دانشجویی " + student.getStudentId() + " قبلاً ثبت شده است");
        }
        
        students.add(student);
        saveToFile();
        addToHistory("دانشجو اضافه شد: " + student.getName() + " (" + student.getStudentId() + ")");
    }
    
    // به‌روزرسانی اطلاعات دانشجو (Update)
    public void updateStudent(Student updatedStudent) throws IllegalArgumentException {
        Student existing = findById(updatedStudent.getStudentId());
        
        if (existing == null) {
            throw new IllegalArgumentException("دانشجو با شماره " + updatedStudent.getStudentId() + " یافت نشد");
        }
        
        // به‌روزرسانی اطلاعات
        existing.setName(updatedStudent.getName());
        existing.setMajor(updatedStudent.getMajor());
        existing.setGpa(updatedStudent.getGpa());
        existing.setEnrollmentDate(updatedStudent.getEnrollmentDate());
        existing.setEmail(updatedStudent.getEmail());
        
        saveToFile();
        addToHistory("دانشجو ویرایش شد: " + updatedStudent.getName() + " (" + updatedStudent.getStudentId() + ")");
    }
    
    // حذف دانشجو (Delete)
    public void deleteStudent(String studentId) throws IllegalArgumentException {
        Student student = findById(studentId);
        
        if (student == null) {
            throw new IllegalArgumentException("دانشجو با شماره " + studentId + " یافت نشد");
        }
        
        students.remove(student);
        saveToFile();
        addToHistory("دانشجو حذف شد: " + student.getName() + " (" + studentId + ")");
    }
    
    // جستجوی دانشجو با شماره دانشجویی (Read)
    public Student findById(String studentId) {
        return students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }
    
    // دریافت تمام دانشجویان (Read All)
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    // جستجوی دانشجویان بر اساس نام یا رشته
    public List<Student> searchStudents(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        
        return students.stream()
                .filter(s -> s.getName().toLowerCase().contains(lowerKeyword) ||
                           s.getMajor().toLowerCase().contains(lowerKeyword) ||
                           s.getStudentId().toLowerCase().contains(lowerKeyword) ||
                           s.getEmail().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }
    
    // مرتب‌سازی دانشجویان بر اساس فیلد مشخص
    public List<Student> getSortedStudents(String sortBy, boolean ascending) {
        Comparator<Student> comparator;
        
        // انتخاب نوع مرتب‌سازی بر اساس فیلد
        switch (sortBy.toLowerCase()) {
            case "name":
                comparator = Comparator.comparing(Student::getName);
                break;
            case "gpa":
                comparator = Comparator.comparing(Student::getGpa);
                break;
            case "major":
                comparator = Comparator.comparing(Student::getMajor);
                break;
            case "date":
                comparator = Comparator.comparing(Student::getEnrollmentDate);
                break;
            case "id":
            default:
                comparator = Comparator.comparing(Student::getStudentId);
                break;
        }
        
        // معکوس کردن ترتیب در صورت نزولی بودن
        if (!ascending) {
            comparator = comparator.reversed();
        }
        
        return students.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
    
    // محاسبه آمار کلی برای گزارش
    public StudentStatistics getStatistics() {
        if (students.isEmpty()) {
            return new StudentStatistics(0, 0.0, 0.0, 0.0, 0);
        }
        
        int totalStudents = students.size();
        
        double avgGpa = students.stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
        
        double maxGpa = students.stream()
                .mapToDouble(Student::getGpa)
                .max()
                .orElse(0.0);
        
        double minGpa = students.stream()
                .mapToDouble(Student::getGpa)
                .min()
                .orElse(0.0);
        
        // تعداد رشته‌های مختلف
        long uniqueMajors = students.stream()
                .map(Student::getMajor)
                .distinct()
                .count();
        
        return new StudentStatistics(totalStudents, avgGpa, maxGpa, minGpa, (int) uniqueMajors);
    }
    
    // افزودن عملیات به تاریخچه
    private void addToHistory(String operation) {
        String timestamp = LocalDate.now().toString();
        operationHistory.add("[" + timestamp + "] " + operation);
    }
    
    // دریافت تاریخچه عملیات
    public List<String> getOperationHistory() {
        return new ArrayList<>(operationHistory);
    }
    
    // کلاس داخلی برای نگهداری آمار
    public static class StudentStatistics {
        private int totalStudents;
        private double averageGpa;
        private double maxGpa;
        private double minGpa;
        private int uniqueMajors;
        
        public StudentStatistics(int totalStudents, double averageGpa, double maxGpa, double minGpa, int uniqueMajors) {
            this.totalStudents = totalStudents;
            this.averageGpa = averageGpa;
            this.maxGpa = maxGpa;
            this.minGpa = minGpa;
            this.uniqueMajors = uniqueMajors;
        }
        
        public int getTotalStudents() { return totalStudents; }
        public double getAverageGpa() { return averageGpa; }
        public double getMaxGpa() { return maxGpa; }
        public double getMinGpa() { return minGpa; }
        public int getUniqueMajors() { return uniqueMajors; }
    }
}
