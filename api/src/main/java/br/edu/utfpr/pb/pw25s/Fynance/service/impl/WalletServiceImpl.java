package br.edu.utfpr.pb.pw25s.Fynance.service.impl;

import br.edu.utfpr.pb.pw25s.Fynance.model.User;
import br.edu.utfpr.pb.pw25s.Fynance.model.Wallet;
import br.edu.utfpr.pb.pw25s.Fynance.repository.WalletRepository;
import br.edu.utfpr.pb.pw25s.Fynance.repository.UserRepository;
import br.edu.utfpr.pb.pw25s.Fynance.service.WalletService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletServiceImpl(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Wallet save(Wallet wallet) {

        return walletRepository.save(wallet);

    }

    @Override
    public Wallet update(Long id, Wallet wallet) {
        Wallet walletToUpdate = walletRepository.getOne(id);
        walletToUpdate.setName(wallet.getName());
        walletToUpdate.setBalance(wallet.getBalance());
        walletToUpdate.setType(wallet.getType());
        walletToUpdate.setAgency(wallet.getAgency());
        walletToUpdate.setBank(wallet.getBank());
        walletToUpdate.setNumber(wallet.getNumber());

        walletRepository.save(walletToUpdate);

        return walletRepository.save(walletToUpdate);
    }

    @Override
    public Wallet findOne(Long id) {
        return walletRepository.findById(id).orElse(null);
    }

    @Override
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Page<Wallet> findAll(Pageable pageable) {
        return walletRepository.findAll(pageable);
    }

    @Override
    public Long count() {
        return walletRepository.count();
    }

    @Override
    public Boolean exists(Long id) {
        return walletRepository.existsById(id);
    }

    @Override
    public User userExists(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void delete(Long id) {
        walletRepository.deleteById(id);
    }

}
