import javax.swing.*;
import java.awt.*;

// پنل صفحه اصلی (Dashboard) - صفحه اول برنامه
// این صفحه شامل منوی ناوبری و نمایش آمار کلی است
public class DashboardPanel extends JPanel {
    private StudentManagementApp mainApp;
    
    // لیبل‌ها برای نمایش آمار
    private JLabel totalStudentsLabel;
    private JLabel avgGpaLabel;
    private JLabel maxGpaLabel;
    private JLabel minGpaLabel;
    
    // سازنده: ایجاد رابط کاربری Dashboard
    public DashboardPanel(StudentManagementApp mainApp) {
        this.mainApp = mainApp;
        
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 248, 255));
        
        // اضافه کردن اجزای مختلف
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createNavigationPanel(), BorderLayout.SOUTH);
    }
    
    // ایجاد پنل هدر با عنوان
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel titleLabel = new JLabel("Student Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel subtitleLabel = new JLabel("سیستم مدیریت دانشجویان");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(230, 240, 255));
        
        headerPanel.setLayout(new GridLayout(2, 1));
        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);
        
        return headerPanel;
    }
    
    // ایجاد پنل مرکزی با آمار و اطلاعات
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        
        // پنل خوش‌آمدگویی
        JPanel welcomePanel = createWelcomePanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        centerPanel.add(welcomePanel, gbc);
        
        // پنل‌های آماری
        JPanel statsPanel = createStatisticsPanel();
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        centerPanel.add(statsPanel, gbc);
        
        return centerPanel;
    }
    
    // ایجاد پنل خوش‌آمدگویی
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel welcomeLabel = new JLabel("<html><center>Welcome to Student Management System<br>" +
                "خوش آمدید به سیستم مدیریت دانشجویان</center></html>");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(70, 130, 180));
        
        panel.add(welcomeLabel);
        return panel;
    }
    
    // ایجاد پنل آمار
    private JPanel createStatisticsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBackground(new Color(240, 248, 255));
        
        // کارت آماری برای تعداد کل دانشجویان
        totalStudentsLabel = new JLabel("0");
        panel.add(createStatCard("Total Students", "تعداد کل دانشجویان", totalStudentsLabel, new Color(100, 149, 237)));
        
        // کارت آماری برای میانگین معدل
        avgGpaLabel = new JLabel("0.00");
        panel.add(createStatCard("Average GPA", "میانگین معدل", avgGpaLabel, new Color(60, 179, 113)));
        
        // کارت آماری برای بالاترین معدل
        maxGpaLabel = new JLabel("0.00");
        panel.add(createStatCard("Highest GPA", "بالاترین معدل", maxGpaLabel, new Color(255, 140, 0)));
        
        // کارت آماری برای پایین‌ترین معدل
        minGpaLabel = new JLabel("0.00");
        panel.add(createStatCard("Lowest GPA", "پایین‌ترین معدل", minGpaLabel, new Color(220, 20, 60)));
        
        return panel;
    }
    
    // ایجاد یک کارت آماری
    private JPanel createStatCard(String title, String persianTitle, JLabel valueLabel, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(5, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 3),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // عنوان کارت
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(color);
        
        JLabel persianTitleLabel = new JLabel(persianTitle, SwingConstants.CENTER);
        persianTitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        persianTitleLabel.setForeground(Color.GRAY);
        
        titlePanel.add(titleLabel);
        titlePanel.add(persianTitleLabel);
        
        // مقدار آماری
        valueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setForeground(color);
        
        card.add(titlePanel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    // ایجاد پنل ناوبری با دکمه‌های منو
    private JPanel createNavigationPanel() {
        JPanel navPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        navPanel.setBackground(new Color(240, 248, 255));
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        // دکمه لیست دانشجویان
        JButton listButton = createNavButton("View Students", "مشاهده لیست", new Color(70, 130, 180));
        listButton.addActionListener(e -> mainApp.showStudentList());
        navPanel.add(listButton);
        
        // دکمه افزودن دانشجو
        JButton addButton = createNavButton("Add Student", "افزودن دانشجو", new Color(60, 179, 113));
        addButton.addActionListener(e -> mainApp.showAddStudentForm());
        navPanel.add(addButton);
        
        // دکمه گزارش
        JButton reportButton = createNavButton("Reports", "گزارش‌ها", new Color(255, 140, 0));
        reportButton.addActionListener(e -> mainApp.showReport());
        navPanel.add(reportButton);
        
        // دکمه خروج
        JButton exitButton = createNavButton("Exit", "خروج", new Color(220, 20, 60));
        exitButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?\nآیا مطمئن هستید که می‌خواهید خارج شوید؟",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        navPanel.add(exitButton);
        
        return navPanel;
    }
    
    // ایجاد دکمه ناوبری
    private JButton createNavButton(String text, String persianText, Color color) {
        JButton button = new JButton("<html><center>" + text + "<br>" + persianText + "</center></html>");
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 60));
        
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
    
    // به‌روزرسانی آمار از Repository
    public void updateStatistics() {
        StudentRepository.StudentStatistics stats = mainApp.getRepository().getStatistics();
        
        totalStudentsLabel.setText(String.valueOf(stats.getTotalStudents()));
        avgGpaLabel.setText(String.format("%.2f", stats.getAverageGpa()));
        maxGpaLabel.setText(String.format("%.2f", stats.getMaxGpa()));
        minGpaLabel.setText(String.format("%.2f", stats.getMinGpa()));
    }
}
