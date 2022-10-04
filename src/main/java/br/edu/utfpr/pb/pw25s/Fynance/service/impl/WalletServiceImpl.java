package br.edu.utfpr.pb.pw25s.Fynance.service.impl;

import br.edu.utfpr.pb.pw25s.Fynance.model.Wallet;
import br.edu.utfpr.pb.pw25s.Fynance.repository.WalletRepository;
import br.edu.utfpr.pb.pw25s.Fynance.service.WalletService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
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
    public void delete(Long id) {
        walletRepository.deleteById(id);
    }

}
