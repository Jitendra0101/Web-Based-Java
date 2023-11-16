package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.DBUtils.*;

import pojos.User;

public class UserDaoImpl implements UserDao {
	// state (fields)
	private Connection connection;
	
	private PreparedStatement pst1, pst2, pst3, pst4, pst5, pst6;

	// def ctor
	public UserDaoImpl() throws SQLException {
		// get cn from db utils
		connection = openConnection();
		pst1 = connection
				.prepareStatement("select first_name,last_name,dob,status from users where email=? and password=?");
		pst2 = connection.prepareStatement("insert into users values(default,?,?,?,?,?,0,'voter')");
		pst3 = connection.prepareStatement("delete from users where id = ?");
		pst4 = connection.prepareStatement("Update users set status = 1 where id = ?");
		pst5 = connection.prepareStatement("select * from users");
		pst6 = connection.prepareStatement("select status from users where id = ?");
		System.out.println("user dao created!");
	}
	
	@Override
	public List<User> getUsers() throws SQLException {
		 
		ResultSet rst = pst5.executeQuery();
		
		List<User> list = new ArrayList<User>();
		
		while(rst.next()) {
			
			list.add(new User(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getDate(6),rst.getBoolean(7), rst.getString(8)));
			
		}
		
		return list;
	}

	@Override
	public User getDetails(String email, String password) throws SQLException {
		// set IN params
		pst1.setString(1, email);
		pst1.setString(2, password);
		// exec query : execQuery() --- RST
		User user = null;

		try (ResultSet rst = pst1.executeQuery()) {
			if (rst.next())
				user = new User(rst.getString(1), rst.getString(2), rst.getDate(3), rst.getBoolean(4));
		}
		return user;// DAO rets populated list of users to tester.
	}

	// clean up
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		closeConnection();
		System.out.println("user dao cleaned up !");
	}

	@Override
	public String addNewUser(User user) throws SQLException {

		pst2.setString(1, user.getFirstName());
		pst2.setString(2, user.getLastName());
		pst2.setString(3, user.getEmail());
		pst2.setString(4, user.getPassword());
		pst2.setDate(5, user.getDob());

		int res = pst2.executeUpdate();

		if (res == 0)
			return "Error, couldnt add new user !!";

		return "User added Successfully !!!";
	}

	@Override
	public String deleteUser(int id) throws SQLException {

		pst3.setInt(1, id);

		int res = pst3.executeUpdate();

		if (res == 0)
			return "User doesnt exist !!";

		return "User Deleted Successfully !!!";

	}

	@Override
	public String updateUserVotingStatus(int id) throws SQLException {

		pst4.setInt(1, id);

		int res = pst4.executeUpdate();

		if (res == 0)
			return "User doesnt exist !!";

		return "User voting status updated Successfully !!!";

	}

	@Override
	public String castVote(int id, Scanner sc) throws SQLException {
		pst6.setInt(1, id);

		ResultSet res = pst6.executeQuery();

		if (!res.next())
			return "User doesnt exist !!";
		
		if(res.getBoolean(1))
			return "User already voted !!";
			
		CandidateDaoImpl cDao = new CandidateDaoImpl();
		cDao.getAllCandidates().forEach(System.out::println);
		
		System.out.println("Enter the ID of candidate you want to vote for.");
		///
		int cid = sc.nextInt();
		
		System.out.println(cDao.updateVoteCount(cid));
		
		return updateUserVotingStatus(id);
		
	
		
	}


}
