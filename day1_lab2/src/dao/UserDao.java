package dao;

import java.sql.SQLException;


import pojos.User;

public interface UserDao {
//add a method to get user details under specific role n dob range
	User getDetails(String email,String password)
			throws SQLException;
}
