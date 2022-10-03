package br.edu.utfpr.pb.pw25s.Fynance.controller;

import br.edu.utfpr.pb.pw25s.Fynance.error.ApiError;
import br.edu.utfpr.pb.pw25s.Fynance.model.Transaction;
import br.edu.utfpr.pb.pw25s.Fynance.service.TransactionService;
import br.edu.utfpr.pb.pw25s.Fynance.utils.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    GenericResponse createTransaction(@RequestBody @Valid Transaction transaction) {
        transactionService.save(transaction);
        return new GenericResponse("Transação salva");
    }

    @GetMapping
    String getString() {
        return "A transação foi selecionada!";
    }
}
