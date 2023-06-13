package pojo;

import java.time.LocalDate;

public class Players {
//| pid | PlayerName    | dob        | avg   | wicketsTaken | Tid
	private int pid;
	private String PlayerName;
	private LocalDate dob;
	private double avg;
	private int wicketsTaken;
	private int Tid;
	private String teamName;
	
	
	public Players(int pid, String playerName, LocalDate dob, double avg, int wicketsTaken, int tid) {
		super();
		this.pid = pid;
		PlayerName = playerName;
		this.dob = dob;
		this.avg = avg;
		this.wicketsTaken = wicketsTaken;
		Tid = tid;
	}
	
	public Players(String playerName,String teamName)
	{
		this.PlayerName=playerName;
		this.teamName=teamName;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPlayerName() {
		return PlayerName;
	}
	
	public String getteamName() {
		return teamName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public int getWicketsTaken() {
		return wicketsTaken;
	}

	public void setWicketsTaken(int wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}

	public int getTid() {
		return Tid;
	}

	public void setTid(int tid) {
		Tid = tid;
	}

	@Override
	public String toString() {
		return "Players [pid=" + pid + ", PlayerName=" + PlayerName + ", dob=" + dob + ", avg=" + avg
				+ ", wicketsTaken=" + wicketsTaken + ", Tid=" + Tid + "]";
	}
	
	 
	
}
