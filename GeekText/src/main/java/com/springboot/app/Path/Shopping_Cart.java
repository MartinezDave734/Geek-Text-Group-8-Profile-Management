package com.springboot.app.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;


@RestController
public class Shopping_Cart extends BaseMethods{



    String getBookNameFromIsbn(String ISBN) {

        String Queried_BookName = null;

        try {
            String Query = "SELECT * FROM BOOK WHERE ISBN = '" + ISBN + "';";
            ResultSet result = SQLQuery(Query);
            while(result.next()) { //CHANGE FROM WHILE INSTEAD OF IF, TEST TO SEE WHAT HAPPENS
                Queried_BookName = result.getString("_name");
            }
            return Queried_BookName;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return Queried_BookName;
    }
    
    @GetMapping("/AddToShoppingCart")
    @ResponseBody
    void addToShoppingCart(@RequestParam String isbn, @RequestParam int quantity) {

    	String Book_Name = getBookNameFromIsbn(isbn);

        try  {
            
        	String Update = "INSERT INTO shopping_cart (ISBN, USER_ID, \"Quantity\", _name) VALUES ('" + isbn + "', " + getUSER_ID() + ", " + quantity + ", '" + Book_Name + "');";
            SQLUpdate(Update);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @DeleteMapping("/Cart/Remove")
    @ResponseBody
    void removeFromShoppingCart(@RequestParam String id, @RequestParam String isbn) { //Function Argument will be utilized when feature is developed.
        //if (isTableEmpty("Shopping_Cart")) {
            //System.out.println("The shopping cart is empty. Please add something to the shopping cart before attempting removal.");
        //}
    }
    
    @GetMapping("/Cart")
    @ResponseBody
    ArrayList<String> viewShoppingCart(@RequestParam String id) {
    	
    	ArrayList<String> cart = new ArrayList<String>();
    	
    	
    	/*
    	cart.add("The shopping cart for this user is empty. Try adding something to the cart!");
        if (isTableEmpty("Shopping_Cart")) {
          return cart;
        }
        cart.remove(0);
        */
    	
    	
    	try {
            
    		String Query = "SELECT * FROM SHOPPING_CART WHERE USER_ID = " + id + ";";
            ResultSet result = SQLQuery(Query);
            
            while (result.next()) { //Continues printing the contents of database rows until ResultSet reaches the end.
                
            	String Queried_ISBN = result.getString("isbn");
                int Queried_QUANTITY = result.getInt("Quantity");
                String Queried_Name = result.getString("_name");
                cart.add(" Name: " + Queried_Name + " || Quantity: " + Queried_QUANTITY + " || ISBN: " + Queried_ISBN + " ||| ");
            
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }














































/*
@RestController
public class Shopping_Cart extends ConnectAndMenu{



    boolean isEmpty() {
        try (Connection connection = DriverManager.getConnection(getURL(), getUsername(), getDBPassword())) {

            String SQL = "SELECT * FROM SHOPPING_CART WHERE USER_ID = " + this.getUSER_ID() + ";";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SQL);

            return !result.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    String getBookNameFromIsbn(String ISBN) {

        String Queried_BookName = null;

        try (Connection connection = DriverManager.getConnection(getURL(), getUsername(), getDBPassword())) {

            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM BOOK WHERE ISBN = '" + ISBN + "';";
            ResultSet result = statement.executeQuery(SQL);

            while (result.next()) {
                Queried_BookName = result.getString("_name");

            }

            return Queried_BookName;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return Queried_BookName;
    }
    
    @PostMapping("/AddToShoppingCart")
    @ResponseBody
    void AddToShoppingCart(@RequestParam String ISBN, @RequestParam int QUANTITY) {

    	String Book_Name = getBookNameFromIsbn(ISBN); //----------------------------------------------------------------Find a way to retrieve the book name from the Book table to simplify adding items.

        try (Connection connection = DriverManager.getConnection(getURL(), getUsername(), getDBPassword())) {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO shopping_cart (ISBN, USER_ID, \"Quantity\", _name) VALUES ('" + ISBN + "', " + getUSER_ID() + ", " + QUANTITY + ", '" + Book_Name + "');";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @DeleteMapping
    void RemoveFromShoppingCart(String USER_ID) { //Function Argument will be utilized when feature is developed.
        if (isEmpty()) {
            System.out.println("The shopping cart is empty. Please add something to the shopping cart before attempting removal.");
        }
    }
    
    @GetMapping("/shoppingCart")
    String ViewShoppingCart(@RequestParam String USER_ID) {
        //if (isEmpty()) {
          //  return "\nThe shopping cart is empty. Please add something to the shopping cart before attempting removal.";
        //}

        try (Connection connection = DriverManager.getConnection(url, username, DBPassword)) {

            Statement statement = connection.createStatement();
            String Query = "SELECT * FROM SHOPPING_CART WHERE USER_ID = " + USER_ID + ";";
            ResultSet result = statement.executeQuery(Query);

            System.out.println("User ID: " + USER_ID + "\n");

            while (result.next()) { //Continues printing the contents of database rows until ResultSet reaches the end.
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
*/

    //Menu Function

}