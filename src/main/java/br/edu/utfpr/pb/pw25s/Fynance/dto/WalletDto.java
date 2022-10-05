package br.edu.utfpr.pb.pw25s.Fynance.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Currency;

@Data
public class WalletDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private char type;

    private int number;

    private int agency;

    private int bank;

    @NotNull // Pode ser 0,00
    private Double balance;

}
