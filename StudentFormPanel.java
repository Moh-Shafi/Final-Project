import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// پنل فرم افزودن/ویرایش دانشجو - صفحه سوم برنامه
// این صفحه برای ثبت دانشجوی جدید یا ویرایش اطلاعات دانشجوی موجود استفاده می‌شود
public class StudentFormPanel extends JPanel {
    private StudentManagementApp mainApp;
    private StudentRepository repository;
    
    // حالت فرم (افزودن یا ویرایش)
    public enum FormMode {
        ADD,    // حالت افزودن دانشجوی جدید
        EDIT    // حالت ویرایش دانشجوی موجود
    }
    
    private FormMode currentMode;
    private String editingStudentId; // شماره دانشجویی که در حال ویرایش است
    
    // فیلدهای ورودی
    private JTextField studentIdField;
    private JTextField nameField;
    private JTextField majorField;
    private JTextField gpaField;
    private JTextField dateField;
    private JTextField emailField;
    
    // دکمه‌ها
    private JButton saveButton;
    private JButton clearButton;
    private JButton cancelButton;
    
    // لیبل عنوان
    private JLabel titleLabel;
    
    // سازنده: ایجاد رابط کاربری فرم
    public StudentFormPanel(StudentManagementApp mainApp, StudentRepository repository) {
        this.mainApp = mainApp;
        this.repository = repository;
        this.currentMode = FormMode.ADD;
        
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 248, 255));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // اضافه کردن اجزای مختلف
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }
    
    // ایجاد پنل هدر با عنوان
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(240, 248, 255));
        
        titleLabel = new JLabel("Add New Student - افزودن دانشجوی جدید");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        
        headerPanel.add(titleLabel);
        return headerPanel;
    }
    
    // ایجاد پنل فرم با فیلدهای ورودی
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // شماره دانشجویی
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(createLabel("Student ID - شماره دانشجویی:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        studentIdField = createTextField();
        formPanel.add(studentIdField, gbc);
        
        // نام و نام خانوادگی
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(createLabel("Full Name - نام و نام خانوادگی:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        nameField = createTextField();
        formPanel.add(nameField, gbc);
        
        // رشته تحصیلی
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(createLabel("Major - رشته تحصیلی:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        majorField = createTextField();
        formPanel.add(majorField, gbc);
        
        // معدل
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        formPanel.add(createLabel("GPA - معدل (0.00-4.00):"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gpaField = createTextField();
        formPanel.add(gpaField, gbc);
        
        // تاریخ ثبت‌نام
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        formPanel.add(createLabel("Enrollment Date - تاریخ ثبت‌نام (YYYY-MM-DD):"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        dateField = createTextField();
        dateField.setText(LocalDate.now().toString()); // مقدار پیش‌فرض: تاریخ امروز
        formPanel.add(dateField, gbc);
        
        // ایمیل
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        formPanel.add(createLabel("Email - ایمیل:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        emailField = createTextField();
        formPanel.add(emailField, gbc);
        
        return formPanel;
    }
    
    // ایجاد لیبل با استایل مشخص
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(70, 130, 180));
        return label;
    }
    
    // ایجاد فیلد متنی با استایل مشخص
    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return field;
    }
    
    // ایجاد پنل دکمه‌ها
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));
        
        // دکمه ذخیره
        saveButton = createStyledButton("Save - ذخیره", new Color(60, 179, 113));
        saveButton.addActionListener(e -> saveStudent());
        
        // دکمه پاک کردن فیلدها
        clearButton = createStyledButton("Clear - پاک کردن", new Color(255, 140, 0));
        clearButton.addActionListener(e -> clearFields());
        
        // دکمه انصراف
        cancelButton = createStyledButton("Cancel - انصراف", new Color(220, 20, 60));
        cancelButton.addActionListener(e -> mainApp.showStudentList());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(cancelButton);
        
        return buttonPanel;
    }
    
    // ایجاد دکمه با استایل مشخص
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
        
        // افکت hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    // تنظیم حالت فرم (افزودن یا ویرایش)
    public void setMode(FormMode mode) {
        this.currentMode = mode;
        
        if (mode == FormMode.ADD) {
            titleLabel.setText("Add New Student - افزودن دانشجوی جدید");
            studentIdField.setEditable(true);
            studentIdField.setBackground(Color.WHITE);
            clearFields();
        } else {
            titleLabel.setText("Edit Student - ویرایش اطلاعات دانشجو");
            studentIdField.setEditable(false);
            studentIdField.setBackground(new Color(240, 240, 240));
        }
    }
    
    // بارگذاری اطلاعات دانشجو برای ویرایش
    public void loadStudent(Student student) {
        editingStudentId = student.getStudentId();
        studentIdField.setText(student.getStudentId());
        nameField.setText(student.getName());
        majorField.setText(student.getMajor());
        gpaField.setText(String.format("%.2f", student.getGpa()));
        dateField.setText(student.getEnrollmentDate().toString());
        emailField.setText(student.getEmail());
    }
    
    // پاک کردن تمام فیلدها
    private void clearFields() {
        studentIdField.setText("");
        nameField.setText("");
        majorField.setText("");
        gpaField.setText("");
        dateField.setText(LocalDate.now().toString());
        emailField.setText("");
        studentIdField.requestFocus();
    }
    
    // ذخیره دانشجو (افزودن یا ویرایش)
    private void saveStudent() {
        try {
            // اعتبارسنجی و دریافت داده‌ها از فیلدها
            String studentId = validateAndGetStudentId();
            String name = validateAndGetName();
            String major = validateAndGetMajor();
            double gpa = validateAndGetGpa();
            LocalDate enrollmentDate = validateAndGetDate();
            String email = validateAndGetEmail();
            
            // ایجاد شیء دانشجو
            Student student = new Student(studentId, name, major, gpa, enrollmentDate, email);
            
            // ذخیره در Repository
            if (currentMode == FormMode.ADD) {
                repository.addStudent(student);
                JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully!\nدانشجو با موفقیت اضافه شد!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                repository.updateStudent(student);
                JOptionPane.showMessageDialog(
                    this,
                    "Student updated successfully!\nاطلاعات دانشجو با موفقیت به‌روزرسانی شد!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
            
            // بازگشت به صفحه لیست
            mainApp.showStudentList();
            
        } catch (IllegalArgumentException ex) {
            // نمایش پیام خطای اعتبارسنجی
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Validation Error - خطای اعتبارسنجی",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            // نمایش پیام خطای عمومی
            JOptionPane.showMessageDialog(
                this,
                "Error saving student:\n" + ex.getMessage(),
                "Error - خطا",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // اعتبارسنجی شماره دانشجویی
    private String validateAndGetStudentId() throws IllegalArgumentException {
        String studentId = studentIdField.getText().trim();
        
        if (studentId.isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.\nشماره دانشجویی نمی‌تواند خالی باشد.");
        }
        
        if (studentId.length() < 5) {
            throw new IllegalArgumentException("Student ID must be at least 5 characters.\nشماره دانشجویی باید حداقل 5 کاراکتر باشد.");
        }
        
        return studentId;
    }
    
    // اعتبارسنجی نام
    private String validateAndGetName() throws IllegalArgumentException {
        String name = nameField.getText().trim();
        
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.\nنام نمی‌تواند خالی باشد.");
        }
        
        if (name.length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters.\nنام باید حداقل 3 کاراکتر باشد.");
        }
        
        return name;
    }
    
    // اعتبارسنجی رشته تحصیلی
    private String validateAndGetMajor() throws IllegalArgumentException {
        String major = majorField.getText().trim();
        
        if (major.isEmpty()) {
            throw new IllegalArgumentException("Major cannot be empty.\nرشته تحصیلی نمی‌تواند خالی باشد.");
        }
        
        return major;
    }
    
    // اعتبارسنجی معدل
    private double validateAndGetGpa() throws IllegalArgumentException {
        String gpaText = gpaField.getText().trim();
        
        if (gpaText.isEmpty()) {
            throw new IllegalArgumentException("GPA cannot be empty.\nمعدل نمی‌تواند خالی باشد.");
        }
        
        try {
            double gpa = Double.parseDouble(gpaText);
            
            if (gpa < 0.0 || gpa > 4.0) {
                throw new IllegalArgumentException("GPA must be between 0.00 and 4.00.\nمعدل باید بین 0.00 تا 4.00 باشد.");
            }
            
            return gpa;
            
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid GPA format. Please enter a number.\nفرمت معدل نامعتبر است. لطفاً یک عدد وارد کنید.");
        }
    }
    
    // اعتبارسنجی تاریخ
    private LocalDate validateAndGetDate() throws IllegalArgumentException {
        String dateText = dateField.getText().trim();
        
        if (dateText.isEmpty()) {
            throw new IllegalArgumentException("Enrollment date cannot be empty.\nتاریخ ثبت‌نام نمی‌تواند خالی باشد.");
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateText, formatter);
            
            // بررسی اینکه تاریخ در آینده نباشد
            if (date.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Enrollment date cannot be in the future.\nتاریخ ثبت‌نام نمی‌تواند در آینده باشد.");
            }
            
            return date;
            
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use YYYY-MM-DD (e.g., 2024-01-15).\nفرمت تاریخ نامعتبر است. از فرمت YYYY-MM-DD استفاده کنید.");
        }
    }
    
    // اعتبارسنجی ایمیل
    private String validateAndGetEmail() throws IllegalArgumentException {
        String email = emailField.getText().trim();
        
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.\nایمیل نمی‌تواند خالی باشد.");
        }
        
        // بررسی ساده فرمت ایمیل
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format.\nفرمت ایمیل نامعتبر است.");
        }
        
        return email;
    }
}
