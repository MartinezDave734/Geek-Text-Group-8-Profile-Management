package ProjectCEN;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Shopping_Cart {

        private final String url = "jdbc:postgresql://localhost:5432/ProjectCEN";
        private final String username = "postgres";
        private final String DBPassword = "AAAAA";
        private final String USER_ID;
        private final String Password;

        Shopping_Cart(String IdArg, String PasswordArg){
            this.USER_ID = IdArg;
            this.Password = PasswordArg;
        }
        String getUSER_ID(){
            return USER_ID;
        }
        String getPassword(){
            return Password;
        }

    boolean isEmpty(String USER_ID){
        try (Connection connection = DriverManager.getConnection(url, username, DBPassword)) {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM SHOPPING_CART WHERE USER_ID = " + this.getUSER_ID() + ";";
            ResultSet result = statement.executeQuery(SQL);
            return !result.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    String FindBookName(String ISBN){
        String Queried_BookName = null;
        try (Connection connection = DriverManager.getConnection(url, username, DBPassword)) {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM BOOK WHERE ISBN = '" + ISBN +"';";
            ResultSet result = statement.executeQuery(SQL);
            while(result.next()){
            Queried_BookName = result.getString("_name");
            }
            return Queried_BookName;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return Queried_BookName;
    }
    void AddToShoppingCart(String USER_ID) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter the ISBN of the book you wish to add: ");
        String ISBN = userInput.nextLine();

        System.out.println("Enter the quantity of the book you wish to add: \n");
        int QUANTITY = userInput.nextInt();

        String Book_Name = FindBookName(ISBN); //----------------------------------------------------------------Find a way to retrieve the book name from the Book table to simplify adding items.

        try (Connection connection = DriverManager.getConnection(url, username, DBPassword)) {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO shopping_cart (ISBN, USER_ID, \"Quantity\", _name) VALUES ('" + ISBN + "', " + USER_ID + ", " + QUANTITY + ", '" + Book_Name + "');";
            statement.executeUpdate(SQL);
            System.out.println("Successfully added " + Book_Name + " to the shopping cart!\n\n\tPress the Enter key to continue...");
            System.in.read(); //Waits for the user to press enter. ONLY WORKS ON ENTER KEY.
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void RemoveFromShoppingCart(String USER_ID){
        if(isEmpty(USER_ID)){
            System.out.println("The shopping cart is empty. Please add something to the shopping cart before attempting removal.");
        }
    }

    String ViewShoppingCart(String USER_ID){
        if(isEmpty(USER_ID)){
            return "\nThe shopping cart is empty. Please add something to the shopping cart before attempting removal.";
        }

        try (Connection connection = DriverManager.getConnection(url, username, DBPassword)) {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM SHOPPING_CART WHERE USER_ID = " + USER_ID + ";";
            System.out.println("\n\nSending Query to Database...\n\n");
            ResultSet result = statement.executeQuery(SQL);
            System.out.println("User ID:" + USER_ID + "\n");
            while(result.next()){ //Continues printing the contents of database rows until ResultSet reaches the end.
                String Queried_ISBN = result.getString("isbn");
                int Queried_QUANTITY = result.getInt("Quantity");
                String Queried_Name = result.getString("_name");
                System.out.println("\tName: " + Queried_Name + "\n\tQuantity: " + Queried_QUANTITY + "\n\tISBN: " + Queried_ISBN + "\n\n");
            }

            System.out.println("Press Enter key to continue...");
            System.in.read(); //Waits for the user to press enter. ONLY WORKS ON ENTER KEY.



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "\n";
    }


    //Menu Function

}