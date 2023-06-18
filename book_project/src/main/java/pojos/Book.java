package pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book extends BaseEntity{
	@Column(name="book_name",length=20)
	private String bookName;
	@Column(name="price",length=20)
	private double price;
	@JoinColumn(name="author_id")
	@ManyToOne
	private Author auth;
	
	public Book() {
		super();
	}

	public Book(String bookName, double price) {
		super();
		this.bookName = bookName;
		this.price = price;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

	public Author getAuth() {
		return auth;
	}

	public void setAuth(Author auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", price=" + price + "]";
	}
	
	
	
	
}
