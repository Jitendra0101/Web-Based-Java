package tester;

import java.sql.Date;
import java.util.Scanner;

import dao.CandidateDaoImpl;
import dao.UserDaoImpl;
import pojos.User;

public class TestLayeredApp2 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();
			CandidateDaoImpl cDao = new CandidateDaoImpl();

			boolean flag = false;
			System.out.println("\n\t MENU \n" + "=======================\n" + "1. Show all Users \n"
					+ "2. Show all Candidates \n" + "3. User Authentication \n" + "4. Add new user\n"
					+ "5. Delete User by ID\n" + "6. Update voting status of user by ID\n" + "7. Cast vote\n"
					+ "0. Exit.\n" + "=======================\n");
			try {
				while (!flag) {
					System.out.println("\nEnter your choice:");
					switch (sc.nextInt()) {

					case 1:

						dao.getUsers().forEach(i -> System.out.println(i));

						break;

					case 2:
						System.out.println("printing all candidates");
						cDao.getAllCandidates().forEach(System.out::println);
						break;
					case 3:

						System.out.println("\nEnter email and password: ");
						User user = dao.getDetails(sc.next(), sc.next());
						System.out.println(
								"\n<--------------------------------------------------User details-------------------------------------------------->\n");
						if (user != null)
							System.out.println(user);
						else
							System.out.println("User with given credentials does NOT exist !!!");
						// clean up
						System.out.println(
								"\n<---------------------------------------------------------------------------------------------------------------->\n");

						break;

					case 4:

						System.out.println("\n Enter new user details:(first_name,last_name,email,password,DOB)");

						System.out.println(dao.addNewUser(
								new User(sc.next(), sc.next(), sc.next(), sc.next(), Date.valueOf(sc.next()))));

						break;

					case 5:

						System.out.println("\n Enter the id of the user to be deleted:");
						System.out.println(dao.deleteUser(sc.nextInt()));

						break;

					case 6:

						System.out.println("\n Enter the id of the user to update voting status:");
						System.out.println(dao.updateUserVotingStatus(sc.nextInt()));
						break;

					case 7:
						System.out.println("\nEnter user id");
						System.out.println(dao.castVote(sc.nextInt(),sc));
						break;
						
					case 0:

						System.out.println("Exiting ...\n");
						flag = true;
						break;

					default:
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				sc.nextLine();
			}

			dao.cleanUp();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
