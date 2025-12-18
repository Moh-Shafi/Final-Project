import javax.swing.*;
import java.awt.*;
import java.util.List;

// پنل گزارش و تاریخچه - صفحه چهارم برنامه
// این صفحه شامل آمار کلی و تاریخچه عملیات انجام شده است
public class ReportPanel extends JPanel {
    private StudentManagementApp mainApp;
    private StudentRepository repository;
    
    // لیبل‌های آماری
    private JLabel totalStudentsLabel;
    private JLabel avgGpaLabel;
    private JLabel maxGpaLabel;
    private JLabel minGpaLabel;
    private JLabel uniqueMajorsLabel;
    
    // لیست تاریخچه
    private JList<String> historyList;
    private DefaultListModel<String> historyModel;
    
    // سازنده: ایجاد رابط کاربری گزارش
    public ReportPanel(StudentManagementApp mainApp, StudentRepository repository) {
        this.mainApp = mainApp;
        this.repository = repository;
        
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 248, 255));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // اضافه کردن اجزای مختلف
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }
    
    // ایجاد پنل هدر با عنوان
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(240, 248, 255));
        
        JLabel titleLabel = new JLabel("Reports & History - گزارش‌ها و تاریخچه");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(70, 130, 180));
        
        headerPanel.add(titleLabel);
        return headerPanel;
    }
    
    // ایجاد پنل مرکزی با آمار و تاریخچه
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        centerPanel.setBackground(new Color(240, 248, 255));
        
        // پنل سمت چپ: آمار
        centerPanel.add(createStatisticsPanel());
        
        // پنل سمت راست: تاریخچه
        centerPanel.add(createHistoryPanel());
        
        return centerPanel;
    }
    
    // ایجاد پنل آمار
    private JPanel createStatisticsPanel() {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                "Statistics - آمار کلی",
                javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                new Color(70, 130, 180)
            ),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // فاصله بالا
        statsPanel.add(Box.createVerticalStrut(10));
        
        // تعداد کل دانشجویان
        totalStudentsLabel = new JLabel("0");
        statsPanel.add(createStatRow("Total Students:", "تعداد کل دانشجویان:", totalStudentsLabel, new Color(100, 149, 237)));
        statsPanel.add(Box.createVerticalStrut(15));
        
        // میانگین معدل
        avgGpaLabel = new JLabel("0.00");
        statsPanel.add(createStatRow("Average GPA:", "میانگین معدل:", avgGpaLabel, new Color(60, 179, 113)));
        statsPanel.add(Box.createVerticalStrut(15));
        
        // بالاترین معدل
        maxGpaLabel = new JLabel("0.00");
        statsPanel.add(createStatRow("Highest GPA:", "بالاترین معدل:", maxGpaLabel, new Color(255, 140, 0)));
        statsPanel.add(Box.createVerticalStrut(15));
        
        // پایین‌ترین معدل
        minGpaLabel = new JLabel("0.00");
        statsPanel.add(createStatRow("Lowest GPA:", "پایین‌ترین معدل:", minGpaLabel, new Color(220, 20, 60)));
        statsPanel.add(Box.createVerticalStrut(15));
        
        // تعداد رشته‌های مختلف
        uniqueMajorsLabel = new JLabel("0");
        statsPanel.add(createStatRow("Unique Majors:", "تعداد رشته‌های مختلف:", uniqueMajorsLabel, new Color(147, 112, 219)));
        
        // فاصله پایین
        statsPanel.add(Box.createVerticalGlue());
        
        return statsPanel;
    }
    
    // ایجاد یک سطر آماری
    private JPanel createStatRow(String title, String persianTitle, JLabel valueLabel, Color color) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BorderLayout(10, 5));
        rowPanel.setBackground(Color.WHITE);
        rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        // پنل عنوان
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(color);
        
        JLabel persianTitleLabel = new JLabel(persianTitle);
        persianTitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        persianTitleLabel.setForeground(Color.GRAY);
        
        titlePanel.add(titleLabel);
        titlePanel.add(persianTitleLabel);
        
        // مقدار
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valueLabel.setForeground(color);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // پنل مقدار با پس‌زمینه رنگی
        JPanel valuePanel = new JPanel();
        valuePanel.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 30));
        valuePanel.setBorder(BorderFactory.createLineBorder(color, 2));
        valuePanel.setPreferredSize(new Dimension(100, 60));
        valuePanel.add(valueLabel);
        
        rowPanel.add(titlePanel, BorderLayout.CENTER);
        rowPanel.add(valuePanel, BorderLayout.EAST);
        
        return rowPanel;
    }
    
    // ایجاد پنل تاریخچه
    private JPanel createHistoryPanel() {
        JPanel historyPanel = new JPanel(new BorderLayout(5, 5));
        historyPanel.setBackground(Color.WHITE);
        historyPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                "Operation History - تاریخچه عملیات",
                javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                new Color(70, 130, 180)
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // ایجاد مدل و لیست تاریخچه
        historyModel = new DefaultListModel<>();
        historyList = new JList<>(historyModel);
        historyList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // رنگ‌بندی سطرها
        historyList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (!isSelected) {
                    c.setBackground(index % 2 == 0 ? Color.WHITE : new Color(240, 248, 255));
                }
                return c;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(historyList);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        
        historyPanel.add(scrollPane, BorderLayout.CENTER);
        
        // پنل توضیحات
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(255, 255, 224));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 215, 0)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        JLabel infoLabel = new JLabel("<html><i>This list shows all operations performed on the system<br>" +
                "این لیست تمام عملیات انجام شده در سیستم را نشان می‌دهد</i></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        infoLabel.setForeground(new Color(139, 69, 19));
        
        infoPanel.add(infoLabel);
        historyPanel.add(infoPanel, BorderLayout.SOUTH);
        
        return historyPanel;
    }
    
    // ایجاد پنل دکمه‌ها
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));
        
        // دکمه تازه‌سازی
        JButton refreshButton = createStyledButton("Refresh - تازه‌سازی", new Color(100, 149, 237));
        refreshButton.addActionListener(e -> refreshData());
        
        // دکمه صادرات گزارش (فعلاً فقط نمایش پیام)
        JButton exportButton = createStyledButton("Export Report - صادرات گزارش", new Color(60, 179, 113));
        exportButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                this,
                "Export feature will be available in future versions.\nقابلیت صادرات در نسخه‌های آینده اضافه خواهد شد.",
                "Info",
                JOptionPane.INFORMATION_MESSAGE
            );
        });
        
        // دکمه بازگشت
        JButton backButton = createStyledButton("Back - بازگشت", new Color(128, 128, 128));
        backButton.addActionListener(e -> mainApp.showDashboard());
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(exportButton);
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
        button.setPreferredSize(new Dimension(200, 35));
        
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
    
    // تازه‌سازی داده‌های گزارش
    public void refreshData() {
        // به‌روزرسانی آمار
        StudentRepository.StudentStatistics stats = repository.getStatistics();
        
        totalStudentsLabel.setText(String.valueOf(stats.getTotalStudents()));
        avgGpaLabel.setText(String.format("%.2f", stats.getAverageGpa()));
        maxGpaLabel.setText(String.format("%.2f", stats.getMaxGpa()));
        minGpaLabel.setText(String.format("%.2f", stats.getMinGpa()));
        uniqueMajorsLabel.setText(String.valueOf(stats.getUniqueMajors()));
        
        // به‌روزرسانی تاریخچه
        historyModel.clear();
        List<String> history = repository.getOperationHistory();
        
        if (history.isEmpty()) {
            historyModel.addElement("No operations recorded yet.");
            historyModel.addElement("هنوز هیچ عملیاتی ثبت نشده است.");
        } else {
            // نمایش تاریخچه به صورت معکوس (جدیدترین‌ها اول)
            for (int i = history.size() - 1; i >= 0; i--) {
                historyModel.addElement(history.get(i));
            }
        }
    }
}
