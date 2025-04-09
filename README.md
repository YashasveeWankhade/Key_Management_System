<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Key Management System</title>
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
            line-height: 1.6;
            color: #24292e;
            max-width: 1012px;
            margin: 0 auto;
            padding: 32px;
        }
        h1, h2, h3 {
            font-weight: 600;
            margin-top: 24px;
            margin-bottom: 16px;
        }
        h1 {
            font-size: 2em;
            padding-bottom: 0.3em;
            border-bottom: 1px solid #eaecef;
        }
        h2 {
            font-size: 1.5em;
            padding-bottom: 0.3em;
            border-bottom: 1px solid #eaecef;
        }
        h3 {
            font-size: 1.25em;
        }
        code {
            font-family: SFMono-Regular, Consolas, 'Liberation Mono', Menlo, monospace;
            background-color: rgba(27, 31, 35, 0.05);
            border-radius: 3px;
            padding: 0.2em 0.4em;
            font-size: 85%;
        }
        ul {
            padding-left: 2em;
        }
        img {
            max-width: 100%;
            height: auto;
        }
        .diagram {
            text-align: center;
            margin: 20px 0;
        }
        .features-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 16px;
            margin: 20px 0;
        }
        .feature-card {
            border: 1px solid #e1e4e8;
            border-radius: 6px;
            padding: 16px;
            background-color: #f6f8fa;
        }
        .feature-card h3 {
            margin-top: 0;
            margin-bottom: 10px;
            color: #0366d6;
        }
        .architecture {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .architecture-layer {
            width: 100%;
            margin-bottom: 20px;
            padding: 20px;
            background-color: #f6f8fa;
            border-radius: 6px;
            border: 1px solid #e1e4e8;
        }
        .screenshot {
            border: 1px solid #e1e4e8;
            border-radius: 6px;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <h1>Key Management System</h1>
    <p>A comprehensive Java desktop application designed to manage and track keys for organizations, facilitating easy issuance, submission, and monitoring of keys.</p>

    <h2>Features</h2>
    <div class="features-grid">
        <div class="feature-card">
            <h3>🔐 Secure Login</h3>
            <p>Multi-layered authentication system to ensure that only authorized personnel can access the key management features.</p>
        </div>
        <div class="feature-card">
            <h3>🔑 Issue Keys</h3>
            <p>Record and track key issuance with details of the recipient, timestamp, and key identifiers.</p>
        </div>
        <div class="feature-card">
            <h3>↩️ Submit Keys</h3>
            <p>Log key returns with automatic timestamp recording and availability status updates.</p>
        </div>
        <div class="feature-card">
            <h3>🔍 Check Availability</h3>
            <p>Quickly verify which keys are currently available or in use with a simple interface.</p>
        </div>
        <div class="feature-card">
            <h3>📊 Record History</h3>
            <p>Maintain a comprehensive log of all key transactions for audit and tracking purposes.</p>
        </div>
        <div class="feature-card">
            <h3>➕ Add New Keys</h3>
            <p>Easily add new keys to the system with custom identifiers and descriptions.</p>
        </div>
    </div>

    <h2>System Architecture</h2>
    <div class="architecture">
        <p>The application implements the Model-View-Controller (MVC) architectural pattern for clean separation of concerns:</p>
        
        <div class="architecture-layer">
            <h3>🖥️ View Layer (Presentation)</h3>
            <ul>
                <li><code>Login.java</code> - The initial login interface</li>
                <li><code>Key.java</code> - Main application window with tabbed interface</li>
                <li><code>IssueKeyPanel.java</code> - Panel for issuing keys</li>
                <li><code>SubmitKeyPanel.java</code> - Panel for submitting keys</li>
                <li><code>CheckAvailabilityPanel.java</code> - Panel for checking key availability</li>
                <li><code>RecordHistoryPanel.java</code> - Panel for viewing transaction history</li>
                <li><code>NewKey.java</code> - Panel for adding new keys</li>
                <li><code>Button_Style.java</code> - Utility for consistent UI styling</li>
            </ul>
        </div>
        
        <div class="architecture-layer">
            <h3>🎮 Controller Layer (Business Logic)</h3>
            <ul>
                <li><code>IssueKeyController.java</code> - Handles key issuance logic</li>
                <li><code>SubmitKeyController.java</code> - Manages key submission operations</li>
                <li><code>CheckAvailabilityController.java</code> - Processes availability checks</li>
                <li><code>RecordHistoryController.java</code> - Controls history display and refreshing</li>
                <li><code>NewKeyController.java</code> - Manages adding new keys to the system</li>
            </ul>
        </div>
        
        <div class="architecture-layer">
            <h3>📦 Model Layer (Data & Database)</h3>
            <ul>
                <li><code>DatabaseConnection.java</code> - Manages database connectivity</li>
                <li>Database Tables:
                    <ul>
                        <li><code>login</code> - Authentication data</li>
                        <li><code>details</code> - Key information</li>
                        <li><code>status</code> - Current availability status</li>
                        <li><code>history</code> - Transaction records</li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

    <div class="diagram">
        <h3>System Flow Diagram</h3>
        <img src="/api/placeholder/800/400" alt="Key Management System Flow Diagram">
    </div>

    <h2>Database Schema</h2>
    <p>The application uses the following database tables:</p>
    <ul>
        <li><strong>login</strong> - Stores user credentials for authentication</li>
        <li><strong>details</strong> - Contains key information (ID, KeyName)</li>
        <li><strong>status</strong> - Tracks key availability (ID, Availability)</li>
        <li><strong>history</strong> - Records transactions (ID, KeyName, IssuedBy, IssueDate, IssueTime, SubmissionDate, SubmissionTime)</li>
    </ul>

    <h2>Technology Stack</h2>
    <ul>
        <li><strong>Programming Language:</strong> Java</li>
        <li><strong>UI Framework:</strong> Java Swing</li>
        <li><strong>Database:</strong> MySQL</li>
        <li><strong>Database Connectivity:</strong> JDBC</li>
    </ul>

    <h2>Installation & Setup</h2>
    <ol>
        <li>Clone the repository:
            <code>git clone https://github.com/yourusername/key-management-system.git</code>
        </li>
        <li>Ensure you have Java Development Kit (JDK) installed</li>
        <li>Install MySQL and create a database named <code>key_management</code></li>
        <li>Run the SQL script in the <code>database</code> folder to create the necessary tables</li>
        <li>Configure database connection in <code>src/DatabaseConnection.java</code> if needed</li>
        <li>Compile and run the application:
            <code>javac -d bin src/*.java</code><br>
            <code>java -cp bin App</code>
        </li>
    </ol>

    <h2>Usage</h2>
    <ol>
        <li>Launch the application and login with your credentials</li>
        <li>Navigate through the tabs to access different functionalities</li>
        <li>Issue keys by selecting the key ID, name, and entering recipient information</li>
        <li>Submit keys by selecting the key details and confirming the return</li>
        <li>Check key availability at any time</li>
        <li>View complete transaction history with the Record History tab</li>
        <li>Add new keys to the system when needed</li>
    </ol>

    <h2>Screenshots</h2>
    <div class="screenshot">
        <h3>Login Screen</h3>
        <img src="/api/placeholder/800/450" alt="Login Screen">
    </div>
    <div class="screenshot">
        <h3>Main Interface</h3>
        <img src="/api/placeholder/800/450" alt="Main Interface">
    </div>

    <h2>Contributing</h2>
    <p>Contributions are welcome! Please feel free to submit a Pull Request.</p>
    <ol>
        <li>Fork the repository</li>
        <li>Create your feature branch (<code>git checkout -b feature/amazing-feature</code>)</li>
        <li>Commit your changes (<code>git commit -m 'Add some amazing feature'</code>)</li>
        <li>Push to the branch (<code>git push origin feature/amazing-feature</code>)</li>
        <li>Open a Pull Request</li>
    </ol>

    <h2>License</h2>
    <p>This project is licensed under the MIT License - see the LICENSE file for details.</p>

    <h2>Contact</h2>
    <p>Project Link: <a href="https://github.com/yourusername/key-management-system">https://github.com/yourusername/key-management-system</a></p>
</body>
</html>
