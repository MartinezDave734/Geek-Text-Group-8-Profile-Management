package ProjectCEN;
import java.util.Scanner;
public class Main {
    ConnectAndMenu java = new ConnectAndMenu();

    public static void main(String[] args) {

        System.out.println("Enter Your User ID: \n");
        Scanner ID_Input = new Scanner(System.in);
        String ID_ARG = ID_Input.nextLine();
        //Need to add a feature to add the user to the database if their ID is not in the database.
        ConnectAndMenu Connection = new ConnectAndMenu(ID_ARG);
        Connection.connect();
        ID_Input.close();

        System.exit(0);
    }
}