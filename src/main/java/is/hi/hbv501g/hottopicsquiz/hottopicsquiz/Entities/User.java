package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;
  
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "userParent")
  private List<CompletedQuiz> completed;
  
  private boolean admin;

  public void addCompletedQuiz(CompletedQuiz completedQuiz) {
    completed.add(completedQuiz);
  }

  public User(String username, String name, boolean admin) {
    this.username = username;
    this.name = name;
    this.admin = admin;
    this.completed = new ArrayList<CompletedQuiz>();
  }

  public String getUsername() {
    return username;
  }

  public String getName() {
    return name;
  }

  public List<CompletedQuiz> getCompleted() {
    return completed;
  }

  public boolean isAdmin() {
    return admin;
  }
}
