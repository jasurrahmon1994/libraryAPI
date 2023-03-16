package uz.test.service;

import uz.test.entity.Book;

import java.util.List;

public interface BookService {
    boolean createBook(Book book);
    Book findBookById(int id);
    Book findBookByName(String name);
    List<Book> findBookByAuthor(String author);
    List<Book> findBookByYear(short year);
    List<Book> getBooksByPage(int limit, int offset);
    List<Book> getAllBooks();
    boolean updateBook(Book book);
    boolean deleteBook(int id);
}
