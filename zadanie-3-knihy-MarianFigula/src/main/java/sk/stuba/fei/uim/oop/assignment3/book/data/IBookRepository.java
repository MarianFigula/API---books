package sk.stuba.fei.uim.oop.assignment3.book.data;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    Book findBookById(Long bookId);

}
