package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Candidate;

import static utils.DBUtils.*;

public class CandidateDaoImpl implements CandidateDao {

	private Connection connection;
	private PreparedStatement pst1;

	public CandidateDaoImpl() throws SQLException {

		connection = openConnection();
		pst1 = connection.prepareStatement("select * from candidates");
		System.out.println("candidate dao created!");
	}

	@Override
	public List<Candidate> getAllCandidates() throws SQLException {

		// set IN params
		//		pst1.setString(1,role);
		//		pst1.setDate(2, begin);
		//		pst1.setDate(3, end);
// exec query : execQuery() --- RST
		List<Candidate> candidates = new ArrayList<>();
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4)));
		}
		return candidates;// DAO rets populated list of users to tester.
	}
	
	//clean up 
		public void cleanUp() throws SQLException
		{
			if(pst1 != null)
				pst1.close();
			closeConnection();
			System.out.println("Candidate dao cleaned up !");
		}

}
