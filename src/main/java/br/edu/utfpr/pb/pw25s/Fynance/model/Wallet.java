package br.edu.utfpr.pb.pw25s.Fynance.model;

import br.edu.utfpr.pb.pw25s.Fynance.validation.UniqueUsername;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Double balance;

}
