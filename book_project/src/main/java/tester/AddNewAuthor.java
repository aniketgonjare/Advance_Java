package tester;

import static utils.HibernateUtils.getFactory;
import java.util.Scanner;
import org.hibernate.SessionFactory;
import dao.AuthorDaoImpl;
import pojos.Author;

public class AddNewAuthor {
	public static void main(String[] args) {
	try(SessionFactory sf = getFactory();
		Scanner sc = new Scanner(System.in)) {
		AuthorDaoImpl authorDao=new AuthorDaoImpl();
		System.out.println("enter firstName, lastName, email, password");
		Author auth=new Author(sc.next(),sc.next(),sc.next(),sc.next());
		System.out.println(authorDao.addNewAuthor(auth));	
	} 
	catch (Exception e) {
		e.printStackTrace();
	}
}
}
