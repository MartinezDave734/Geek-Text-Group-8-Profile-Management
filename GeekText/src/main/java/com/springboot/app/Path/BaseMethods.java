package com.springboot.app.Path;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseMethods {
    final String url = "";
    final String username = "";
    final String DBPassword = "";
    private String USER_ID;
    
    /*
    
    ConnectAndMenu() {
        this.USER_ID = null;
    }
    
    ConnectAndMenu(String IdArg) {
        this.USER_ID = IdArg;
    }

    ConnectAndMenu(String IdArg, String PassArg) { //Meant to be used when the profile integration is complete.
        this.USER_ID = IdArg;
    }
    */
    
    public String getUSER_ID() {
        return USER_ID;
    }
    public String getURL() {
    	return url;
    }
    public String getUsername() {
    	return username;
    }
    public String getDBPassword() {
    	return DBPassword;
    }
        
    public ResultSet SQLQuery(String query) {

    	ResultSet result = null;
        try (Connection connection = DriverManager.getConnection(getURL(), getUsername(), getDBPassword())) {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void SQLUpdate(String update) {
    	
    	int result;
        try (Connection connection = DriverManager.getConnection(getURL(), getUsername(), getDBPassword())) {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(update);
            if(result > 0) {
            	System.out.println("A new row has been added.\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    /*boolean isTableEmpty(String Table) {  //Needs editing, calling this function invokes ResultSet::Next, which causes any ResultSet using this function to iterate (This is an error).
        try (Connection connection = DriverManager.getConnection(getURL(), getUsername(), getDBPassword())) {
        	
            String Query= "SELECT * FROM " + Table + "WHERE USER_ID = " + this.getUSER_ID() + ";";
            ResultSet queryResult = SQLQuery(Query);
            
            if(queryResult.next()) {
            	return true;
            }
            else return false;
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }*/
    


    
}


