package dao;

import pojos.Book;

public interface BookDao {

	String AddBook(Book book, int id);
	String RemoveBook(int id);
	
}
