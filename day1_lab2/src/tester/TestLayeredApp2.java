package tester;

import java.util.Scanner;

import dao.UserDaoImpl;
import pojos.User;

public class TestLayeredApp2 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();
			System.out.println("\nEnter email and password: ");
			//tester  --> Dao's method
			User user= dao.getDetails(sc.next(),sc.next());
			System.out.println("\n<--------------------------------------------------User details-------------------------------------------------->\n");
			if(user!=null)
				System.out.println(user);
			else
				System.out.println("User with given credentials does NOT exist !!!");
			//clean up
			System.out.println("\n<---------------------------------------------------------------------------------------------------------------->\n");
			dao.cleanUp();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
