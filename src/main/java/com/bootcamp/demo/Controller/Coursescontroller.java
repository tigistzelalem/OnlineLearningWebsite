package com.bootcamp.demo.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

// import javax.sound.sampled.Line;
import javax.validation.Valid;

import com.bootcamp.demo.Model.Courses;
import com.bootcamp.demo.Repository.CoursesRepository;
import com.bootcamp.demo.Repository.UserCoursesRepository;

// import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.LEAST;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin("http://localhost:8080")

public class Coursescontroller {

  public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

  @Autowired
  private CoursesRepository courseRepo;

  @Autowired
  private UserCoursesRepository ucRepo;

  @RequestMapping("/courses")
  public String viewcoursePage(Model model) {
    List<Courses> courses = courseRepo.findAll();
    model.addAttribute("courses", courses);
    return "courses";
  }

  @GetMapping("/list")
  public String listpage(Model model) {
    model.addAttribute("courses", new Courses());
    {
      return "list";
    }
  }

  @GetMapping("/admin/edit/{id}")
  public String updatepage(@PathVariable long id, Model model) {
    Courses courses = courseRepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

    model.addAttribute("courses", courses);
    return "edit";
  }

  @PostMapping("/admin/{id}")
  public String updateUser(@PathVariable("id") long id, @Valid Courses courses,
      BindingResult result, Model model) {
    Courses existCourse = courseRepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    if (result.hasErrors()) {
      courses.setId(id);
      return "edit";
    }
    courses.setId(existCourse.getId());
    courseRepo.save(courses);

    return "redirect:/admin";
  }

  @GetMapping("/delete/{id}")
  public String deleteUser(@PathVariable("id") long id, Model model) {
    Courses courses = courseRepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    courseRepo.delete(courses);
    return "redirect:/admin";
  }

  @GetMapping("/course/{id}")
  public String showUpdateForm(@PathVariable("id") long id, Model model) {
    Courses courses = courseRepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));

    model.addAttribute("courses", courses);
    // ucRepo.save(courses);

    return "detail";
  }

  @PostMapping("/admin")
  public String addNewCourse(@Valid Courses courses,
      @RequestParam("title") String title,
      @RequestParam("description") String description,
      @RequestParam("file") MultipartFile file) {
    try {
      String fileTempo = file.getOriginalFilename();
      String fileName = fileTempo.replaceAll(" ", "_");
      String filePath = Paths.get(uploadDirectory, fileName).toString();
      String fileType = file.getContentType();
      long size = file.getSize();
      String fileSize = String.valueOf(size);
      Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
      fileName = "/uploads/" + fileName;

      // Save the file locally
      BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
      stream.write(file.getBytes());
      stream.close();
      courses.setTitle(title);
      courses.setDescription(description);
      courses.setFilePath(filePath);
      courses.setFileSize(fileSize);
      courses.setFileType(fileType);
      courses.setFileName(fileName);

      courseRepo.save(courses);

    } catch (IOException e) {
      e.printStackTrace();

    }
    return "redirect:/admin";
  }

  @GetMapping("/learnCourse/{id}")
  public String showFileView(@PathVariable("id") long id, Model model) {
    Courses courses = courseRepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));

    model.addAttribute("courses", courses);
    // ucRepo.save(courses);

    return "watchVideo";
  }
}
