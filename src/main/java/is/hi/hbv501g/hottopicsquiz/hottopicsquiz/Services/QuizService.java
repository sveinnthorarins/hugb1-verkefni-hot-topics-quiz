package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Services;

import java.util.List;

import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.Quiz;

public interface QuizService {
  
  public List<Quiz> findAllByOrderByEndDateDesc();

  public Quiz findCurrentQuiz();

  public List<Quiz> findByName(String name);

  public Quiz findById(Long id);

  public Quiz saveQuiz(Quiz quiz);

  public void deleteQuiz(Quiz quiz);

}
