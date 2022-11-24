package br.edu.utfpr.pb.pw25s.Fynance.controller;

import br.edu.utfpr.pb.pw25s.Fynance.model.User;
import br.edu.utfpr.pb.pw25s.Fynance.service.UserService;
import br.edu.utfpr.pb.pw25s.Fynance.utils.GenericResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("save")
    GenericResponse createUser(@RequestBody @Valid User user) {
        userService.save(user);
        return new GenericResponse("Registro salvo");
    }

    @PostMapping("update/{id}")
    GenericResponse updateUser(@PathVariable Long id, @RequestBody @Valid User user) {
        userService.update(id, user);
        return new GenericResponse("Registro atualizado");
    }

    @GetMapping
    String getString() {
        return "O usuário está autenticado!";
    }
}
