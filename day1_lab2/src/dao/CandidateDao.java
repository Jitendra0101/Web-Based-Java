package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Candidate;

public interface CandidateDao {
	
	List<Candidate> getAllCandidates()
			throws SQLException;
	
	String updateVoteCount(int id) throws SQLException;

}
