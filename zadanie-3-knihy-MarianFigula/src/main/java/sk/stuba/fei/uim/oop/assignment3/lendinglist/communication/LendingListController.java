package sk.stuba.fei.uim.oop.assignment3.lendinglist.communication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.communication.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.lendinglist.logic.ILendingListService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/list")
public class LendingListController {

    @Autowired
    private ILendingListService service;

    @GetMapping()
    public List<LendingListResponse> getAllLists(){
        return this.service.getAllLists().stream().map(LendingListResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public LendingListResponse createList(LendingListRequest request){
        return new LendingListResponse(this.service.createList(request));
    }

    @GetMapping("/{id}")
    public LendingListResponse getById(@PathVariable("id") Long listId) throws NotFoundException{
        return new LendingListResponse(this.service.getById(listId));
    }

    @DeleteMapping("/{id}")
    public void deleteLendingList(@PathVariable("id") Long listId) throws NotFoundException{
        this.service.deleteLendingList(listId);
    }

    @PostMapping("/{id}/add")
    public LendingListResponse addBookToList(@PathVariable("id") Long listId,@RequestBody BookIdRequest bookRequest) throws NotFoundException, IllegalOperationException {
        return new LendingListResponse(this.service.addBookToList(listId,bookRequest.getId()));
    }

    @DeleteMapping("/{id}/remove")
    public LendingListResponse removeBookFromLendingList(@PathVariable("id") Long listId, @RequestBody BookIdRequest request) throws NotFoundException {
        return new LendingListResponse(this.service.removeBookFromLendingList(listId, request.getId()));
    }

    @GetMapping("/{id}/lend")
    public void lendLendingList(@PathVariable("id") Long listId) throws NotFoundException, IllegalOperationException{
        this.service.lendLendingList(listId);
    }
}
