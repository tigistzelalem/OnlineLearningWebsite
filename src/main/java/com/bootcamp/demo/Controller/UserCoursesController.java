package com.bootcamp.demo.Controller;

import java.util.List;
// import java.util.Optional;

import javax.validation.Valid;

import com.bootcamp.demo.Model.Courses;
import com.bootcamp.demo.Model.User;
import com.bootcamp.demo.Model.UserCourses;
import com.bootcamp.demo.Repository.CoursesRepository;
import com.bootcamp.demo.Repository.UserCoursesRepository;
import com.bootcamp.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserCoursesController {

  @Autowired
  private UserCoursesRepository ucRepo;

  @Autowired
  private CoursesRepository courseRepo;

  @Autowired
  private UserRepository Repo;

  @GetMapping("/userdashboard")
  public String addFormCourse(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();

    User auth = Repo.findByEmail(currentPrincipalName);

    if (auth == null) {
      return "redirect:/";
    }

    List<UserCourses> courses = ucRepo.findUserById(auth.getId());

    System.out.println(courses);

    model.addAttribute("courses", courses);

    return "userdashboard";
  }

  @PostMapping("/enroll-course/{id}")
  public String enrollCourse(@PathVariable("id") long id, @ModelAttribute("courses") @Valid UserCourses userCourses,
      BindingResult result, Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();

    User eUser = Repo.findByEmail(currentPrincipalName);
    Courses courses = courseRepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));

    if (result.hasErrors()) {
      courses.setId(id);
      return "home";
    }

    userCourses.setUser(eUser);
    userCourses.setCourses(courses);

    ucRepo.save(userCourses);

    return "redirect:/userdashboard";
  }

}
