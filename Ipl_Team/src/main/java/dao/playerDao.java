package dao;

import java.sql.SQLException;
import java.util.List;

import exception.InvalidePlayerDataException;
import pojo.Players;

public interface playerDao {
	
	 public boolean addPlayer(String playerId,String playerName,String teamName,String dob,String battingAvg,String wicketsTaken) throws SQLException, InvalidePlayerDataException;
	 public List<Players> getListOfTeamPlayers(String teamId) throws SQLException;
	 public boolean removePlayerById(int id) throws SQLException, InvalidePlayerDataException;
}
