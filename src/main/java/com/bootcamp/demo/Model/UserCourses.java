package com.bootcamp.demo.Model;

// import java.util.Date;

// import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
// import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

// import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "userCourses")
public class UserCourses {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "course_id", referencedColumnName = "id")
  private Courses courses;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Courses getCourses() {
    return courses;
  }

  public void setCourses(Courses courses) {
    this.courses = courses;
  }

}
