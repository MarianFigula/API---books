package sk.stuba.fei.uim.oop.assignment3.lendinglist.logic;

import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lendinglist.communication.LendingListRequest;
import sk.stuba.fei.uim.oop.assignment3.lendinglist.data.LendingList;
import java.util.List;

public interface ILendingListService {

    List<LendingList> getAllLists();

    LendingList createList(LendingListRequest request);

    LendingList getById(Long listId) throws NotFoundException;

    void deleteLendingList(Long listId) throws NotFoundException;

    LendingList addBookToList(Long listId, Long bookId) throws NotFoundException, IllegalOperationException;

    LendingList removeBookFromLendingList(Long listId, Long bookId) throws NotFoundException;

    void lendLendingList(Long listId) throws NotFoundException, IllegalOperationException;
}
