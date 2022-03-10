package com.bootcamp.demo.Repository;

import java.util.List;

import com.bootcamp.demo.Model.User;
import com.bootcamp.demo.Model.UserCourses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCoursesRepository extends JpaRepository<UserCourses, Long> {

  List<UserCourses> findUserCoursesByUserOrderById(User user);

  List<UserCourses> findAllByOrderByIdDesc();

  @Query(value = "FROM UserCourses u WHERE u.user.id= :id")
  public List<UserCourses> findUserById(Long id);

}
