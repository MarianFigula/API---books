package sk.stuba.fei.uim.oop.assignment3.author.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IAuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAll();

    Author findAuthorById(Long id);

}
