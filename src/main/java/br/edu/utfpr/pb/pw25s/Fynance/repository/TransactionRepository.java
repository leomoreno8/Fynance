package br.edu.utfpr.pb.pw25s.Fynance.repository;

import br.edu.utfpr.pb.pw25s.Fynance.model.Transaction;
import br.edu.utfpr.pb.pw25s.Fynance.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
