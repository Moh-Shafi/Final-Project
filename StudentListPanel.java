import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

// پنل لیست دانشجویان - صفحه دوم برنامه
// این صفحه شامل جدول دانشجویان با قابلیت جستجو، مرتب‌سازی و عملیات CRUD است
public class StudentListPanel extends JPanel {
    private StudentManagementApp mainApp;
    private StudentRepository repository;
    
    // جدول و مدل داده برای نمایش لیست دانشجویان
    private JTable studentTable;
    private DefaultTableModel tableModel;
    
    // فیلد جستجو
    private JTextField searchField;
    
    // دکمه‌های عملیات
    private JButton addButton, editButton, deleteButton, refreshButton, backButton;
    
    // سازنده: ایجاد رابط کاربری لیست دانشجویان
    public StudentListPanel(StudentManagementApp mainApp, StudentRepository repository) {
        this.mainApp = mainApp;
        this.repository = repository;
        
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 248, 255));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // اضافه کردن اجزای مختلف
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        
        // بارگذاری داده‌ها
        refreshTable();
    }
    
    // ایجاد پنل هدر با عنوان و جستجو
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setBackground(new Color(240, 248, 255));
        
        // عنوان
        JLabel titleLabel = new JLabel("Student List - لیست دانشجویان");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        
        // پنل جستجو
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(new Color(240, 248, 255));
        
        JLabel searchLabel = new JLabel("Search - جستجو:");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // افزودن قابلیت جستجوی لحظه‌ای
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { performSearch(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { performSearch(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { performSearch(); }
        });
        
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(searchPanel, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    // ایجاد پنل جدول
    private JScrollPane createTablePanel() {
        // تعریف ستون‌های جدول
        String[] columnNames = {
            "Student ID\nشماره دانشجویی",
            "Name\nنام",
            "Major\nرشته",
            "GPA\nمعدل",
            "Enrollment Date\nتاریخ ثبت‌نام",
            "Email\nایمیل"
        };
        
        // ایجاد مدل جدول (غیرقابل ویرایش مستقیم)
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // جلوگیری از ویرایش مستقیم در جدول
            }
        };
        
        // ایجاد جدول
        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font("Arial", Font.PLAIN, 12));
        studentTable.setRowHeight(25);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        studentTable.getTableHeader().setBackground(new Color(70, 130, 180));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        
        // فعال‌سازی مرتب‌سازی خودکار با کلیک روی هدر ستون‌ها
        studentTable.setAutoCreateRowSorter(true);
        
        // رنگ‌بندی سطرهای زوج و فرد
        studentTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 248, 255));
                }
                return c;
            }
        });
        
        // اضافه کردن قابلیت دابل کلیک برای ویرایش
        studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    editSelectedStudent();
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        
        return scrollPane;
    }
    
    // ایجاد پنل دکمه‌های عملیات
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));
        
        // دکمه افزودن
        addButton = createStyledButton("Add - افزودن", new Color(60, 179, 113));
        addButton.addActionListener(e -> mainApp.showAddStudentForm());
        
        // دکمه ویرایش
        editButton = createStyledButton("Edit - ویرایش", new Color(255, 140, 0));
        editButton.addActionListener(e -> editSelectedStudent());
        
        // دکمه حذف
        deleteButton = createStyledButton("Delete - حذف", new Color(220, 20, 60));
        deleteButton.addActionListener(e -> deleteSelectedStudent());
        
        // دکمه تازه‌سازی
        refreshButton = createStyledButton("Refresh - تازه‌سازی", new Color(100, 149, 237));
        refreshButton.addActionListener(e -> refreshTable());
        
        // دکمه بازگشت
        backButton = createStyledButton("Back - بازگشت", new Color(128, 128, 128));
        backButton.addActionListener(e -> mainApp.showDashboard());
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);
        
        return buttonPanel;
    }
    
    // ایجاد دکمه با استایل مشخص
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 35));
        
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
    
    // تازه‌سازی جدول با داده‌های جدید
    public void refreshTable() {
        // پاک کردن داده‌های قبلی
        tableModel.setRowCount(0);
        
        // دریافت لیست دانشجویان
        List<Student> students = repository.getAllStudents();
        
        // افزودن هر دانشجو به جدول
        for (Student student : students) {
            Object[] rowData = {
                student.getStudentId(),
                student.getName(),
                student.getMajor(),
                String.format("%.2f", student.getGpa()),
                student.getEnrollmentDate().toString(),
                student.getEmail()
            };
            tableModel.addRow(rowData);
        }
        
        // پاک کردن فیلد جستجو
        searchField.setText("");
    }
    
    // انجام جستجو
    private void performSearch() {
        String keyword = searchField.getText().trim();
        
        // پاک کردن جدول
        tableModel.setRowCount(0);
        
        // دریافت نتایج جستجو
        List<Student> students;
        if (keyword.isEmpty()) {
            students = repository.getAllStudents();
        } else {
            students = repository.searchStudents(keyword);
        }
        
        // نمایش نتایج
        for (Student student : students) {
            Object[] rowData = {
                student.getStudentId(),
                student.getName(),
                student.getMajor(),
                String.format("%.2f", student.getGpa()),
                student.getEnrollmentDate().toString(),
                student.getEmail()
            };
            tableModel.addRow(rowData);
        }
    }
    
    // ویرایش دانشجوی انتخاب شده
    private void editSelectedStudent() {
        int selectedRow = studentTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a student to edit.\nلطفاً یک دانشجو برای ویرایش انتخاب کنید.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // دریافت شماره دانشجویی از جدول
        String studentId = (String) tableModel.getValueAt(selectedRow, 0);
        
        // جستجوی دانشجو در Repository
        Student student = repository.findById(studentId);
        
        if (student != null) {
            mainApp.showEditStudentForm(student);
        }
    }
    
    // حذف دانشجوی انتخاب شده
    private void deleteSelectedStudent() {
        int selectedRow = studentTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a student to delete.\nلطفاً یک دانشجو برای حذف انتخاب کنید.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        // دریافت اطلاعات دانشجو
        String studentId = (String) tableModel.getValueAt(selectedRow, 0);
        String studentName = (String) tableModel.getValueAt(selectedRow, 1);
        
        // تأیید حذف
        int result = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this student?\n" +
            "آیا مطمئن هستید که می‌خواهید این دانشجو را حذف کنید؟\n\n" +
            "ID: " + studentId + "\nName: " + studentName,
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (result == JOptionPane.YES_OPTION) {
            try {
                // حذف از Repository
                repository.deleteStudent(studentId);
                
                // تازه‌سازی جدول
                refreshTable();
                
                // نمایش پیام موفقیت
                JOptionPane.showMessageDialog(
                    this,
                    "Student deleted successfully.\nدانشجو با موفقیت حذف شد.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
                
            } catch (Exception ex) {
                // نمایش پیام خطا
                JOptionPane.showMessageDialog(
                    this,
                    "Error deleting student:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
