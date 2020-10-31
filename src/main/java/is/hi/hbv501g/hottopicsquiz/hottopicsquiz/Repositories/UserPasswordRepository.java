package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.User;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.UserPassword;

@Repository
public interface UserPasswordRepository extends JpaRepository<UserPassword, User> {
  
  Optional<UserPassword> findByUserParent(User userParent);

}
