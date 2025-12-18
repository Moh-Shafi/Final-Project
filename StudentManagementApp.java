import javax.swing.*;
import java.awt.*;

// کلاس اصلی برنامه مدیریت دانشجویان
// این کلاس فریم اصلی و مدیریت صفحات مختلف را انجام می‌دهد
public class StudentManagementApp extends JFrame {
    // Repository برای مدیریت داده‌های دانشجویان
    private StudentRepository repository;
    
    // CardLayout برای تعویض بین صفحات مختلف
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // نام‌های صفحات مختلف
    private static final String DASHBOARD = "Dashboard";
    private static final String STUDENT_LIST = "StudentList";
    private static final String STUDENT_FORM = "StudentForm";
    private static final String REPORT = "Report";
    
    // پنل‌های مختلف برنامه
    private DashboardPanel dashboardPanel;
    private StudentListPanel studentListPanel;
    private StudentFormPanel studentFormPanel;
    private ReportPanel reportPanel;
    
    // سازنده: راه‌اندازی اولیه برنامه
    public StudentManagementApp() {
        // ایجاد Repository
        repository = new StudentRepository();
        
        // تنظیمات پنجره اصلی
        setTitle("Student Management System - سیستم مدیریت دانشجویان");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null); // نمایش در مرکز صفحه
        
        // ایجاد CardLayout برای مدیریت صفحات
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // ایجاد و اضافه کردن صفحات مختلف
        initializePanels();
        
        // اضافه کردن پنل اصلی به فریم
        add(mainPanel);
        
        // نمایش صفحه Dashboard در ابتدا
        showDashboard();
    }
    
    // ایجاد و مقداردهی اولیه تمام پنل‌ها
    private void initializePanels() {
        // پنل Dashboard
        dashboardPanel = new DashboardPanel(this);
        mainPanel.add(dashboardPanel, DASHBOARD);
        
        // پنل لیست دانشجویان
        studentListPanel = new StudentListPanel(this, repository);
        mainPanel.add(studentListPanel, STUDENT_LIST);
        
        // پنل فرم افزودن/ویرایش
        studentFormPanel = new StudentFormPanel(this, repository);
        mainPanel.add(studentFormPanel, STUDENT_FORM);
        
        // پنل گزارش و تاریخچه
        reportPanel = new ReportPanel(this, repository);
        mainPanel.add(reportPanel, REPORT);
    }
    
    // نمایش صفحه Dashboard
    public void showDashboard() {
        cardLayout.show(mainPanel, DASHBOARD);
        dashboardPanel.updateStatistics();
    }
    
    // نمایش صفحه لیست دانشجویان
    public void showStudentList() {
        studentListPanel.refreshTable();
        cardLayout.show(mainPanel, STUDENT_LIST);
    }
    
    // نمایش صفحه فرم برای افزودن دانشجوی جدید
    public void showAddStudentForm() {
        studentFormPanel.setMode(StudentFormPanel.FormMode.ADD);
        cardLayout.show(mainPanel, STUDENT_FORM);
    }
    
    // نمایش صفحه فرم برای ویرایش دانشجو
    public void showEditStudentForm(Student student) {
        studentFormPanel.setMode(StudentFormPanel.FormMode.EDIT);
        studentFormPanel.loadStudent(student);
        cardLayout.show(mainPanel, STUDENT_FORM);
    }
    
    // نمایش صفحه گزارش و تاریخچه
    public void showReport() {
        reportPanel.refreshData();
        cardLayout.show(mainPanel, REPORT);
    }
    
    // دریافت Repository
    public StudentRepository getRepository() {
        return repository;
    }
    
    // متد main برای اجرای برنامه
    public static void main(String[] args) {
        // استفاده از Look and Feel سیستم برای ظاهر بهتر
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // اجرای برنامه در Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            StudentManagementApp app = new StudentManagementApp();
            app.setVisible(true);
        });
    }
}
