package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.User;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.UserPassword;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Repositories.UserPasswordRepository;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Repositories.UserRepository;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Services.UserService;

@Service
public class UserServiceImp implements UserService {

  UserRepository repository;
  UserPasswordRepository pwRepo;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImp(UserRepository repo, UserPasswordRepository pwRepo) {
    this.repository = repo;
    this.pwRepo = pwRepo;
  }

  @Override
  public User findById(Long id) {
    User user = repository.findById(id).orElseThrow(
      () -> new Error("User id not found in database.")
    );
    return user;
  }

  /**
   * Validates login information.
   * @return the User entity if info is valid, {@code null} if username or password is invalid.
   */
  @Override
  public User validateLogin(String username, String password) {
    username = username.toLowerCase();
    User user = repository.findByUsername(username);
    if (user == null) return null;
    UserPassword userPw = pwRepo.findByUserParent(user).orElseThrow(
      () -> new Error("User's password not saved in database.")
    );
    if (passwordEncoder.matches(password, userPw.getEncryptedPassword())) return user;
    return null;
  }

  /**
   * Saves the User entity to database.
   */
  @Override
  public User saveUser(User user) {
    return repository.save(user);
  }

  /**
   * Creates and saves a new User entity to database.
   * <p>
   * Use the returned instance for further operations as the save operation might have changed the entity instance completely.
   * @return the saved User entity or {@code null} if username already exists in database.
   */
  @Override
  public User saveNewUser(String username, String name, String password) {
    username = username.toLowerCase();
    //Check if username already exists in database, if it does: return null
    User user = repository.findByUsername(username);
    if (user != null) return null;
    //Else we continue
    user = new User(username, name, false);
    //Save the new user to database, here we receive the
    //returned instance as the save operation might have changed the instance
    user = repository.save(user);
    //Create a new UserPassword entity and encode the password
    UserPassword userPw = new UserPassword(user, passwordEncoder.encode(password));
    //Save the new UserPassword entity
    pwRepo.save(userPw);
    //return the saved entity instance
    return user;
  }
  
}
