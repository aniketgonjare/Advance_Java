package pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name="author")
public class Author extends BaseEntity {
	@Column(length=20,name="first_name")
	private String firstName;
	@Column(length=20,name="last_name")
	private String lastName;
	@Column(length=35)
	private String email;
	@Column(length=29)
	private String password;
	@OneToMany(mappedBy = "auth",cascade = CascadeType.ALL)
	private List<Book> books=new ArrayList<>();
	
	
	public Author(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	
	
	public Author() {
		super();
	}



	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	public void addBook(Book b) {
		books.add(b);
		b.setAuth(this);
	}
	
	public void removeBook(Book b)
	{
		books.remove(b);
		b.setAuth(null);
	}
	
}
