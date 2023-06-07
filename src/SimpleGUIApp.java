import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class SimpleGUIApp extends JFrame {
    static Dotenv dotenv = Dotenv.configure().load();

    private JTextField nameTextField;
    private JButton saveButton;

    private JButton showButton;

    // MySQL's connection details
    private static final String DB_URL = dotenv.get("DB_URI");
    private static final String DB_USER = dotenv.get("DB_USERNAME");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

    public SimpleGUIApp() {
        // Set up the frame
        setTitle("Simple GUI App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        // Create components
        nameTextField = new JTextField(20);
        saveButton = new JButton("Save");
        showButton = new JButton("Show Users");


        // Add components to the content pane
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(new JLabel("Name:"));
        container.add(nameTextField);
        container.add(saveButton);
        container.add(showButton);

        // Add action listener to the save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                saveData(name);
                nameTextField.setText("");
            }
        }
        );
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUsers();
            }
        });

    }

    // Method to save data to the database
    private void saveData(String name) {
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a prepared statement
            String sql = "INSERT INTO users (name) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the parameter values
            statement.setString(1, name);

            // Execute the statement
            statement.executeUpdate();

            // Close the connections and statement
            statement.close();
            conn.close();

            // Display a success message
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data.");
        }
    }

    private void showUsers() {
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a statement for executing the SQL query
            Statement statement = conn.createStatement();

            // Execute the query to retrieve all users
            String sql = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(sql);

            // Process the results
            StringBuilder usersList = new StringBuilder();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                usersList.append("ID: ").append(id).append(", Name: ").append(name).append("\n");
            }

            // Close the connections and statement
            resultSet.close();
            statement.close();
            conn.close();

            // Display the list of users in a dialog box
            JOptionPane.showMessageDialog(this, usersList.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving users.");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimpleGUIApp app = new SimpleGUIApp();
                app.setVisible(true);
            }
        });
    }
}
