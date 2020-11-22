package is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
  User findByUsername(String username);

}
