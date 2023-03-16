package uz.test.command.impl;

import uz.test.command.Command;
import uz.test.entity.Book;
import uz.test.service.BookService;
import uz.test.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class AddBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        BookService bookService = BookServiceImpl.getInstance();
            String name = req.getParameter("name");
            String author = req.getParameter("author");
            int year = Integer.parseInt(req.getParameter("year"));
            Book book = new Book(author, year);
            book.setName(name);
            return bookService.createBook(book) ? "Added" : "Failed";
    }
}
