package sk.stuba.fei.uim.oop.assignment3.book.logic;

import sk.stuba.fei.uim.oop.assignment3.book.communication.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.communication.BookRequestEdit;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import java.util.List;

public interface IBookService {

    List<Book> getAllBooks();

    Book createBook(BookRequest request) throws NotFoundException;

    Book getById(Long bookId) throws NotFoundException;

    Book updateBook(Long bookId, BookRequestEdit request) throws NotFoundException;

    void deleteBook(Long bookId) throws NotFoundException;

    Integer getBookAmount(Long bookId) throws NotFoundException;

    Integer incrementBookAmount(Long id, Book book) throws NotFoundException;

    Integer getBookLendCount(Long bookId) throws NotFoundException;

    void addBookToAuthor(Long authorId, Long bookId) throws NotFoundException;

}
