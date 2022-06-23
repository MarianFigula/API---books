package sk.stuba.fei.uim.oop.assignment3.author.logic;

import sk.stuba.fei.uim.oop.assignment3.author.communication.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import java.util.List;

public interface IAuthorService {

    List<Author> getAllAuthors();

    Author createAuthor(AuthorRequest request);

    Author getById(Long authorId) throws NotFoundException;

    Author updateAuthor(Long authorId, AuthorRequest request) throws NotFoundException;

    void deleteAuthor(Long authorId) throws NotFoundException;

}
