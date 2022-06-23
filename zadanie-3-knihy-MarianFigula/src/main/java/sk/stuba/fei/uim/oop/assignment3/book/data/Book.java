package sk.stuba.fei.uim.oop.assignment3.book.data;

import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private Author author;
    private Integer pages;
    private Integer amount;
    private Integer lendCount;
}
