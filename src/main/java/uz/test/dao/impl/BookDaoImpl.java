package uz.test.dao.impl;

import uz.test.dao.BookDao;
import uz.test.entity.Book;
import uz.test.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl extends BookDao {
    private final String CREATE_BOOK_QUERY = "insert into books(name, author, year) values(?, ?, ?)";
    private final String DELETE_BOOK_QUERY = "delete from books where id = ?";
    private final String UPDATE_BOOK_QUERY = "update books set name = ?, author = ?, year = ? where id = ?";
    private final String SELECT_ALL_BOOKS_QUERY = "select * from books";
    private final String SELECT_BOOK_BY_ID_QUERY = "select * from books where id = ?";
    private final String SELECT_BOOK_BY_NAME_QUERY = "select * from books where name = ?";
    private final String SELECT_BOOK_BY_AUTHOR_QUERY = "select * from books where author = ?";
    private final String SELECT_BOOK_BY_YEAR_QUERY = "select * from books where year = ?";
    private final String SELECT_BOOK_BY_PAGEABLE_QUERY = "select * from books limit ? offset ?";
    private static final BookDao instance = new BookDaoImpl();

    private BookDaoImpl() {
    }

    public static BookDao getInstance() {
        return instance;
    }

    @Override
    public boolean create(Book book) {
        connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CREATE_BOOK_QUERY)) {
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.execute();
            ConnectionPool.getInstance().releaseConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BOOK_QUERY)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            ConnectionPool.getInstance().releaseConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Book book) {
        connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_BOOK_QUERY)) {
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.setInt(4, book.getId());
            ps.executeUpdate();
            ConnectionPool.getInstance().releaseConnection(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Book findById(int id) {
        Book book = new Book();
        connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BOOK_BY_ID_QUERY)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setYear(rs.getInt("year"));
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book findBookByName(String name) {
        Book book = new Book();
        connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BOOK_BY_NAME_QUERY)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setYear(rs.getInt("year"));
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        return getBooks(SELECT_ALL_BOOKS_QUERY);
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
        return getBooks(SELECT_BOOK_BY_AUTHOR_QUERY);
    }

    @Override
    public List<Book> findBookByYear(short year) {
        return getBooks(SELECT_BOOK_BY_YEAR_QUERY);
    }

    @Override
    public List<Book> getBooksByPage(int limit, int offset) {
        return null;
    }

    private List<Book> getBooks(String select_all_books_query) {
        List<Book> bookList = new ArrayList<>();
        connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(select_all_books_query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setYear(rs.getInt("year"));
                bookList.add(book);
            }
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
