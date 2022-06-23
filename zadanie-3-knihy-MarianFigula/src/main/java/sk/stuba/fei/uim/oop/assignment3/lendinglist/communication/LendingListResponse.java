package sk.stuba.fei.uim.oop.assignment3.lendinglist.communication;

import sk.stuba.fei.uim.oop.assignment3.book.communication.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.lendinglist.data.LendingList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class LendingListResponse {
    private final Long id;
    private final List<BookResponse> lendingList;
    private final boolean lended;

    public LendingListResponse(LendingList list){
        this.id = list.getId();
        this.lendingList = list.getBooks().stream().map(BookResponse::new).collect(Collectors.toList());
        this.lended = list.isLended();
    }
}
