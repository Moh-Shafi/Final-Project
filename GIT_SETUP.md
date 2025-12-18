# Git Setup and GitHub Upload Guide
## راهنمای راه‌اندازی Git و آپلود به GitHub

---

## Step 1: Install Git

### Windows
1. Download Git from: https://git-scm.com/download/win
2. Run installer with default settings
3. Verify installation:
   ```bash
   git --version
   ```

### Linux
```bash
sudo apt-get install git
```

### Mac
```bash
brew install git
```

---

## Step 2: Configure Git (First Time Only)

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

---

## Step 3: Initialize Local Repository

Open terminal in the project folder:

```bash
cd c:\Users\shafi\Desktop\PL

# Initialize git repository
git init

# Add all files to staging
git add .

# Create first commit
git commit -m "Initial commit: Student Management System UAP"
```

---

## Step 4: Create GitHub Repository

1. Go to https://github.com
2. Click "New Repository" (green button)
3. Repository name: `student-management-system-uap`
4. Description: `Student Management System - UAP Pemrograman Lanjut`
5. Choose: **Public** (or Private if you prefer)
6. **DO NOT** check "Initialize with README" (we already have one)
7. Click "Create Repository"

---

## Step 5: Connect Local to GitHub

GitHub will show you commands. Use these:

```bash
# Add remote repository
git remote add origin https://github.com/YOUR_USERNAME/student-management-system-uap.git

# Push to GitHub
git branch -M main
git push -u origin main
```

Replace `YOUR_USERNAME` with your actual GitHub username.

---

## Step 6: Verify Upload

1. Refresh your GitHub repository page
2. You should see all files uploaded
3. README.md will be displayed automatically

---

## Branching Strategy (As Required by UAP)

### Create Feature Branches

```bash
# Create and switch to feature-crud branch
git checkout -b feature-crud
git add .
git commit -m "Implement CRUD operations"
git push origin feature-crud

# Create feature-ui branch
git checkout -b feature-ui
git add .
git commit -m "Implement Swing UI with 4 panels"
git push origin feature-ui

# Create feature-validation branch
git checkout -b feature-validation
git add .
git commit -m "Add input validation and exception handling"
git push origin feature-validation
```

### Merge Branches to Main

```bash
# Switch back to main
git checkout main

# Merge feature branches
git merge feature-crud
git merge feature-ui
git merge feature-validation

# Push merged changes
git push origin main
```

---

## Regular Updates (After Making Changes)

```bash
# Check status
git status

# Add changed files
git add .

# Commit with message
git commit -m "Description of changes"

# Push to GitHub
git push origin main
```

---

## Common Git Commands

| Command | Description |
|---------|-------------|
| `git status` | Check current status |
| `git add .` | Add all changes |
| `git add filename` | Add specific file |
| `git commit -m "message"` | Commit changes |
| `git push` | Upload to GitHub |
| `git pull` | Download from GitHub |
| `git log` | View commit history |
| `git branch` | List branches |
| `git checkout -b name` | Create new branch |
| `git merge branch` | Merge branch |

---

## .gitignore File

The `.gitignore` file is already created and will prevent uploading:
- Compiled `.class` files
- IDE configuration files
- Temporary files
- Log files

This keeps your repository clean!

---

## Troubleshooting

### Error: "remote origin already exists"
```bash
git remote remove origin
git remote add origin https://github.com/YOUR_USERNAME/repo-name.git
```

### Error: "failed to push"
```bash
git pull origin main --allow-unrelated-histories
git push origin main
```

### Error: Authentication failed
- Use Personal Access Token instead of password
- Generate token at: GitHub → Settings → Developer settings → Personal access tokens

---

## Best Practices for UAP

1. **Commit regularly** with clear messages
2. **Use branches** for different features
3. **Write descriptive commit messages**:
   - ✅ Good: "Add student validation in form panel"
   - ❌ Bad: "update"
4. **Push frequently** to avoid losing work
5. **Keep README updated** with latest features

---

## Example Commit Messages

```bash
git commit -m "Add Student model class with CSV conversion"
git commit -m "Implement StudentRepository with CRUD operations"
git commit -m "Create Dashboard panel with statistics"
git commit -m "Add search and sort functionality to student list"
git commit -m "Implement form validation with exception handling"
git commit -m "Add operation history to report panel"
git commit -m "Update README with installation instructions"
```

---

## Repository Structure on GitHub

After upload, your repository will show:

```
student-management-system-uap/
├── .gitignore
├── README.md
├── COMPILE_AND_RUN.md
├── GIT_SETUP.md
├── Student.java
├── StudentRepository.java
├── StudentManagementApp.java
├── DashboardPanel.java
├── StudentListPanel.java
├── StudentFormPanel.java
├── ReportPanel.java
└── students_data.csv
```

---

## Submission Checklist for UAP

- [x] Repository created on GitHub
- [x] All source code uploaded
- [x] README.md with complete documentation
- [x] .gitignore file present
- [x] Multiple commits showing development progress
- [x] Feature branches created and merged
- [x] Clear commit messages
- [x] Repository link ready to submit

---

## Quick Reference

### First Time Setup
```bash
cd c:\Users\shafi\Desktop\PL
git init
git add .
git commit -m "Initial commit: Student Management System"
git remote add origin https://github.com/USERNAME/REPO.git
git push -u origin main
```

### Regular Updates
```bash
git add .
git commit -m "Your message here"
git push
```

---

**Ready to submit! - آماده برای تحویل!**

Remember to add your repository link to the spreadsheet mentioned in the UAP instructions.
