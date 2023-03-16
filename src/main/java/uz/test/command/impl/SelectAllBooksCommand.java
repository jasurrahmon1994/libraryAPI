package uz.test.command.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.test.command.Command;
import uz.test.entity.Book;
import uz.test.service.BookService;
import uz.test.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SelectAllBooksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        BookService bookService = BookServiceImpl.getInstance();
        List<Book> allBooks = bookService.getAllBooks();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(allBooks);
    }
}
