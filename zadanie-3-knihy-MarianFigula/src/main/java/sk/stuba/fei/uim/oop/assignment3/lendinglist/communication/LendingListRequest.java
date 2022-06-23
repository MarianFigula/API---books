package sk.stuba.fei.uim.oop.assignment3.lendinglist.communication;

import sk.stuba.fei.uim.oop.assignment3.book.communication.BookResponse;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LendingListRequest {
    private List<BookResponse> lendingList;
    private boolean lended;
}
