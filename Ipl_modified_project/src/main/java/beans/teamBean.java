package beans;

import java.sql.SQLException;
import java.util.List;

import dao.TeamDaoImpl;
import pojos.Team;

public class teamBean {
	private TeamDaoImpl teamDao;

	public teamBean() throws SQLException {
		teamDao = new TeamDaoImpl();

	}
 
	public TeamDaoImpl getTeamDao() {
		return teamDao;
	}

	public void setTeamDao(TeamDaoImpl teamdao) {
		this.teamDao = teamdao;
	}

	public List<Team> getTeamList() throws SQLException {
		return teamDao.getSelectedDetails();

	}

}
