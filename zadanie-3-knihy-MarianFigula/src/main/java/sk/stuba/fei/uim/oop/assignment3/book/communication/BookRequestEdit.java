package sk.stuba.fei.uim.oop.assignment3.book.communication;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookRequestEdit {
    private String name;
    private String description;
    private Long author;
    private Integer pages;
}
