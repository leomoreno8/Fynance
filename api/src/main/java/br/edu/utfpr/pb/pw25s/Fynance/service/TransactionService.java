package br.edu.utfpr.pb.pw25s.Fynance.service;

import br.edu.utfpr.pb.pw25s.Fynance.model.Transaction;
import br.edu.utfpr.pb.pw25s.Fynance.model.User;
import br.edu.utfpr.pb.pw25s.Fynance.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    Transaction save(Transaction transaction);

    Transaction update(Long id, Transaction transaction);

    Transaction findOne(Long id);

    List<Transaction> findAll();

    Page<Transaction> findAll(Pageable pageable);

    Long count();

    Boolean exists(Long id);

    User userExists(String username);

    Wallet walletExists(Long id);

    void delete(Long id);
}
