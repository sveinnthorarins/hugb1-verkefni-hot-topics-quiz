package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "userParent", cascade = CascadeType.ALL)
  private List<CompletedQuiz> completed;
  
  private boolean admin;

  public void addCompletedQuiz(CompletedQuiz completedQuiz) {
    completed.add(completedQuiz);
  }

  public User() {}

  public User(String username, String name, boolean admin) {
    this.username = username;
    this.name = name;
    this.admin = admin;
    this.completed = new ArrayList<CompletedQuiz>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<CompletedQuiz> getCompleted() {
    return completed;
  }

  public void setCompleted(List<CompletedQuiz> completed) {
    this.completed = completed;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }
}
