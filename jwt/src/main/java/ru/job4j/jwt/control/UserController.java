package ru.job4j.jwt.control;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.jwt.models.Person;
import ru.job4j.jwt.store.UserStore;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserStore users;
    private BCryptPasswordEncoder encoder;

    public UserController(UserStore users,
                          BCryptPasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        users.save(person);
    }

  //  @GetMapping("/login")
  //  public Person login(@RequestBody Person person) {
  //      return users.findByUsername(person.getUsername());
  //  }

    @GetMapping("/all")
    public List<Person> findAll() {
        return users.findAll();
    }
}
