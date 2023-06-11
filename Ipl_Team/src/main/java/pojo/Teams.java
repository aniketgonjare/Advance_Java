package pojo;

public class Teams {
	
	private int id;
	private String TeamName;
	
	public Teams(int id, String teamName) {
		super();
		this.id = id;
		TeamName = teamName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeamName() {
		return TeamName;
	}
	public void setTeamName(String teamName) {
		TeamName = teamName;
	}
	
	@Override
	public String toString() {
		return "Teams [id=" + id + ", TeamName=" + TeamName + "]";
	}
	
	

}
