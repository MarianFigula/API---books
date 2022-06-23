package sk.stuba.fei.uim.oop.assignment3.lendinglist.data;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import javax.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class LendingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private List<Book> books;

    private boolean lended;

}
