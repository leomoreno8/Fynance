package br.edu.utfpr.pb.pw25s.Fynance.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull // Obrigatório preencher. Não pode ser vazio.
    private String name;

    @NotNull // Carteira que foi feita a transação
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @NotNull
    private Date dateTransaction;

    @NotNull
    private Double valueTransaction;

    @NotNull // E = Entrada ---- S = Saída
    private String es;

    @NotNull // Pode ser ''
    private String description;

    @NotNull // Pode ser 'outros'
    private String category;

    @NotNull // Pode ser 0,00
    private Double fees;

    @NotNull
    private Double total;

}
