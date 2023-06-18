package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.AuthorDaoImpl;
import pojos.Author;
import pojos.Book;

public class Add3Books {
	public static void main(String[] args) {
	try(SessionFactory sf = getFactory();
		Scanner sc = new Scanner(System.in)) {
		AuthorDaoImpl authorDao=new AuthorDaoImpl();
		System.out.println("enter firstName, lastName, email, password");
		Author auth=new Author(sc.next(),sc.next(),sc.next(),sc.next());
		for(int i=0;i<3;i++)
		{
			System.out.println("enter "+i+"th bookname and price");
			Book b=new Book(sc.next(),sc.nextDouble());
			auth.addBook(b);
		}
		System.out.println(authorDao.launchNewAuthor(auth));
	} 
	catch (Exception e) {
		e.printStackTrace();
	}
}
}
