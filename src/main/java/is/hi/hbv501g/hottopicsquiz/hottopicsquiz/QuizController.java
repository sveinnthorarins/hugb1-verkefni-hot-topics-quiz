package is.hi.hbv501g.hottopicsquiz.hottopicsquiz;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.CompletedQuiz;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.Question;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.Quiz;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Entities.User;
import is.hi.hbv501g.hottopicsquiz.hottopicsquiz.Services.QuizService;

@Controller
@SessionAttributes({"user", "currentquiz"})
public class QuizController {
  
  private QuizService quizService;

  @Autowired
  public QuizController(QuizService quizServ) {
    this.quizService = quizServ;
  }

  @RequestMapping(value = "/menu", method = RequestMethod.GET)
  public String quizzesMenu(Model model) {
    if (model.getAttribute("user") == null) return "redirect:/login";
    Quiz currentquiz = quizService.findCurrentQuiz();
    model.addAttribute("currentquiz", currentquiz);
    return "menu";
  }

  @RequestMapping(value ="/play", method = RequestMethod.GET)
  public String playView(Model model) {
    if (model.getAttribute("user") != null) return "redirect:/login";
    return "play";
  }

  @RequestMapping(value = "/play", method = RequestMethod.POST)
  public String playPost(@RequestParam int an1, @RequestParam int an2, @RequestParam int an3, @RequestParam int an4, @RequestParam int an5, Model model) {
    User user = (User) model.getAttribute("user");
    if (user == null) return "redirect:/login";
    Quiz currentquiz = (Quiz) model.getAttribute("currentquiz");
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
    return "redirect:/results?cId=" + user.getCompleted().get(user.getCompleted().size()-1).getId().toString();
  }

  @RequestMapping(value = "/results", method = RequestMethod.GET)
  public String resultsView(@RequestParam Long cId, Model model) {
    User user = (User) model.getAttribute("user");
    if (user == null) return "redirect:/login";
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
    User user = (User) model.getAttribute("user");
    if (user == null) return "redirect:/login";
    if (!user.isAdmin()) return "notadmin";
    List<Quiz> allquizzes = quizService.findAllByOrderByEndDateDesc();
    model.addAttribute("allquizzes", allquizzes);
    return "admin";
  }

  @RequestMapping(value = "/createquiz", method = RequestMethod.GET)
  public String createQuizView(Model model) {
    User user = (User) model.getAttribute("user");
    if (user == null) return "redirect:/login";
    if (!user.isAdmin()) return "notadmin";
    return "createquiz";
  }

  @RequestMapping(value = "/createquiz", method = RequestMethod.POST)
  public String createQuiz(Model model, @RequestParam String title, @RequestParam String startDate, @RequestParam String endDate, 
                            @RequestParam String q1, @RequestParam int ca1, @RequestParam String url1,
                            @RequestParam String q1an1, @RequestParam String q1an2, @RequestParam String q1an3, @RequestParam String q1an4, 
                            @RequestParam String q3, @RequestParam int ca3, @RequestParam String url2,
                            @RequestParam String q3an1, @RequestParam String q3an2, @RequestParam String q3an3, @RequestParam String q3an4, 
                            @RequestParam String q4, @RequestParam int ca4, @RequestParam String url3,
                            @RequestParam String q4an1, @RequestParam String q4an2, @RequestParam String q4an3, @RequestParam String q4an4, 
                            @RequestParam String q2, @RequestParam int ca2, @RequestParam String url4,
                            @RequestParam String q2an1, @RequestParam String q2an2, @RequestParam String q2an3, @RequestParam String q2an4, 
                            @RequestParam String q5, @RequestParam int ca5, @RequestParam String url5,
                            @RequestParam String q5an1, @RequestParam String q5an2, @RequestParam String q5an3, @RequestParam String q5an4) {
    User user = (User) model.getAttribute("user");
    if (user == null) return "redirect:/login";
    if (!user.isAdmin()) return "notadmin";
    LocalDateTime start = LocalDateTime.of(LocalDate.parse(startDate), LocalTime.of(0, 0));
    LocalDateTime end = LocalDateTime.of(LocalDate.parse(endDate), LocalTime.of(23, 59));
    Quiz quiz = new Quiz();
    Question question1 = createQuestion(q1, url1, q1an1, q1an2, q1an3, q1an4, ca1, quiz);
    Question question2 = createQuestion(q2, url2, q2an1, q2an2, q2an3, q2an4, ca2, quiz);
    Question question3 = createQuestion(q3, url3, q3an1, q3an2, q3an3, q3an4, ca3, quiz);
    Question question4 = createQuestion(q4, url4, q4an1, q4an2, q4an3, q4an4, ca4, quiz);
    Question question5 = createQuestion(q5, url5, q5an1, q5an2, q5an3, q5an4, ca5, quiz);
    List<Question> questions = new ArrayList<Question>();
    questions.add(question1);
    questions.add(question2);
    questions.add(question3);
    questions.add(question4);
    questions.add(question5);
    quiz.setQuestions(questions);
    quiz.setStartDate(start);
    quiz.setEndDate(end);
    quiz.setName(title);
    quizService.saveQuiz(quiz);
    return "admin";
  }
  
  //This site is not fully implemented but this function is
  @RequestMapping(value = "/editquiz", method = RequestMethod.GET)
  public String editQuizView(@RequestParam Long qId, Model model) {
    User user = (User) model.getAttribute("user");
    if (user == null) return "redirect:/login";
    if (!user.isAdmin()) return "notadmin";
    Quiz quiz = quizService.findById(qId);
    model.addAttribute("quiz", quiz);
    return "editquiz";
  }

  //This site is not fully implemented
  @RequestMapping(value = "/editquiz", method = RequestMethod.POST)
  public String editQuiz(Model model) {
    User user = (User) model.getAttribute("user");
    if (user == null) return "redirect:/login";
    if (!user.isAdmin()) return "notadmin";
    return "admin";
  }

  private Question createQuestion(String q, String url, String an1, String an2, String an3, String an4, int c, Quiz parent) {
    List<String> answers = new ArrayList<String>();
    answers.add(an1);
    answers.add(an2);
    answers.add(an3);
    answers.add(an4);
    List<Boolean> correct = new ArrayList<Boolean>();
    for (int i = 1; i < 5; i++) {
      if (i == c) correct.add(true);
      else correct.add(false);
    }
    Question question = new Question(parent, q, answers, correct, url);
    return question;
  }
}
