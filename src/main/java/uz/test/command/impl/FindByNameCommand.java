package uz.test.command.impl;

import com.google.gson.GsonBuilder;
import uz.test.command.Command;
import uz.test.entity.Book;
import uz.test.service.BookService;
import uz.test.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class FindByNameCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        BookService bookService = BookServiceImpl.getInstance();
        String name = request.getParameter("name");
        Book book = bookService.findBookByName(name);
        return new GsonBuilder().setPrettyPrinting().create().toJson(book);
    }
}
