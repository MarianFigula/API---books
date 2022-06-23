package sk.stuba.fei.uim.oop.assignment3.author.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private IAuthorService service;

    @GetMapping()
    public List<AuthorResponse> getAllAuthors(){
        return this.service.getAllAuthors().stream().map(AuthorResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponse createAuthor(@RequestBody AuthorRequest authorRequest){
        return new AuthorResponse(this.service.createAuthor(authorRequest));
    }

    @GetMapping(value = "/{id}")
    public AuthorResponse getById(@PathVariable("id") Long authorId)  throws NotFoundException {
        return new AuthorResponse(this.service.getById(authorId));
    }

    @PutMapping(value = "/{id}")
    public AuthorResponse updateAuthor(@PathVariable("id") Long authorId, @RequestBody AuthorRequest request) throws NotFoundException {
        return new AuthorResponse(this.service.updateAuthor(authorId, request));
    }

    @DeleteMapping(value="/{id}")
    public void deleteAuthor(@PathVariable("id") Long authorId) throws NotFoundException{
        this.service.deleteAuthor(authorId);
    }
}
