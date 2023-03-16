package uz.test.service.impl;

import uz.test.dao.BookDao;
import uz.test.dao.impl.BookDaoImpl;
import uz.test.entity.Book;
import uz.test.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao = BookDaoImpl.getInstance();
    private static final BookServiceImpl instance = new BookServiceImpl();

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        return instance;
    }
    @Override
    public boolean createBook(Book book) {
        return bookDao.create(book);
    }

    @Override
    public Book findBookById(int id) {
        return bookDao.findById(id);
    }

    @Override
    public Book findBookByName(String name) {
        return bookDao.findBookByName(name);
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
        return bookDao.findBookByAuthor(author);
    }

    @Override
    public List<Book> findBookByYear(short year) {
        return bookDao.findBookByYear(year);
    }

    @Override
    public List<Book> getBooksByPage(int limit, int offset) {
        return bookDao.getBooksByPage(limit, offset);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDao.update(book);
    }

    @Override
    public boolean deleteBook(int id) {
        return bookDao.delete(id);
    }
}
