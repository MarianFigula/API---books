package sk.stuba.fei.uim.oop.assignment3.lendinglist.data;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ILendingListRepository extends CrudRepository<LendingList, Long> {

    List<LendingList> findAll();

    LendingList findLendingListById(Long id);

}
