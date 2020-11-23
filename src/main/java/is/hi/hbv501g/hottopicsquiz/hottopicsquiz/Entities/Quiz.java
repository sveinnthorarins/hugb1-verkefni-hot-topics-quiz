package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Quiz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "quizParent", cascade = CascadeType.ALL)
  private List<Question> questions;

  private String name;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  public Quiz() {}

  public Quiz(List<Question> questions, String name, LocalDateTime startDate, LocalDateTime endDate) {
    this.questions = questions;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }
}
