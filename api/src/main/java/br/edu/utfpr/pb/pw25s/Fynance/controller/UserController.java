package br.edu.utfpr.pb.pw25s.Fynance.controller;

import br.edu.utfpr.pb.pw25s.Fynance.model.User;
import br.edu.utfpr.pb.pw25s.Fynance.service.UserService;
import br.edu.utfpr.pb.pw25s.Fynance.service.AuthService;
import br.edu.utfpr.pb.pw25s.Fynance.utils.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("create")
    GenericResponse createUser(@RequestBody @Valid User user) {
        userService.save(user);
        return new GenericResponse("Registro salvo");
    }

    @PostMapping("update/{id}")
    GenericResponse updateUser(@PathVariable Long id, @RequestBody @Valid User user) {
        userService.update(id, user);
        return new GenericResponse("Registro atualizado");
    }

    @GetMapping("{user}")
    public ResponseEntity<UserDetails> get(@PathVariable String user) {
        return ResponseEntity.ok( authService.loadUserByUsername(user) );
    }

    @GetMapping
    String getString() {
        return "O usuário está autenticado!";
    }
}
