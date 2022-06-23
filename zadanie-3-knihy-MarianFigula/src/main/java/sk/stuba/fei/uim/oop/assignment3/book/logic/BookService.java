package sk.stuba.fei.uim.oop.assignment3.book.logic;

import sk.stuba.fei.uim.oop.assignment3.book.communication.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.communication.BookRequestEdit;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.IBookRepository;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IAuthorService authorService;

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book createBook(BookRequest request) throws NotFoundException {
        Book book = new Book();
        book.setName(request.getName());
        book.setDescription(request.getDescription());
        book.setAuthor(authorService.getById(request.getAuthor()));
        book.setPages(request.getPages());
        book.setAmount(request.getAmount());
        book.setLendCount(request.getLendCount());
        return this.bookRepository.save(book);
    }

    @Override
    public void addBookToAuthor(Long authorId, Long bookId) throws NotFoundException {
        Optional<Author> authorOptional = Optional.ofNullable(this.authorService.getById(authorId));
        Author author = new Author();
        if (authorOptional.isPresent()) {
            author = authorOptional.get();
        }
        Book book = this.getById(bookId);
        author.getBooks().add(book);
        book.setAuthor(author);
        this.bookRepository.save(book);
    }

    @Override
    public Book getById(Long bookId) throws NotFoundException {
        Book book = this.bookRepository.findBookById(bookId);
        if (book == null) {
            throw new NotFoundException();
        }
        return book;
    }

    @Override
    public void deleteBook(Long bookId) throws NotFoundException {
        Author author = this.getById(bookId).getAuthor();
        author.getBooks().removeIf(book -> book.getId().equals(bookId));
        this.bookRepository.delete(this.getById(bookId));
    }

    @Override
    public Book updateBook(Long bookId, BookRequestEdit request) throws NotFoundException {
        Book book = this.getById(bookId);

        if (request.getName() != null){
            book.setName(request.getName());
        }
        if (request.getDescription() != null){
            book.setDescription(request.getDescription());
        }
        if (request.getAuthor() != 0 && request.getAuthor() != null){
            book.setAuthor(this.authorService.getById(request.getAuthor()));
        }
        if (request.getPages() != null  && request.getPages() != 0){
            book.setPages(request.getPages());
        }
        return this.bookRepository.save(book);
    }

    @Override
    public Integer getBookAmount(Long bookId) throws NotFoundException {
        Optional<Book> bookOpt = this.bookRepository.findById(bookId);
        if (bookOpt.isPresent()){
            Book book = bookOpt.get();
            return book.getAmount();
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public Integer incrementBookAmount(Long bookId,Book book) throws NotFoundException {
        Optional<Book> bookOpt = this.bookRepository.findById(bookId);
        Book oldBook = new Book();
        if (bookOpt.isPresent()) {
            oldBook = bookOpt.get();
        }
        Integer oldAmount = oldBook.getAmount();
        if (oldAmount == null){
            throw new NotFoundException();
        }
        oldBook.setAmount(oldAmount + book.getAmount());
        this.bookRepository.save(oldBook);

        return oldBook.getAmount();
    }

    @Override
    public Integer getBookLendCount(Long bookId) throws NotFoundException {
        Optional<Book> bookOpt = this.bookRepository.findById(bookId);
        if (bookOpt.isPresent()){
            Book book = bookOpt.get();
            return book.getLendCount();
        }else {
            throw new NotFoundException();
        }
    }
}
