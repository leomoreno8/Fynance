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

    @NotNull
    private String name;

    @NotNull
    private char type;

    @NotNull
    private int number;

    @NotNull
    private int agency;

    @NotNull
    private int bank;

    @NotNull
    private Currency balance;

}
