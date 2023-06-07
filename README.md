# Simple GUI App in Java

A simple Java GUI application that allows you to save and retrieve user data from a MySQL database. The application uses a Swing-based graphical user interface.

## Requirements

To run this application, you need to have the following software installed:

- Java Development Kit (JDK)
- MySQL database server

## Installation and Setup

1. Clone the repository:

   ```shell
   git clone https://github.com/ShafayetAhmad/your-repository.git
   ```

2. Set up the MySQL database:

   - Create a new database in your MySQL server.
   - Import the `users.sql` file located in the repository's src directory into the created database. This file contains the necessary table structure.

3. Configure the database connection:

   - Open the `.env` file in the repository's root directory.
   - Modify the following environment variables in the `.env` file to match your MySQL database configuration:

     ```shell
     DB_URI=jdbc:mysql://localhost:3306/your-database-name
     DB_USERNAME=your-username
     DB_PASSWORD=your-password
     ```

4. Build and run the application:

   ```shell
   javac SimpleGUIApp.java
   java SimpleGUIApp
   ```

## Usage

1. Launch the application by running the `SimpleGUIApp` class.

2. Enter a name in the text field and click the "Save" button to save the name to the database.

3. Click the "Show Users" button to display a list of all the saved users in a dialog box.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgements

This application uses the [dotenv-java](https://github.com/cdimascio/java-dotenv) library by [cdimascio](https://github.com/cdimascio).

Feel free to customize the README according to your specific project requirements and add any additional sections or information as needed.