package dao;

import static dbUtils.Utils.closeConnection;
import static dbUtils.Utils.openConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import exception.InvalidePlayerDataException;
import pojo.Players;

public class playerDaoImpl implements playerDao {
	private Connection cn;
	private PreparedStatement pst1, pst2, pst3;

	public playerDaoImpl() throws SQLException {
		cn = openConnection();
		// pid | PlayerName | dob | avg | wicketsTaken | Tid
		pst1 = cn.prepareStatement("insert into players values (?,?,?,?,?,?)");
		pst2 = cn.prepareStatement("select TeamName,playerName from team,players where id=Tid and id=?");
		pst3 = cn.prepareStatement("delete from players where pid=?");
	}

	@Override
	public boolean addPlayer(String playerId, String playerName, String teamId, String dob, String battingAvg,
			String wicketsTaken) throws SQLException, InvalidePlayerDataException {
		Date age = Date.valueOf(dob);
		validateAge(dob);
		double avg = validatebattingAvg(battingAvg);
		int tid = Integer.parseInt(teamId);
		int wktTaken = validateWicketsTaken(wicketsTaken);
		// pid | PlayerName | dob | avg | wicketsTaken | Tid
		pst1.setInt(1, Integer.parseInt(playerId));
		pst1.setString(2, playerName);

		pst1.setDate(3, age);
		pst1.setDouble(4, avg);
		pst1.setInt(5, Integer.parseInt(wicketsTaken));
		pst1.setInt(6, tid);
		int num = pst1.executeUpdate();
		if (num == 1)
			return true;
		else
			return false;
	}

	private void validateAge(String dob) throws InvalidePlayerDataException {
		LocalDate ld = LocalDate.parse(dob);
		LocalDate today = LocalDate.now();
		int age = Period.between(ld, today).getYears();
		if (age > 40)
			throw new InvalidePlayerDataException("players age must be within 40");

	}

	private double validatebattingAvg(String battingAvg) throws InvalidePlayerDataException {
		double avg = Double.parseDouble(battingAvg);
		if (avg > 25)
			return avg;
		else
			throw new InvalidePlayerDataException("players batting avg must be greater then 25");

	}

	private int validateWicketsTaken(String wicketsTaken) throws InvalidePlayerDataException {
		int wcktsTaken = Integer.parseInt(wicketsTaken);
		if (wcktsTaken > 100)
			return wcktsTaken;
		else
			throw new InvalidePlayerDataException("you cannot add the player who has not taken minimum 100 wickets");

	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		closeConnection();
	}

	public List<Players> getListOfTeamPlayers(String teamId) throws SQLException {
		List<Players> playerList = new ArrayList<>();
		int tid = Integer.parseInt(teamId);
		pst2.setInt(1, tid);
		try (ResultSet rst = pst2.executeQuery()) {
			while (rst.next()) {
				playerList.add(new Players(rst.getString(1), rst.getString(2)));
			}
		}
		return playerList;
	}

	@Override
	public boolean removePlayerById(int id) throws SQLException, InvalidePlayerDataException {
		pst3.setInt(1, id);
		int rst2=pst3.executeUpdate();
		if(rst2==1)
			return true;
		else
			throw new InvalidePlayerDataException("Player Id you entered is Invalid");
		
	}
	
	

}
