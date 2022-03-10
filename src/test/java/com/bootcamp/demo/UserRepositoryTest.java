package com.bootcamp.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.bootcamp.demo.Model.User;
import com.bootcamp.demo.Repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

  @Autowired
  private UserRepository repo;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testCreateUser() {
    User user = new User();
    user.setEmail("dhgjsdg@gmail.com");
    user.setPassword("f1234");
    user.setFirstname("jo");
    user.setLastname("youuu");

    User savedUser = repo.save(user);
    User existuser = entityManager.find(User.class, savedUser.getId());

    assertThat(existuser.getEmail()).isEqualTo(user.getEmail());

  }

}
