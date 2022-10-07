package br.edu.utfpr.pb.pw25s.Fynance.controller;

import br.edu.utfpr.pb.pw25s.Fynance.dto.WalletDto;
import br.edu.utfpr.pb.pw25s.Fynance.model.Transaction;
import br.edu.utfpr.pb.pw25s.Fynance.model.Wallet;
import br.edu.utfpr.pb.pw25s.Fynance.service.CrudService;
import br.edu.utfpr.pb.pw25s.Fynance.service.TransactionService;
import br.edu.utfpr.pb.pw25s.Fynance.dto.TransactionDto;

import br.edu.utfpr.pb.pw25s.Fynance.service.WalletService;
import br.edu.utfpr.pb.pw25s.Fynance.utils.GenericResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private ModelMapper modelMapper;

    public TransactionController(TransactionService transactionService,
                            ModelMapper modelMapper) {
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("save")
    public ResponseEntity<TransactionDto> save(@RequestBody @Valid TransactionDto transactionDto) {
        Transaction transaction = transactionService.save(
                convertDtoToEntity(transactionDto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transaction.getId()).toUri();

        return ResponseEntity.created(location).body(convertEntityToDto(transaction));
    }

    @PostMapping("update/{id}")
    GenericResponse updateWallet(@PathVariable Long id, @RequestBody @Valid Transaction transaction) {
        transactionService.update(id, transaction);
        return new GenericResponse("Transacao atualizada");
    }

    @GetMapping("{id}") // http://localhost:8080/transactions/1
    public ResponseEntity<TransactionDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(convertEntityToDto(transactionService.findOne(id)));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> findAll() {
        return ResponseEntity.ok(
                transactionService.findAll().stream()
                        .map(this::convertEntityToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("page")
    public ResponseEntity<Page<TransactionDto>> findAllPaged(@RequestParam int page,
                                                        @RequestParam int size,
                                                        @RequestParam(required = false) String order,
                                                        @RequestParam(required = false) Boolean asc) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (order != null && asc != null) {
            pageRequest = PageRequest.of(page, size,
                    asc ? Sort.Direction.ASC : Sort.Direction.DESC, order);
        }
        return ResponseEntity.ok(
                transactionService.findAll(pageRequest).map(
                        this::convertEntityToDto) );

    }

    @PutMapping("save")
    public ResponseEntity<TransactionDto> update(@RequestBody @Valid TransactionDto transactionDto) {
        Transaction transaction = transactionService.save(
                convertDtoToEntity(transactionDto));
        return ResponseEntity.ok(convertEntityToDto(transaction));
    }

    @GetMapping("count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok( transactionService.count() );
    }

    @GetMapping("exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        return ResponseEntity.ok( transactionService.exists(id) );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }

    private Transaction convertDtoToEntity(TransactionDto transactionDto) {
        return modelMapper.map(transactionDto, Transaction.class);
    }

    private TransactionDto convertEntityToDto(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDto.class);
    }
}
