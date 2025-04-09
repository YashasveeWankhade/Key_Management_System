# 🔐 Key Management System

A **Java-based desktop application** designed to streamline the issuance, submission, and tracking of physical keys. With a secure login system and intuitive Swing-based GUI, administrators can efficiently manage key inventory and monitor transaction history.

---

## ✨ Features

- ✅ **Secure Login System**  
  Multi-layered authentication ensures restricted access.

- 🔑 **Issue Keys**  
  Track who has borrowed which key and when.

- 🔁 **Submit Keys**  
  Record key returns and update availability.

- 📋 **Check Availability**  
  Instantly view which keys are currently in use or available.

- 📜 **Record History**  
  Maintain a complete transaction log with timestamps.

- ➕ **Add New Keys**  
  Easily add and categorize new keys into the system.

---

## 🏗️ System Architecture

This project follows the **Model-View-Controller (MVC)** architectural pattern for modularity and ease of maintenance.

### 📦 Model
- **Database Layer**: MySQL database to store key and user data.
- **Entity Classes**: Represents core entities like `Key`, `User`, `Transaction`.
- **Data Access Layer**: JDBC-based operations for all DB interactions.

### 🎨 View
- **GUI**: Java Swing components for all user interfaces.
- **Panels**: Functional modules (issue, submit, history, etc.).
- **Styling**: Consistent, user-friendly layout.

### 🧠 Controller
- **Action Handlers**: Respond to UI events and invoke appropriate logic.
- **Business Logic**: Enforces issuance and return rules.
- **Validation**: Checks inputs and secures data operations.

---

## 🛠️ Tech Stack

| Layer       | Technology         |
|-------------|--------------------|
| Language    | Java               |
| GUI         | Java Swing         |
| DBMS        | MySQL              |
| Connectivity| JDBC               |
| Build       | Native Java Compile|

---

## 🧩 Database Schema

The database includes the following tables:

- `login`: Stores user authentication credentials
- `details`: Contains metadata about each key
- `status`: Tracks key availability
- `history`: Logs all key issuance/return transactions

---

## 🚀 Installation & Setup

1. ✅ Install Java Runtime Environment (JRE)
2. ✅ Install MySQL Server
3. ✅ Create a MySQL database named `key_management`
4. ✅ Execute the provided SQL script to generate required tables
5. 🔧 Configure DB settings in `DatabaseConnection.java`
6. 🛠️ Compile `.java` files or run the provided `.jar`

---

## 📚 Usage Flow

1. 🔐 **Login** – Authenticate using valid credentials
2. 🔑 **Issue Key** – Select a key and log recipient information
3. 🔁 **Submit Key** – Mark key as returned and update status
4. 👁️ **Check Availability** – View current status of all keys
5. 🕑 **View History** – Access complete audit trail
6. ➕ **Add New Key** – Introduce new keys into the system

---

## 🛡️ Security Features

- 🔒 Password-protected login system
- 🔐 Encrypted DB connections
- 🚫 SQL injection protection via input validation
- 🕓 Session lifecycle management

---

## 📈 Future Enhancements

- 📧 Email notifications for overdue keys
- 📷 Barcode / QR code support for faster key scanning
- 👥 Role-based access control for different staff levels
- 📊 Advanced analytics and usage reports
- 📱 Mobile app integration for remote access

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you'd like to change.

---

## 📄 License

This project is currently under proprietary license. All rights reserved.

---

## 📬 Contact

For suggestions, support, or feature requests:  
📧 **Email:** [you@example.com]

---

🔨🤖🔧 Built with care using Java, Swing & MySQL.
