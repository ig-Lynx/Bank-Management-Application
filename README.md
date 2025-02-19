# 🏦 Bank Management Application

## 📌 Overview
This project is a Java-based console application that simulates banking operations. It allows users to create different types of bank accounts, check balances, deposit and withdraw funds, transfer money between accounts, and calculate interest. The application uses Object-Oriented Programming (OOP) principles, including inheritance, polymorphism, and abstraction.

## 🌟 Features
- 🏦 Create Savings, Current, Premium Savings, and Premium Current accounts
- 💰 Deposit and withdraw money with different methods
- 🔄 Fund transfers between accounts
- 📈 Interest calculation for eligible accounts
- 🏷️ View account details including IFSC code and branch name
- ⚖️ Enforce banking rules such as overdraft limits and minimum balances
- 📂 Maintain an array of accounts for user management

## 🛠️ Technologies Used
### 1. ☕ Java
- The core programming language used for implementing the application.
- Provides object-oriented features such as inheritance, encapsulation, and polymorphism.

### 2. 🎯 Object-Oriented Programming (OOP) Principles
- **Encapsulation**: Private variables with public getter and setter methods.
- **Inheritance**: `BankAccount` as the base class, with `SavingsAccount`, `CurrentAccount`, and `PremiumAccount` as derived classes.
- **Polymorphism**: Overriding deposit and withdraw methods in subclasses.
- **Abstraction**: `AccountServices` interface and `PremiumAccount` abstract class.

### 3. 🔌 Interface (`AccountServices`)
- Defines standard banking operations such as deposit, withdraw, and fund transfer.
- Enforces full implementation in all account types.

### 4. 🏛️ Abstract Class (`PremiumAccount`)
- Restricts object creation while allowing common methods for premium account types.
- Implements abstract method `getBenefits()` which is defined uniquely in subclasses.

### 5. ⚠️ Exception Handling
- Prevents withdrawal beyond balance or overdraft limit.
- Ensures deposits and withdrawals are non-negative.

### 6. 🔧 Utility Class (`BankUtil`)
- Generates unique account numbers using a static counter.

## 🚀 Installation & Setup
1. **🔨 Compile the Java program**
   ```bash
   javac BankApp.java
   ```
2. **▶️ Run the Application**
   ```bash
   java BankApp
   ```

## 📂 File Structure
```
├── BankApp.java            # Main application logic and user interactions
├── BankAccount.java        # Base class for account types
├── SavingsAccount.java     # Savings account implementation
├── CurrentAccount.java     # Current account implementation
├── PremiumAccount.java     # Abstract class for premium accounts
├── PremiumSavingsAccount.java  # Premium savings account with high interest
├── PremiumCurrentAccount.java  # Premium current account with overdraft
└── BankUtil.java           # Utility class for account number generation
```

## 🎯 Usage
1. 🏦 Run the application and follow the menu options.
2. 🆕 Create an account by providing the account holder's name and initial balance.
3. 💳 Perform transactions such as deposits, withdrawals, and transfers.
4. 🔍 Check balance and account details.
5. 🎁 View benefits if the account is a premium account.
6. 📊 Calculate interest for applicable accounts.

## 🔮 Future Enhancements
- 🖥️ Add a graphical user interface (GUI) for better user experience.
- 🗄️ Implement database storage for persistent account records.
- 🌍 Support for online banking features like bill payments.

## 👨‍💻 Contributors
- **Your Name** (your.email@example.com)
- **Additional Contributors** (if any)

## 📜 License
This project is licensed under the MIT License.

