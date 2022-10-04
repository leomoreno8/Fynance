package br.edu.utfpr.pb.pw25s.Fynance.controller;

import br.edu.utfpr.pb.pw25s.Fynance.dto.WalletDto;
import br.edu.utfpr.pb.pw25s.Fynance.model.Wallet;
import br.edu.utfpr.pb.pw25s.Fynance.service.WalletService;
import br.edu.utfpr.pb.pw25s.Fynance.utils.GenericResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("wallets")
public class WalletController {
    private final WalletService walletService;
    private ModelMapper modelMapper;

    public WalletController(WalletService walletService,
                                 ModelMapper modelMapper) {
        this.walletService = walletService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<WalletDto> save(@RequestBody @Valid WalletDto walletDto) {
        Wallet wallet = walletService.save(
                convertDtoToEntity(walletDto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(wallet.getId()).toUri();

        return ResponseEntity.created(location).body(convertEntityToDto(wallet));
    }

    @GetMapping("{id}") // http://localhost:8080/wallets/1
    public ResponseEntity<WalletDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(convertEntityToDto(walletService.findOne(id)));
    }

    @GetMapping
    public ResponseEntity<List<WalletDto>> findAll() {
        return ResponseEntity.ok(
                walletService.findAll().stream()
                        .map(this::convertEntityToDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("page")
    public ResponseEntity<Page<WalletDto>> findAllPaged(@RequestParam int page,
                                                             @RequestParam int size,
                                                             @RequestParam(required = false) String order,
                                                             @RequestParam(required = false) Boolean asc) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (order != null && asc != null) {
            pageRequest = PageRequest.of(page, size,
                    asc ? Sort.Direction.ASC : Sort.Direction.DESC, order);
        }
        return ResponseEntity.ok(
                walletService.findAll(pageRequest).map(
                        this::convertEntityToDto) );

    }

    @PutMapping
    public ResponseEntity<WalletDto> update(@RequestBody @Valid WalletDto walletDto) {
        Wallet wallet = walletService.save(
                convertDtoToEntity(walletDto));
        return ResponseEntity.ok(convertEntityToDto(wallet));
    }

    @GetMapping("count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok( walletService.count() );
    }

    @GetMapping("exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        return ResponseEntity.ok( walletService.exists(id) );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        walletService.delete(id);
    }




    private Wallet convertDtoToEntity(WalletDto walletDto) {
        return modelMapper.map(walletDto, Wallet.class);
    }

    private WalletDto convertEntityToDto(Wallet wallet) {
        return modelMapper.map(wallet, WalletDto.class);
    }
}
