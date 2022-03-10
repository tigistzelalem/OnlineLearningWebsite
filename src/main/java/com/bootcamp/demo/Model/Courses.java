package com.bootcamp.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "courses")
public class Courses {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false, length = 45)
  private String title;
  @Column(nullable = false, length = 255)
  private String description;
  @Column(nullable = false, length = 20)
  private String instructur;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "file_path")
  private String filePath;

  @Column(name = "file_type")
  private String fileType;

  @Column(name = "file_size")
  private String fileSize;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void getDescription(String description) {
    this.description = description;
  }

  public String getInstructur() {
    return instructur;
  }

  public void setInstructur(String instructur) {
    this.instructur = instructur;
  }
}
