package br.edu.utfpr.pb.pw25s.Fynance.controller;

import br.edu.utfpr.pb.pw25s.Fynance.model.Transaction;
import br.edu.utfpr.pb.pw25s.Fynance.service.CrudService;
import br.edu.utfpr.pb.pw25s.Fynance.service.TransactionService;
import br.edu.utfpr.pb.pw25s.Fynance.dto.TransactionDto;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController extends CrudController<Transaction, TransactionDto, Long> {
    private final TransactionService transactionService;
    private final ModelMapper modelMapper;

    public TransactionController(TransactionService transactionService, ModelMapper modelMapper) {
        super(Transaction.class, TransactionDto.class);
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected CrudService<Transaction, Long> getService() {
        return this.transactionService;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return this.modelMapper;
    }
}
