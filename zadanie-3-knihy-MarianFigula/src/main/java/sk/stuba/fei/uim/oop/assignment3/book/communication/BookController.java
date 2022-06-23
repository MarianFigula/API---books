package sk.stuba.fei.uim.oop.assignment3.book.communication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.data.Amount;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService service;

    @GetMapping()
    public List<BookResponse> getAllBooks(){
        return this.service.getAllBooks().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@RequestBody BookRequest bookRequest) throws NotFoundException {
        BookResponse bookResponse = new BookResponse(this.service.createBook(bookRequest));
        this.service.addBookToAuthor(bookResponse.getAuthor(), bookResponse.getId());
        return bookResponse;
    }

    @GetMapping(value="/{id}")
    public BookResponse getById(@PathVariable("id") Long bookId) throws NotFoundException{
        return new BookResponse(this.service.getById(bookId));
    }

    @PutMapping(value="/{id}")
    public BookResponse updateBook(@PathVariable("id") Long bookId, @RequestBody BookRequestEdit request) throws NotFoundException {
        return new BookResponse(this.service.updateBook(bookId, request));
    }

    @DeleteMapping(value="/{id}")
    public void deleteBook(@PathVariable("id") Long bookId) throws NotFoundException{
        this.service.deleteBook(bookId);
    }

    @PostMapping("/{id}/amount")
    public Amount incrementBookAmount(@PathVariable("id") Long bookId, @RequestBody Book book) throws NotFoundException {
        Amount amount = new Amount();
        Integer productAmount = this.service.incrementBookAmount(bookId, book);
        amount.setAmount(productAmount);
        return amount;
    }

    @GetMapping(value="/{id}/amount")
    public Amount getBookAmount(@PathVariable("id") Long bookId) throws NotFoundException {
        Amount amount = new Amount();
        amount.setAmount(this.service.getBookAmount(bookId));
        return amount;
    }

    @GetMapping(value="/{id}/lendCount")
    public Amount getLendCount(@PathVariable("id") Long bookId) throws NotFoundException{
        Amount amount = new Amount();
        amount.setAmount(this.service.getBookLendCount(bookId));
        return amount;
    }
}
