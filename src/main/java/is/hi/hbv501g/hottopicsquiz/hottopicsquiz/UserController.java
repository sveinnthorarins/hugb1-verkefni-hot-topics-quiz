package is.hi.hbv501g.hottopicsquiz.hottopicsquiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.User;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Services.UserService;

@Controller
@SessionAttributes("User")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService serv) {
    this.userService = serv;
  }

  @RequestMapping("/")
  public String homeLoginCheck(Model model) {
    if(model.getAttribute("User") != null) return "redirect:/menu";
    return "redirect:/login";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String userLoginPage() {
    return "login";
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String userLogin(@RequestParam String username, @RequestParam String password, Model model) {
    User user = userService.validateLogin(username, password);
    if(user == null) {
      model.addAttribute("error", "Incorrect username or password.");
      return "login";
    }
    model.addAttribute("User", user);
    return "redirect:/menu";
  }

  @RequestMapping(value = "/signup", method = RequestMethod.GET)
  public String userSignupPage() {
    return "signup";
  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public String userSignup(String username, String name, String password, Model model) {
    User user = userService.saveNewUser(username, name, password);
    if(user == null) { //username already taken
      model.addAttribute("username", username);
      model.addAttribute("name", name);
      model.addAttribute("password", password);
      model.addAttribute("error", "Username already taken.");
      return "signup";
    }
    model.addAttribute("User", user);
    return "redirect:/menu";
  }

}
