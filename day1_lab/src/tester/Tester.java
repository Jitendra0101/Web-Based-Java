package tester;

import java.util.List;
import java.util.Scanner;

import dao.CandidateDaoImpl;
import pojos.Candidate;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try (Scanner sc = new Scanner(System.in)) {
			// create dao instance
			CandidateDaoImpl dao = new CandidateDaoImpl();
			// tester --> Dao's method
			List<Candidate> candidates = dao.getAllCandidates();	
			System.out.println("Selected Candidates ");
			candidates.forEach(System.out::println);
			// clean up
			dao.cleanUp();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
