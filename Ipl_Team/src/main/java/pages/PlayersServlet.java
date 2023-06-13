package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.playerDaoImpl;
import pojo.Players;


@WebServlet("/specificTeamPlayers")
public class PlayersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	playerDaoImpl playerdao;
   
 
	public void init(ServletConfig config) throws ServletException {
		try {
			playerdao=new playerDaoImpl();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

	
	public void destroy() {
		try {
			playerdao.cleanUp();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter())
		{
			String[] teamId=request.getParameterValues("tm");
			List<Players> list=playerdao.getListOfTeamPlayers(teamId[0]);
			pw.print("<h2>Team Name : "+list.get(0).getPlayerName() +"</h2><h3></br> Players :</h3> "+"<ol>");
			for(Players p:list)
			{
				
				pw.print("<li>"+" "+p.getteamName()+"</li>");
			}
			pw.print("</ol>");
			pw.print("Press here to go <a href='index.html'> Back</a>");
		} catch (SQLException e) {
			try {
				playerdao.cleanUp();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}



}
