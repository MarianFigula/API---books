package sk.stuba.fei.uim.oop.assignment3.lendinglist.logic;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lendinglist.communication.LendingListRequest;
import sk.stuba.fei.uim.oop.assignment3.lendinglist.data.ILendingListRepository;
import sk.stuba.fei.uim.oop.assignment3.lendinglist.data.LendingList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LendingListService implements ILendingListService {

    @Autowired
    private ILendingListRepository listRepository;

    @Autowired
    private IBookService bookService;

    @Override
    public List<LendingList> getAllLists() {
        return this.listRepository.findAll();
    }

    @Override
    public LendingList createList(LendingListRequest request) {
        LendingList list = new LendingList();
        list.setBooks(new ArrayList<>());
        list.setLended(false);
        return this.listRepository.save(list);
    }

    @Override
    public LendingList getById(Long listId) throws NotFoundException {
        LendingList list = this.listRepository.findLendingListById(listId);
        if (list == null){
            throw new NotFoundException();
        }
        return list;
    }

    @Override
    public void deleteLendingList(Long listId) throws NotFoundException {
        LendingList list = this.getById(listId);

        if (list.isLended()) {
            decrementBooksLendCount(list);
        }
        this.listRepository.delete(this.getById(listId));
    }

    @Override
    public LendingList addBookToList(Long listId, Long bookId) throws NotFoundException, IllegalOperationException {
        Optional<LendingList> listOpt = Optional.ofNullable(this.getById(listId));
        LendingList list = new LendingList();
        Book book = this.bookService.getById(bookId);

        if (listOpt.isPresent()){
            list=listOpt.get();
        }
        if (list.isLended() || list.getBooks().contains(book)){
            throw new IllegalOperationException();
        }
        list.getBooks().add(book);

        return this.listRepository.save(list);
    }

    @Override
    public LendingList removeBookFromLendingList(Long listId, Long bookId) throws NotFoundException {
        Optional<LendingList> listOpt = Optional.ofNullable(this.getById(listId));
        LendingList list = new LendingList();
        Book book = this.bookService.getById(bookId);

        if (listOpt.isPresent()){
            list=listOpt.get();
        }

        list.getBooks().remove(book);
        return this.listRepository.save(list);
    }

    @Override
    public void lendLendingList(Long listId) throws NotFoundException, IllegalOperationException {
        Optional<LendingList> listOpt = Optional.ofNullable(this.getById(listId));
        LendingList list = new LendingList();

        if (listOpt.isPresent()){
            list=listOpt.get();
        }
        if (list.isLended()){
            throw new IllegalOperationException();
        }
        list.setLended(true);
        incrementBooksLendCount(list);
    }

    private void incrementBooksLendCount(LendingList list){
        list.getBooks().forEach(book -> {
            try {
                if (book.getAmount().equals(book.getLendCount())){
                    throw new IllegalOperationException();
                }
                book.setLendCount(book.getLendCount() + 1);
            }catch (IllegalOperationException ignored) {}
        });
    }

    private void decrementBooksLendCount(LendingList list){
        list.getBooks().forEach(book -> book.setLendCount(book.getLendCount() - 1));
    }
}
