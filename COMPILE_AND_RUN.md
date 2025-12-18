# How to Compile and Run - راهنمای کامپایل و اجرا

## Prerequisites - پیش‌نیازها

### Install Java JDK
1. Download JDK from: https://www.oracle.com/java/technologies/downloads/
2. Install JDK (recommended: JDK 17 or higher)
3. Add Java to PATH:
   - Windows: 
     - Search "Environment Variables" in Start Menu
     - Add `C:\Program Files\Java\jdk-XX\bin` to PATH
   - Verify installation:
     ```bash
     java -version
     javac -version
     ```

---

## Method 1: Command Line (Recommended)

### Step 1: Open Terminal
- **Windows**: Press `Win + R`, type `cmd`, press Enter
- Navigate to project folder:
  ```bash
  cd c:\Users\shafi\Desktop\PL
  ```

### Step 2: Compile All Files
```bash
javac Student.java StudentRepository.java DashboardPanel.java StudentListPanel.java StudentFormPanel.java ReportPanel.java StudentManagementApp.java
```

### Step 3: Run the Application
```bash
java StudentManagementApp
```

---

## Method 2: Using IntelliJ IDEA

### Step 1: Open Project
1. Open IntelliJ IDEA
2. Click "Open" and select the `PL` folder
3. Wait for indexing to complete

### Step 2: Run
1. Right-click on `StudentManagementApp.java`
2. Select "Run 'StudentManagementApp.main()'"
3. Application window will appear

---

## Method 3: Using VS Code

### Step 1: Install Extensions
1. Install "Extension Pack for Java" from VS Code marketplace

### Step 2: Open and Run
1. Open the `PL` folder in VS Code
2. Open `StudentManagementApp.java`
3. Click "Run" button above the `main` method
4. Or press `F5`

---

## Method 4: Using Eclipse

### Step 1: Create Project
1. File → New → Java Project
2. Uncheck "Use default location"
3. Browse to `c:\Users\shafi\Desktop\PL`
4. Click Finish

### Step 2: Run
1. Right-click on `StudentManagementApp.java`
2. Run As → Java Application

---

## Method 5: Create Executable JAR

### Step 1: Compile
```bash
javac *.java
```

### Step 2: Create Manifest File
Create `manifest.txt`:
```
Main-Class: StudentManagementApp
```

### Step 3: Create JAR
```bash
jar cvfm StudentManagementApp.jar manifest.txt *.class
```

### Step 4: Run JAR
```bash
java -jar StudentManagementApp.jar
```

Or double-click the JAR file (if Java is properly installed)

---

## Troubleshooting - رفع مشکلات

### Error: "javac is not recognized"
**Problem:** Java is not in PATH  
**Solution:** 
1. Find Java installation directory (usually `C:\Program Files\Java\jdk-XX\bin`)
2. Add to System PATH
3. Restart terminal/IDE

### Error: "Could not find or load main class"
**Problem:** Wrong directory or compilation issue  
**Solution:**
1. Make sure you're in the correct directory
2. Delete all `.class` files
3. Recompile: `javac *.java`
4. Run: `java StudentManagementApp`

### Error: File encoding issues
**Problem:** CSV file encoding  
**Solution:**
```bash
javac -encoding UTF-8 *.java
java StudentManagementApp
```

### Application doesn't start
**Problem:** Missing dependencies or wrong Java version  
**Solution:**
1. Verify Java version: `java -version` (should be 8+)
2. Check all `.java` files are present
3. Recompile all files

---

## Quick Start Commands

### Windows PowerShell
```powershell
cd c:\Users\shafi\Desktop\PL
javac *.java
java StudentManagementApp
```

### Windows CMD
```cmd
cd c:\Users\shafi\Desktop\PL
javac *.java
java StudentManagementApp
```

### Linux/Mac
```bash
cd ~/Desktop/PL
javac *.java
java StudentManagementApp
```

---

## First Run

When you run the application for the first time:
1. A window titled "Student Management System" will appear
2. The file `students_data.csv` will be created automatically
3. You can start adding students immediately

---

## Sample Data (Optional)

If you want to test with sample data, create `students_data.csv` with:

```csv
StudentID,Name,Major,GPA,EnrollmentDate,Email
202410001,Ahmad Rizki,Computer Science,3.75,2024-09-01,ahmad@email.com
202410002,Siti Nurhaliza,Information Systems,3.90,2024-09-01,siti@email.com
202410003,Budi Santoso,Software Engineering,3.65,2024-09-01,budi@email.com
202410004,Dewi Lestari,Data Science,3.85,2024-09-01,dewi@email.com
202410005,Eko Prasetyo,Cyber Security,3.70,2024-09-01,eko@email.com
```

---

## Need Help?

If you encounter any issues:
1. Check that Java JDK is installed correctly
2. Verify all `.java` files are in the same directory
3. Make sure you're running commands from the correct directory
4. Check the README.md for more detailed information

---

**Good Luck! - موفق باشید!**
