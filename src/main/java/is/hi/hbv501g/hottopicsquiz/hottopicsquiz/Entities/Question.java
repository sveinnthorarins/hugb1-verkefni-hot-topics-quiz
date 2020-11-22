package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Question {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "quiz_id", nullable = false)
  @JsonIgnore
  private Quiz quizParent;
  
  private String text;

  @ElementCollection(targetClass = String.class)
  private List<String> answers;

  @ElementCollection(targetClass = Boolean.class)
  private List<Boolean> correctAnswers;

  private String infoUrl;

  public Question() {}

  public Question(Quiz quizParent, String text, List<String> answers, List<Boolean> correctAnswers, String infoUrl) {
    this.quizParent = quizParent;
    this.text = text;
    this.answers = answers;
    this.correctAnswers = correctAnswers;
    this.infoUrl = infoUrl;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Quiz getQuizParent() {
    return quizParent;
  }

  public void setQuizParent(Quiz quizParent) {
    this.quizParent = quizParent;
  }
  
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<String> getAnswers() {
    return answers;
  }

  public void setAnswers(List<String> answers) {
    this.answers = answers;
  }

  public List<Boolean> getCorrectAnswers() {
    return correctAnswers;
  }

  public void setCorrectAnswers(List<Boolean> correctAnswers) {
    this.correctAnswers = correctAnswers;
  }

  public String getInfoUrl() {
    return infoUrl;
  }

  public void setInfoUrl(String infoUrl) {
    this.infoUrl = infoUrl;
  }
}
