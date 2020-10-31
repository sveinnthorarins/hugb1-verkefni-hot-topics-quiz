package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class UserPassword {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User userParent;

  private String encryptedPassword;

  public UserPassword(User userParent, String encryptedPassword) {
    this.userParent = userParent;
    this.encryptedPassword = encryptedPassword;
  }

  public User getUserParent() {
    return userParent;
  }

  public String getEncryptedPassword() {
    return encryptedPassword;
  }

}
