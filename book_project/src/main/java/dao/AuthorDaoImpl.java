package dao;
import static utils.HibernateUtils.getFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Author;

public class AuthorDaoImpl implements AuthorDao {

	@Override
	public String addNewAuthor(Author auth) {
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			session.save(auth);
			tx.commit();
			return "author added";
		}catch(RuntimeException e)
		{
			if(tx!=null)
				tx.rollback();
			throw e;
		}
	}

	@Override
	public String launchNewAuthor(Author auth) {
		Session session=getFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		try {
			session.persist(auth);
			tx.commit();
		}catch(RuntimeException e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		}
		return null;
	}

}
