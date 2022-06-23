package sk.stuba.fei.uim.oop.assignment3.author.logic;

import sk.stuba.fei.uim.oop.assignment3.author.communication.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.author.data.IAuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private IAuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author createAuthor(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setSurname(request.getSurname());
        author.setBooks(new ArrayList<>());
        return this.authorRepository.save(author);
    }

    @Override
    public Author getById(Long authorId)  throws NotFoundException {
        Author author = this.authorRepository.findAuthorById(authorId);
        if (author == null) {
            throw new NotFoundException();
        }
        return author;
    }

    @Override
    public Author updateAuthor(Long authorId, AuthorRequest request) throws NotFoundException{
        Author author = this.getById(authorId);
        if (request.getName() != null){
            author.setName(request.getName());
        }
        if (request.getSurname() != null){
            author.setSurname(request.getSurname());
        }
        return this.authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long authorId) throws NotFoundException {
        this.authorRepository.delete(this.getById(authorId));
    }
}
