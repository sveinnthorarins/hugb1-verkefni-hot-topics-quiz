package is.hi.hbv501g.hottopicsquiz.hottopicsquiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.CompletedQuiz;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.Quiz;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.User;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Services.QuizService;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Services.UserService;

@Controller
@SessionAttributes({"User", "CurrentQuiz"})
public class QuizController {
  
  private QuizService quizService;
  private UserService userService;

  @Autowired
  public QuizController(QuizService quizServ, UserService userServ) {
    this.quizService = quizServ;
    this.userService = userServ;
  }

  @RequestMapping(value = "/menu", method = RequestMethod.GET)
  public String quizzesMenu(Model model) {
    Quiz currentquiz = quizService.findCurrentQuiz();
    model.addAttribute("CurrentQuiz", currentquiz);
    return "menu";
  }

  @RequestMapping(value ="/play", method = RequestMethod.GET)
  public String playView() {
    return "play";
  }

  @RequestMapping(value = "/play", method = RequestMethod.POST)
  public String playPost(@RequestParam int an1, @RequestParam int an2, @RequestParam int an3, @RequestParam int an4, @RequestParam int an5, Model model) {
    User user = (User) model.getAttribute("User");
    Quiz currentquiz = (Quiz) model.getAttribute("CurrentQuiz");
    int answers[] = {an1, an2, an3, an4, an5};
    boolean correctAnswers[] = new boolean[5];
    int score = 0;
    for (int i = 0; i < 5; i++) {
      if (currentquiz.getQuestions().get(i).getCorrectAnswers().get(answers[i])) {
        correctAnswers[i] = true;
        score++;
      } else correctAnswers[i] = false;
    }

    CompletedQuiz quiz = new CompletedQuiz(user, currentquiz, score, correctAnswers);
    user.addCompletedQuiz(quiz);
    userService.saveUser(user);
    user = userService.findById(user.getId());
    model.addAttribute("User", user);
    return "redirect:/results?cId=" + user.getCompleted().get(user.getCompleted().size()-1).getId().toString();
  }

  @RequestMapping(value = "/results", method = RequestMethod.GET)
  public String resultsView(@RequestParam Long cId, Model model) {
    User user = (User) model.getAttribute("User");
    List<CompletedQuiz> cq = user.getCompleted();
    CompletedQuiz quiz = null;
    for (int i = cq.size()-1; i >= 0; i--) {
      if (cq.get(i).getId().equals(cId)) {
        quiz = cq.get(i);
        break;
      }
    }
    model.addAttribute("completedquiz", quiz);
    return "results";
  }

  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String adminView(Model model) {
    User user = (User) model.getAttribute("User");
    if (!user.isAdmin()) return "notadmin";
    List<Quiz> allquizzes = quizService.findAllByOrderByEndDateDesc();
    model.addAttribute("allquizzes", allquizzes);
    return "admin";
  }

  /*
  createQuizView() {

  }

  createQuiz() {

  }

  editQuizView() {
    
  }

  editQuiz() {
    
  }
  */
}
