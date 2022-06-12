import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Shopping_Cart {

    private final String url = "jdbc:postgresql://localhost:5432/ProjectCEN";
    private final String username = "postgres";
    private final String password = "AAAAA";


    void AddToShoppingCart(String ISBN, String USER_ID, int QUANTITY) throws SQLException {

        try (Connection connection = DriverManager.getConnection(url, username, password);) {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO shopping_cart (ISBN, USER_ID, \"Quantity\") VALUES ('" + ISBN + "', " + USER_ID + ", " + QUANTITY + ");";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }


    private void connect() {
        String ISBN, USER_ID;
        int QUANTITY;
        Scanner userInput = new Scanner(System.in);


        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            if (connection != null) {
                System.out.println("Successfully connected to PostgresSQL server! Enter a User ID to store your shopping cart:\n");
                USER_ID = userInput.nextLine();
            } else {
                System.out.println("Failed to connect to the server. Exiting...\n");
                return;
            }


            Statement statement = connection.createStatement();
            System.out.println("What would you like to do? \n\t1. Add a book to the shopping cart. \n\t2. Remove a book from the shopping cart\n\t3. View the shopping cart");

            switch (userInput.nextInt()) {
                case 1:
                    System.out.println("Enter the ISBN of the book you wish to add: ");
                    userInput.nextLine();
                    ISBN = userInput.nextLine();
                    System.out.println("Enter the quantity of the book you wish to add: \n");
                    QUANTITY = userInput.nextInt();
                    AddToShoppingCart(ISBN, USER_ID, QUANTITY);
                    break;
                case 2:

                    break;

                case 3:
            }


            //Example of SQL Query to send to the database: "INSERT INTO _NAME (firstName, lastName)" + " VALUES ('Jordan', 'Osnar')";


            //================================================================


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Shopping_Cart sqlConnect = new Shopping_Cart();
        sqlConnect.connect();

    }
}