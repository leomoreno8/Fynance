package br.edu.utfpr.pb.pw25s.Fynance.service.impl;

import br.edu.utfpr.pb.pw25s.Fynance.model.Transaction;
import br.edu.utfpr.pb.pw25s.Fynance.model.User;
import br.edu.utfpr.pb.pw25s.Fynance.model.Wallet;
import br.edu.utfpr.pb.pw25s.Fynance.repository.TransactionRepository;
import br.edu.utfpr.pb.pw25s.Fynance.repository.UserRepository;
import br.edu.utfpr.pb.pw25s.Fynance.repository.WalletRepository;
import br.edu.utfpr.pb.pw25s.Fynance.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, WalletRepository walletRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {

        return transactionRepository.save(transaction);

    }

    @Override
    public Transaction update(Long id, Transaction transaction) {
        Transaction transactionToUpdate = transactionRepository.getOne(id);
        transactionToUpdate.setName(transaction.getName());
        transactionToUpdate.setDateTransaction(transaction.getDateTransaction());
        transactionToUpdate.setValueTransaction(transaction.getValueTransaction());
        transactionToUpdate.setCategory(transaction.getCategory());
        transactionToUpdate.setES(transaction.getES());
        transactionToUpdate.setDescription(transaction.getDescription());
        transactionToUpdate.setFees(transaction.getFees());
        transactionToUpdate.setTotal(transaction.getTotal());
        transactionToUpdate.setWallet(transaction.getWallet());

        transactionRepository.save(transactionToUpdate);

        return transactionRepository.save(transactionToUpdate);
    }

    @Override
    public Transaction findOne(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Page<Transaction> findAll(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    @Override
    public Long count() {
        return transactionRepository.count();
    }

    @Override
    public Boolean exists(Long id) {
        return transactionRepository.existsById(id);
    }

    @Override
    public User userExists(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Wallet walletExists(Long id) {
        return walletRepository.findWalletById(id);
    }

    @Override
    public void delete(Long id) {
        walletRepository.deleteById(id);
    }

}
