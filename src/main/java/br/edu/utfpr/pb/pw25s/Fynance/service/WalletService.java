package br.edu.utfpr.pb.pw25s.Fynance.service;

import br.edu.utfpr.pb.pw25s.Fynance.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WalletService {
    Wallet save(Wallet wallet);

    Wallet update(Wallet wallet);

    Wallet findOne(Long id);

    List<Wallet> findAll();

    Page<Wallet> findAll(Pageable pageable);

    Long count();

    Boolean exists(Long id);

    void delete(Long id);
}
