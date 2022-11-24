package br.edu.utfpr.pb.pw25s.Fynance.service;

import br.edu.utfpr.pb.pw25s.Fynance.model.User;
import br.edu.utfpr.pb.pw25s.Fynance.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WalletService {
    Wallet save(Wallet wallet);

    Wallet update(Long id, Wallet wallet);

    Wallet findOne(Long id);

    List<Wallet> findAll();

    Page<Wallet> findAll(Pageable pageable);

    Long count();

    Boolean exists(Long id);

    User userExists(String username);

    void delete(Long id);
}
