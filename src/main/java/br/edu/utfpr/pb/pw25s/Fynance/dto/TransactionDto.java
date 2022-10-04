package br.edu.utfpr.pb.pw25s.Fynance.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Currency;
import java.util.Date;

@Data
public class TransactionDto {

    private Long id;

    @NotNull // Obrigatório preencher. Não pode ser vazio.
    private String name;

    @NotNull // Tem que conectar com uma Wallet (Conta) aqui. Salvará o ID aqui. Essa relação será feito por Services.
    private Long idWallet;

    @NotNull
    private Date dateTransaction;

    @NotNull
    private Currency valueTransaction;

    @NotNull // E = Entrada ---- S = Saída
    // Validação de só poder E ou S aqui futuramente
    private char ES;

    @NotNull // Pode ser ''
    private String description;

    @NotNull // Pode ser 'outros'
    private String category;

    @NotNull // Pode ser 0,00
    private Currency fees;

    @NotNull
    private Currency total;
}
