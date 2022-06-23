package sk.stuba.fei.uim.oop.assignment3.author.data;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;

    @OneToMany(orphanRemoval = true)
    private List<Book> books;

}
