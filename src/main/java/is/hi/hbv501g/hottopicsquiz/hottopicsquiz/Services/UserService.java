package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Services;

import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.User;

public interface UserService {
  
  public User validateLogin(String username, String password);

  public void saveUser(User user);

  public User saveNewUser(String username, String name, String password);

}
