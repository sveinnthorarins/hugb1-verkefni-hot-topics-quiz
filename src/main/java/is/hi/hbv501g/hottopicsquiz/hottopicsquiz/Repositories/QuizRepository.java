package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
  
  List<Quiz> findAllByOrderByEndDateDesc();
  List<Quiz> findByName(String name);

}
