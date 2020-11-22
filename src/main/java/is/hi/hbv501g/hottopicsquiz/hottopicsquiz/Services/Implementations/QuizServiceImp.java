package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Services.Implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.Quiz;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Repositories.QuizRepository;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Services.QuizService;

@Service
public class QuizServiceImp implements QuizService {

  QuizRepository repository;

  @Autowired
  public QuizServiceImp(QuizRepository repo) {
    this.repository = repo;
  }

  @Override
  public List<Quiz> findAllByOrderByEndDateDesc() {
    return repository.findAllByOrderByEndDateDesc();
  }

  public Quiz findCurrentQuiz() {
    List<Quiz> quizzes = repository.findAllByOrderByEndDateDesc();
    for (int i = 0; i < quizzes.size(); i++) {
      Quiz quiz = quizzes.get(i);
      if (quiz.getEndDate().isAfter(LocalDateTime.now())) {
        if (quiz.getStartDate().isBefore(LocalDateTime.now())) return quiz;
      } else break;
    }
    return null;
  }

  @Override
  public List<Quiz> findByName(String name) {
    return repository.findByName(name);
  }

  @Override
  public Quiz saveQuiz(Quiz quiz) {
    return repository.save(quiz);
  }

  @Override
  public void deleteQuiz(Quiz quiz) {
    repository.delete(quiz);
  }
  
}
