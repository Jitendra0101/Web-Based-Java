package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import pojos.User;

public interface UserDao {
//add a method to get user details under specific role n dob range
	
	List<User> getUsers()throws SQLException;
	
	User getDetails(String email,String password)
			throws SQLException;
	
	String addNewUser(User user)throws SQLException;

	String deleteUser(int id)throws SQLException;
	
	String updateUserVotingStatus(int id) throws SQLException;
	
	String castVote(int id, Scanner sc) throws SQLException;

}

