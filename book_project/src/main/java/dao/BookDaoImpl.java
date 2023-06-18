package dao;
import static utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Author;
import pojos.Book;

public class BookDaoImpl implements BookDao {

	@Override
	public String AddBook(Book book,int id) {
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			Author auth=session.get(Author.class,id);
			auth.addBook(book);
			session.persist(book);
			tx.commit();
			return "book added";
		}catch(RuntimeException e){
			if(tx!=null)
				tx.rollback();
			throw e;
		}
		
	}

	@Override
	public String RemoveBook(int bk) {
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		Author a=new Author();
		try {
			Book b=session.get(Book.class, bk);
			a.removeBook(b);	
			session.remove(b);
			tx.commit();
			return "book added";
		}
		catch(RuntimeException e)
		{
			if(tx!=null)
				tx.rollback();
			throw e;
		}
	}

}
