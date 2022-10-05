package br.edu.utfpr.pb.pw25s.Fynance.repository;

import br.edu.utfpr.pb.pw25s.Fynance.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
