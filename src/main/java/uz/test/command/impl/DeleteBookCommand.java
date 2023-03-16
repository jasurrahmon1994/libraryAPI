package uz.test.command.impl;

import uz.test.command.Command;
import uz.test.service.BookService;
import uz.test.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class DeleteBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        BookService bookService = BookServiceImpl.getInstance();
        return bookService.deleteBook(id) ? "Deleted" : null;
    }
}
