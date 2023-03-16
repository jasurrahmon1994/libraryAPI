package uz.test.command.impl;

import uz.test.command.Command;
import uz.test.entity.Book;
import uz.test.service.BookService;
import uz.test.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UpdateBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        BookService bookService = BookServiceImpl.getInstance();
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        int year = Integer.parseInt(request.getParameter("year"));
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = new Book(author, year);
        book.setName(name);
        book.setAuthor(author);
        book.setYear(year);
        book.setId(id);
        return bookService.updateBook(book) ? "Updated" : null;
    }
}
