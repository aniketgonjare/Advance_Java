package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.playerDaoImpl;
import exception.InvalidePlayerDataException;

/**
 * Servlet implementation class TeamServlet
 */
@WebServlet("/team")
public class TeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	playerDaoImpl playerdao;
	@Override
	public void init() throws ServletException {
		try {
			playerdao=new playerDaoImpl();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter())
		{
			String playerId=request.getParameter("pid");
//			String teamName=request.getParameter("tm");
			String[] teamId=request.getParameterValues("tm");
			String playerName=request.getParameter("nm");
			String dob=request.getParameter("dob");
			String battingAvg=request.getParameter("avg");
			String wicketsTaken=request.getParameter("wck");
			
			boolean result=playerdao.addPlayer(playerId,playerName,teamId[0],dob,battingAvg,wicketsTaken);
			if(result) {
				pw.print("<h2>Player added to the team!!!</br>   </h2> <a href='players.html'> click here <a/>To see players in the team ");
				pw.print("</br></br> Or Press here to go <a href='index.html'> Back</a>");
			}
				else
				pw.print("player not added to the team");
				
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (InvalidePlayerDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void destroy() {
		try {
			playerdao.cleanUp();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
