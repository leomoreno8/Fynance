package br.edu.utfpr.pb.pw25s.Fynance.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Currency;
import java.util.Date;

@Data
public class TransactionDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long idWallet;

    @NotNull
    private Date dateTransaction;

    @NotNull
    private Currency valueTransaction;

    @NotNull
    private char ES;

    @NotNull
    private String description;

    @NotNull
    private String category;

    @NotNull
    private Currency fees;

    @NotNull
    private Currency total;
}
