package kr.co.jhta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.co.jhta.vo.Book;

public class BookDAO {

	private DataSource ds;
	public void setDs(DataSource ds) {
		this.ds = ds;
	}
	
	public void insertBook(Book book) throws SQLException {
		String query = "insert into sample_books ("
				+ "book_no , bookt_title, book_write, book_genre, book_publisher, book_price,"
				+ "book_discountPrice, book_registeredDate, book_stock, book_point,"
				+ "book_likes ) values ("
				+ "sample_book_seq.nextval, ?,?,?,?,?,?,sysdate,?,?,? ) ";
		
		//DB 전처리
		Connection connection = ds.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(query);
		
		// 파라미터 값 바인딩
		pstmt.setString(1, book.getTitle());
		pstmt.setString(2, book.getWriter());
		pstmt.setString(3, book.getGenre());
		pstmt.setString(4, book.getPublisher());
		pstmt.setInt(5, book.getPrice());
		pstmt.setInt(6, book.getDiscountPrice());
		pstmt.setInt(7, book.getStock());
		pstmt.setDouble(8, book.getPoint());
		pstmt.setInt(9, book.getLikes());
		// 쿼리 실행
		pstmt.executeUpdate();
		
		//DB Access 후처리
		pstmt.close();
		connection.close();
		
		
	}
	public void deleteBook(int bookNo) throws SQLException {
		String query = "delete from sample_books where book_no = ? ";
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, bookNo);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	public void updateBook(Book book) throws SQLException {
		String query = "update sample_books set book_price = ? , book_discount_price = ? where book_no = ? ";
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, book.getPrice());
		pstmt.setInt(2, book.getDiscountPrice());
		pstmt.setInt(3, book.getNo());
		pstmt.executeUpdate();
	
		pstmt.close();
		conn.close();
		
		
	}
	public Book getBookByNo(int bookNo) throws SQLException {
		String query = "select * from sample_books where book_no = ? ";
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, bookNo);
		ResultSet rs = pstmt.executeQuery();
		
		Book book = null;
		if(rs.next()) {	book = rsFunc(rs); }
		rs.close();
		pstmt.close();
		conn.close();
		
		return book;
	}
	public List<Book> getAllBooks() throws SQLException {
		List<Book> books = new ArrayList<Book>();
		
		String query = "select * from sample_books order by book_no asc"; 
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) { Book book = rsFunc(rs); books.add(book);}
		rs.close();
		pstmt.close();
		conn.close();
		
		return books;
	}
	
	private Book rsFunc(ResultSet rs) throws SQLException {
		Book book = new Book();
		book = new Book();
		book.setNo(rs.getInt("book_no"));
		book.setTitle(rs.getString("book_title"));
		book.setWriter(rs.getString("book_write"));
		book.setGenre(rs.getString("book_genre"));
		book.setPublisher(rs.getString("book_publisher"));
		book.setPrice(rs.getInt("book_price"));
		book.setDiscountPrice(rs.getInt("book_discount_Price"));
	
		return book;
	}
}
