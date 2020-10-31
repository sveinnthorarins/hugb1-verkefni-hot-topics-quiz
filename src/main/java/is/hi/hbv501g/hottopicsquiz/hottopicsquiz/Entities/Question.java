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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Question {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "quiz_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Quiz quizParent;
  
  private String text;

  @ElementCollection(targetClass = String.class)
  private List<String> answer;

  private String infoUrl;

  public Question(Quiz quizParent, String text, List<String> answer, String infoUrl) {
    this.quizParent = quizParent;
    this.text = text;
    this.answer = answer;
    this.infoUrl = infoUrl;
  }

  public Quiz getQuizParent() {
    return quizParent;
  }
  
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<String> getAnswer() {
    return answer;
  }

  public void setAnswer(List<String> answer) {
    this.answer = answer;
  }

  public String getInfoUrl() {
    return infoUrl;
  }

  public void setInfoUrl(String infoUrl) {
    this.infoUrl = infoUrl;
  }
}
