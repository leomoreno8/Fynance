package br.edu.utfpr.pb.pw25s.Fynance.service;

import br.edu.utfpr.pb.pw25s.Fynance.model.User;
import br.edu.utfpr.pb.pw25s.Fynance.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public User save(User user) {
        user.setPassword( passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        User userToUpdate = userRepository.getOne(id);
        userToUpdate.setDisplayName(user.getDisplayName());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword( passwordEncoder.encode(user.getPassword()));
        userRepository.save(userToUpdate);

        return userRepository.save(userToUpdate);
    }
}
