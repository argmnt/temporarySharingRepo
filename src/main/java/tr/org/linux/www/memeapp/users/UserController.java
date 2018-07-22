package tr.org.linux.www.memeapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    String findAll(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/{id}")
    String show(@PathVariable Long id) {
//        Optional<User> user = userRepository.findById(id);
        return "users/index";
    }

    @GetMapping("/new")
    String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "users/new";
    }

    @PostMapping("")
    String create(User user) {
        User persistedUser = userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    String edit(Model model, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        model.addAttribute("user", user.get());
        return "users/edit";
    }

    @PutMapping("/{id}")
    String update(User user, @PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User persistedUser = userOptional.get();
        persistedUser.setEmail(user.getEmail());
        persistedUser.setFirstName(user.getFirstName());
        persistedUser.setLastName(user.getLastName());
        userRepository.save(persistedUser);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}
