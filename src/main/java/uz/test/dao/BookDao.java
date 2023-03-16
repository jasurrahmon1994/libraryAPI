package uz.test.dao;

import uz.test.entity.Book;

import java.util.List;

public abstract class BookDao extends BaseDao<Book>{
    abstract public Book findBookByName(String name);
    abstract public List<Book> findBookByAuthor(String author);
    abstract public List<Book> findBookByYear(short year);
    abstract public List<Book> getBooksByPage(int limit, int offset);
}
