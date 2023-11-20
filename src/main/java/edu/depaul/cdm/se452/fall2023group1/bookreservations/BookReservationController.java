package edu.depaul.cdm.se452.fall2023group1.bookreservations;

import edu.depaul.cdm.se452.fall2023group1.books.BookService;
import edu.depaul.cdm.se452.fall2023group1.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookreservation")
public class BookReservationController {

    @Autowired
    private BookReservationService service;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserRepository repository;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("reservations", service.getAllReservations());
        model.addAttribute("books", bookService.list());
        model.addAttribute("users", repository.findAll());
        if (session.getAttribute("course") == null) {
            model.addAttribute("reservation", new BookReservation());
            model.addAttribute("btnAddOrModifyLabel", "Reserve Book");
        } else {
            model.addAttribute("reservation", session.getAttribute("reservation"));
            model.addAttribute("btnAddOrModifyLabel", "Modify Book");
        }
        return "bookreservations/list";
    }

    @PostMapping
    public String save(@ModelAttribute BookReservation reservation, HttpSession session) {
//        if (course.getId() == 0)
//            service.create(course);
//        else {
//            var editCourse = service.find(course.getId());
//            editCourse.setDept(course.getDept());
//            editCourse.setNum(course.getNum());
//            editCourse.setDescription(course.getDescription());
//            service.create(editCourse);
//            session.setAttribute("course", null);
//        }
        return "redirect:/bookreservation";
    }
}
