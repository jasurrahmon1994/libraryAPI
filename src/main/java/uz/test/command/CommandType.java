package uz.test.command;

import uz.test.command.impl.*;

public enum CommandType {
    ADD_BOOK(new AddBookCommand()),
    SELECT_ALL_BOOKS(new SelectAllBooksCommand()),
    DELETE_BOOK(new DeleteBookCommand()),
    UPDATE_BOOK(new UpdateBookCommand()),
    FIND_BOOK_BY_NAME(new FindByNameCommand());

    final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr){
        return commandStr == null ? SELECT_ALL_BOOKS.command : CommandType.valueOf(commandStr.toUpperCase()).command;
    }
}
