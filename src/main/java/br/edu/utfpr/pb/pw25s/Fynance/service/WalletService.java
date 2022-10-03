package br.edu.utfpr.pb.pw25s.Fynance.service;

import br.edu.utfpr.pb.pw25s.Fynance.model.Wallet;
import br.edu.utfpr.pb.pw25s.Fynance.repository.WalletRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    //BCryptPasswordEncoder passwordEncoder;

    public WalletService(WalletRepository walletRepository) {

        this.walletRepository = walletRepository;
        //passwordEncoder = new BCryptPasswordEncoder();
    }

    public Wallet save(Wallet wallet) {
        //wallet.setPassword( passwordEncoder.encode(wallet.getPassword()));
        return walletRepository.save(wallet);
    }
}
