package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Candidate;

public interface AdminDao {
	List<Candidate> getTopTwoCandidates() throws SQLException;
	
	List<Candidate> partyVoteList() throws SQLException; 
}
