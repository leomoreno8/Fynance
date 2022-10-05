package br.edu.utfpr.pb.pw25s.Fynance.repository;

import br.edu.utfpr.pb.pw25s.Fynance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
