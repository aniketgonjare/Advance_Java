package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.playerDaoImpl;
import exception.InvalidePlayerDataException;

/**
 * Servlet implementation class RemovePlayer
 */
@WebServlet("/remove")
public class RemovePlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	playerDaoImpl playerdao;

	public void init(ServletConfig config) throws ServletException {
		try {
			playerdao = new playerDaoImpl();
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			int id = Integer.parseInt(request.getParameter("pid"));
			boolean result=playerdao.removePlayerById(id);
			if(result) {
				pw.print("player with id "+ id+" removed from team");				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (InvalidePlayerDataException e) {
			
			e.printStackTrace();
		}
	}
}
