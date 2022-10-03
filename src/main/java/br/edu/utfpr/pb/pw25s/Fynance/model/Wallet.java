package br.edu.utfpr.pb.pw25s.Fynance.model;

import br.edu.utfpr.pb.pw25s.Fynance.validation.UniqueUsername;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Currency;

@Data
@Entity(name = "tb_wallet")

public class Wallet {
    @Id
    @GeneratedValue
    private long id;

    @NotNull // Obrigatório preencher. Não pode ser vazio.
    private String name;

    @NotNull // Obrigatório preencher. Pode ser 'outro'
    private char type;

    // Pode ser nulo dependendo do tipo da conta
    // Validação pelo tipo de conta aqui (futuramente)
    private int number;

    // Pode ser nulo dependendo do tipo da conta
    // Validação pelo tipo de conta aqui (futuramente)
    private int agency;

    // Pode ser nulo dependendo do tipo da conta
    // Validação pelo tipo de conta aqui (futuramente)
    private int bank;

    @NotNull // Pode ser 0,00
    private Currency balance;

}
