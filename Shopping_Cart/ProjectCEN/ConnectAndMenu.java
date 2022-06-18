package ProjectCEN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectAndMenu {
    final String url = "jdbc:postgresql://localhost:5432/ProjectCEN";
    final String username = "postgres";
    final String DBPassword = "AAAAA";
    private String USER_ID;
    private String Password;

    ConnectAndMenu(){
        this.USER_ID = null;
        this.Password = null;
    }
    ConnectAndMenu(String IdArg){
        this.USER_ID = IdArg;
        this.Password = null;
    }
    ConnectAndMenu(String IdArg, String PassArg){ //Meant to be used when the profile integration is complete.
        this.USER_ID = IdArg;
        this.Password = PassArg;
    }
    public String getUSER_ID(){
        return USER_ID;
    }
    public String getPassword(){
        return Password;
    }
    public void setUSER_ID(String ID_Input){
        this.USER_ID = ID_Input;
    }
    public void setPassword(String NewPassword){
        this.Password = NewPassword;
    }

    void Menu(Shopping_Cart shop) throws SQLException {
        System.out.println("\nWhat would you like to do? \n\t1. Add a book to the shopping cart. \n\t2. Remove a book from the shopping cart.\n\t3. View the shopping cart.\n\t4. Exit.");
        Scanner MenuSelect = new Scanner(System.in);
        switch (MenuSelect.nextInt()) {
            case 1: {
                shop.AddToShoppingCart(this.getUSER_ID());
                Menu(shop);
            }
            case 2: {
                shop.RemoveFromShoppingCart(this.getUSER_ID());
                Menu(shop);
            }
            case 3: {
                System.out.println(shop.ViewShoppingCart(this.getUSER_ID()));
                Menu(shop);
            }
            case 4: {
                System.out.println("Exiting...");
                System.exit(0);
            }
            default: {
                System.out.println("Error: Please select one of the main options.");
                Menu(shop);
            }
        }
        MenuSelect.close();
    }

    protected void connect() {
        try (Connection connection = DriverManager.getConnection(url, username, DBPassword)) {
            if (connection != null) {
                System.out.println("Connection successful!\n");
            } else {
                System.out.println("Failed to connect to the server. Exiting...\n");
                return;
            }
            Menu(new Shopping_Cart(this.getUSER_ID(), this.getPassword()));  //NEED TO CHECK THE ARGUMENT LATER TO MAKE SURE IT DOESN'T CAUSE ISSUES WITH THE STACK
        } catch (SQLException e) {
            System.out.println("Connection Failed! Exiting...\n");
            e.printStackTrace();
        }
    }

}
