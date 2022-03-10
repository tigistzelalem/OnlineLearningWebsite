package com.bootcamp.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
import javax.persistence.Table;

// import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
// import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
@DynamicInsert
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "course_id", referencedColumnName = "id")
  private Courses courses;

  @Column(nullable = false, unique = true, length = 45)
  private String email;
  @Column(nullable = false, length = 45)
  private String firstname;
  @Column(nullable = false, length = 20)
  private String lastname;
  @Column(nullable = false, unique = true, length = 64)
  private String password;
  @Column
  private String role;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFristname() {
    return firstname;
  }

  public void setFristname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastnamename(String lastname) {
    this.lastname = lastname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Courses getCourses() {
    return courses;
  }

  public void setRole(Courses courses) {
    this.courses = courses;
  }

}
