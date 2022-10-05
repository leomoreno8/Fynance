package br.edu.utfpr.pb.pw25s.Fynance.service.impl;

import br.edu.utfpr.pb.pw25s.Fynance.model.Transaction;
import br.edu.utfpr.pb.pw25s.Fynance.repository.TransactionRepository;
import br.edu.utfpr.pb.pw25s.Fynance.service.TransactionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class TransactionServiceImpl extends CrudServiceImpl<Transaction, Long>
    implements TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
            this.transactionRepository = transactionRepository;
        }

        @Override
        protected JpaRepository<Transaction, Long> getRepository() {
            return this.transactionRepository;
        }

}
