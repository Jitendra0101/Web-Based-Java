package dao;

import java.sql.*;
import static utils.DBUtils.*;

import pojos.User;

public class UserDaoImpl implements UserDao {
	//state (fields)
	private Connection connection;
	private PreparedStatement pst1;
	//def ctor
	public UserDaoImpl() throws SQLException{
		// get cn from db utils
		connection=openConnection();
		pst1=connection.prepareStatement("select first_name,last_name,dob,status from users where email=? and password=?");
		System.out.println("user dao created!");
	}

	@Override
	public User getDetails(String email, String password) throws SQLException {
		// set IN params
		pst1.setString(1,email);
		pst1.setString(2,password);
		//exec query : execQuery() --- RST
		User user = null;
		
		try(ResultSet rst=pst1.executeQuery())
		{
			if(rst.next())
				user =new User(rst.getString(1),rst.getString(2),rst.getDate(3),rst.getBoolean(4));
		}
			return user;//DAO rets populated list of users to tester.
	}
	//clean up 
	public void cleanUp() throws SQLException
	{
		if(pst1 != null)
			pst1.close();
		closeConnection();
		System.out.println("user dao cleaned up !");
	}

}
