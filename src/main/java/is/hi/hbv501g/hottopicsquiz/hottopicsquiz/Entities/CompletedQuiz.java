package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class CompletedQuiz {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonIgnore
  private User userParent;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "quiz_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Quiz quiz;

  private int score;

  private boolean[] correctAnswers;

  public CompletedQuiz() {}

  //the boolean array should be of size 5
  public CompletedQuiz(User userParent, Quiz quiz, int score, boolean[] cA) {
    this.userParent = userParent;
    this.quiz = quiz;
    this.score = score;
    this.correctAnswers = cA;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUserParent() {
    return userParent;
  }

  public void setUserParent(User userParent) {
    this.userParent = userParent;
  }

  public Quiz getQuiz() {
    return quiz;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public boolean[] getCorrectAnswers() {
    return correctAnswers;
  }

  public void setCorrectAnswers(boolean[] correctAnswers) {
    this.correctAnswers = correctAnswers;
  }
}
