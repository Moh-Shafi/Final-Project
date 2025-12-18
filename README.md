# Student Management System
## سیستم مدیریت دانشجویان

**Version:** 1.0  
**Date:** December 2024  
**Course:** Pemrograman Lanjut - UAP (Ujian Akhir Praktikum)

---

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technical Specifications](#technical-specifications)
- [Installation & Setup](#installation--setup)
- [How to Run](#how-to-run)
- [Project Structure](#project-structure)
- [Usage Guide](#usage-guide)
- [Testing Documentation](#testing-documentation)
- [Contributors](#contributors)

---

## 🎯 Overview

Student Management System is a desktop application built with **Java Swing** that provides a comprehensive solution for managing student records. The application implements full CRUD operations (Create, Read, Update, Delete) with persistent data storage using CSV files.

**سیستم مدیریت دانشجویان** یک برنامه دسکتاپ است که با استفاده از Java Swing ساخته شده و امکان مدیریت کامل اطلاعات دانشجویان را فراهم می‌کند. این برنامه عملیات CRUD کامل را پیاده‌سازی کرده و داده‌ها را به صورت دائمی در فایل CSV ذخیره می‌کند.

---

## ✨ Features

### 1. **Dashboard (صفحه اصلی)**
- Welcome screen with navigation menu
- Real-time statistics display:
  - Total number of students
  - Average GPA
  - Highest and lowest GPA
  - Number of unique majors
- Quick access buttons to all features

### 2. **Student List (لیست دانشجویان)**
- Display all students in a sortable table
- **Search functionality**: Real-time search by name, ID, major, or email
- **Sorting**: Click column headers to sort data
- **CRUD Operations**:
  - Add new student
  - Edit existing student (double-click or use Edit button)
  - Delete student with confirmation
- Color-coded rows for better readability

### 3. **Student Form (فرم افزودن/ویرایش)**
- User-friendly form for adding/editing students
- **Input fields**:
  - Student ID (unique identifier)
  - Full Name
  - Major (field of study)
  - GPA (0.00 - 4.00)
  - Enrollment Date (YYYY-MM-DD format)
  - Email
- **Comprehensive validation**:
  - Required field checks
  - Format validation (email, date, GPA range)
  - Duplicate ID prevention
- Clear error messages in both English and Persian

### 4. **Reports & History (گزارش‌ها و تاریخچه)**
- Detailed statistics panel
- Operation history log showing all actions performed
- Export functionality (planned for future versions)

---

## 🛠 Technical Specifications

### Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **IDE** (Optional): IntelliJ IDEA, Eclipse, NetBeans, or VS Code

### Technologies Used
- **GUI Framework**: Java Swing
- **Data Storage**: CSV file format
- **Java APIs**:
  - `java.time.LocalDate` for date handling
  - `java.util.ArrayList` for in-memory data management
  - `java.util.Comparator` for sorting
  - `java.io.*` for file operations

### Design Patterns
- **Repository Pattern**: Separates data access logic from business logic
- **MVC-like Structure**: Clear separation between UI panels and data management
- **CardLayout**: For seamless navigation between screens

### Exception Handling
- Try-catch blocks for file I/O operations
- Input validation with custom error messages
- User-friendly error dialogs

---

## 📥 Installation & Setup

### Step 1: Clone the Repository
```bash
git clone <your-repository-url>
cd PL
```

### Step 2: Verify Java Installation
```bash
java -version
```
Make sure you have JDK 8 or higher installed.

### Step 3: Compile the Project
```bash
javac *.java
```

### Step 4: Run the Application
```bash
java StudentManagementApp
```

---

## 🚀 How to Run

### Method 1: Command Line
1. Open terminal/command prompt
2. Navigate to the project directory:
   ```bash
   cd c:\Users\shafi\Desktop\PL
   ```
3. Compile all Java files:
   ```bash
   javac *.java
   ```
4. Run the main application:
   ```bash
   java StudentManagementApp
   ```

### Method 2: Using IDE
1. Open the project folder in your IDE (IntelliJ IDEA, Eclipse, etc.)
2. Locate `StudentManagementApp.java`
3. Right-click and select "Run" or press the Run button
4. The application window will appear

### Method 3: Create JAR File (Optional)
```bash
# Compile all files
javac *.java

# Create JAR
jar cvfe StudentManagementApp.jar StudentManagementApp *.class

# Run JAR
java -jar StudentManagementApp.jar
```

---

## 📁 Project Structure

```
PL/
│
├── Student.java                  # Model class for student data
├── StudentRepository.java        # Data access layer (CRUD + File I/O)
├── StudentManagementApp.java    # Main application class
├── DashboardPanel.java           # Dashboard UI (Page 1)
├── StudentListPanel.java         # Student list UI (Page 2)
├── StudentFormPanel.java         # Add/Edit form UI (Page 3)
├── ReportPanel.java              # Reports & history UI (Page 4)
├── students_data.csv             # Data storage file (auto-created)
└── README.md                     # This file
```

### File Descriptions

| File | Description |
|------|-------------|
| `Student.java` | Defines the Student model with fields: ID, name, major, GPA, enrollment date, email |
| `StudentRepository.java` | Handles all data operations: CRUD, file I/O, search, sorting, statistics |
| `StudentManagementApp.java` | Main frame with CardLayout for screen navigation |
| `DashboardPanel.java` | Home screen with statistics and navigation buttons |
| `StudentListPanel.java` | Table view with search, sort, and CRUD operations |
| `StudentFormPanel.java` | Form for adding/editing students with validation |
| `ReportPanel.java` | Statistics and operation history display |
| `students_data.csv` | CSV file for persistent data storage (created automatically) |

---

## 📖 Usage Guide

### Adding a New Student
1. Click **"Add Student"** button from Dashboard or Student List
2. Fill in all required fields:
   - Student ID (must be unique, minimum 5 characters)
   - Full Name (minimum 3 characters)
   - Major
   - GPA (0.00 to 4.00)
   - Enrollment Date (format: YYYY-MM-DD)
   - Email (valid email format)
3. Click **"Save"** button
4. Success message will appear and you'll return to the student list

### Editing a Student
1. Go to **Student List** page
2. **Double-click** on a student row, OR select a row and click **"Edit"**
3. Modify the desired fields (Student ID cannot be changed)
4. Click **"Save"** to update

### Deleting a Student
1. Go to **Student List** page
2. Select a student row
3. Click **"Delete"** button
4. Confirm the deletion in the dialog box
5. Student will be removed from the system

### Searching Students
1. Go to **Student List** page
2. Type in the **Search** field
3. Results will filter automatically as you type
4. Search works on: Student ID, Name, Major, and Email

### Sorting Students
1. Go to **Student List** page
2. Click on any **column header** to sort by that column
3. Click again to reverse the sort order

### Viewing Reports
1. Click **"Reports"** button from Dashboard
2. View statistics:
   - Total students
   - Average GPA
   - Highest/Lowest GPA
   - Number of unique majors
3. Check operation history in the right panel

---

## 🧪 Testing Documentation

### Manual Testing Scenarios

#### Test Case 1: Add Student
**Steps:**
1. Navigate to Add Student form
2. Enter valid data for all fields
3. Click Save

**Expected Result:**
- Student appears in the student list
- Data is saved to `students_data.csv`
- Success message is displayed

**Status:** ✅ Passed

#### Test Case 2: Duplicate ID Prevention
**Steps:**
1. Try to add a student with an existing Student ID
2. Click Save

**Expected Result:**
- Error message: "شماره دانشجویی قبلاً ثبت شده است"
- Student is not added

**Status:** ✅ Passed

#### Test Case 3: Invalid GPA
**Steps:**
1. Enter GPA value outside 0.00-4.00 range (e.g., 5.0)
2. Click Save

**Expected Result:**
- Error message: "معدل باید بین 0.00 تا 4.00 باشد"
- Form is not submitted

**Status:** ✅ Passed

#### Test Case 4: Search Functionality
**Steps:**
1. Go to Student List
2. Enter search keyword
3. Verify filtered results

**Expected Result:**
- Only matching students are displayed
- Search works on all fields

**Status:** ✅ Passed

#### Test Case 5: Data Persistence
**Steps:**
1. Add/Edit/Delete students
2. Close application
3. Reopen application

**Expected Result:**
- All changes are preserved
- Data loads correctly from CSV file

**Status:** ✅ Passed

### Code Review Findings

#### Issue 1: Variable Naming
**Finding:** Some variable names could be more descriptive  
**Solution:** Renamed variables to follow Java naming conventions (e.g., `repo` → `repository`)

#### Issue 2: Exception Handling
**Finding:** Need more specific exception types  
**Solution:** Added `IllegalArgumentException` for validation errors, separate from `IOException`

#### Issue 3: Code Duplication
**Finding:** Button styling code repeated in multiple panels  
**Solution:** Created `createStyledButton()` helper method in each panel

#### Issue 4: Magic Numbers
**Finding:** Hard-coded color values and dimensions  
**Solution:** Extracted to named Color objects for consistency

---

## 👥 Contributors

**Team Members:**
- [Your Name 1]
- [Your Name 2]

**Supervisor:**
- Ir. Wildan Suharso, M.Kom.

**Institution:**
- Universitas Muhammadiyah Malang
- Laboratorium Informatika

---

## 📝 Notes

### Data File Format
The `students_data.csv` file uses the following format:
```csv
StudentID,Name,Major,GPA,EnrollmentDate,Email
202410001,Ahmad Rizki,Computer Science,3.75,2024-09-01,ahmad@email.com
202410002,Siti Nurhaliza,Information Systems,3.90,2024-09-01,siti@email.com
```

### Future Enhancements
- Export reports to PDF
- Import students from Excel
- Student photo upload
- Grade management system
- Attendance tracking
- Email notifications

### Known Issues
- None at this time

---

## 📄 License

This project is created for educational purposes as part of the UAP (Ujian Akhir Praktikum) for Pemrograman Lanjut course.

---

## 📞 Support

For questions or issues, please contact:
- Email: [your-email@example.com]
- GitHub Issues: [repository-url]/issues

---

**Last Updated:** December 2024  
**Version:** 1.0.0
