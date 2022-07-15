package com.springboot.app.Path;

import java.sql.*;
import java.lang.String;
import org.springframework.web.bind.annotation.*;

@RestController
public class User extends BaseMethods {
    private String name;
    private String email;
    private String password;
    private String homeAddress;
    
    public User() {
    	
    }
    
    public User(String name, String email, String password, String homeAddress){
        this.name = name;
        this.email = email;
        this.password = password;
        this.homeAddress = homeAddress;
    }
    public String getName() {
    	return name;
    }
    public String getEmail() {
    	return email;
    }
    public String getPassword() {
    	return password;
    }
    public String getAddress() {
    	return homeAddress;
    }

    public void printUser(){
        this.printName();
        this.printEmail();
        this.printPassword();
        this.printHomeAdd();
    }

    public void printName(){
        System.out.println("Name : " + name);
    }

    public void printEmail(){
        System.out.println("Email : " + email);
    }

    public void printPassword(){
        System.out.println("Password : " + password);
    }

    public void printHomeAdd(){
        System.out.println("Home Address : " + homeAddress);
    }

    public void insertToProfile(){
            SQLUpdate("INSERT INTO profile (_name, email, _password, homeAdd) VALUES "+
                    "( \'" + getName() + "\', \'" + getEmail() + "\', \'" + getPassword() + "\', \'" + getAddress() + "\')");
    }

    @GetMapping("/User")
    @ResponseBody
    public String getUser(@RequestParam String username){
    	
    	String queryResult = null;
    	
        try {
            
        	ResultSet result = SQLQuery("SELECT * FROM profile WHERE _name = " + username + ";");
            
        	while(result.next()){
                //String Queried_Name = result.getString("_name");
                //String Queried_Email = result.getString("email");
                //String Queried_Password = result.getString("_password");
                //String Queried_HomeAdd = result.getString("homeAdd");
                queryResult = (" Name: "+ result.getString("_name") + " | Email: "+ result.getString("email") + " | Password: " + result.getString("_password") + " | Home Address: "+ result.getString("homeAdd") + " ||| ");
            }
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryResult;
    }
    
    /*
    private boolean newUser(){
        return (connection.countRow("SELECT email FROM users WHERE email = " + email + " ") == 0);
    }
	*/
}
