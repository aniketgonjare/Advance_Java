package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.BookDaoImpl;
import pojos.Book;

public class AddBook {
	public static void main(String[] args) {
	try(SessionFactory sf = getFactory();
		Scanner sc = new Scanner(System.in)) {
		BookDaoImpl bookDao=new BookDaoImpl();
		System.out.println("enter bookname, price");
		
		Book book=new Book(sc.next(),sc.nextDouble());
		System.out.println("enter author id");
		System.out.println(bookDao.AddBook(book,sc.nextInt()));
	} 
	catch (Exception e) {
		e.printStackTrace();
	}
}
}
