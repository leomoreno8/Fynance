package br.edu.utfpr.pb.pw25s.Fynance.model;

//import br.edu.utfpr.pb.pw25s.Fynance.validation.UniqueUsername;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;

@Data
@Entity(name = "tb_transaction")

public class Transaction {
    @Id
    @GeneratedValue
    private long id;

    @NotNull // Obrigatório preencher. Não pode ser vazio.
    private String name;

    @NotNull // Tem que conectar com uma Wallet (Conta) aqui. Salvará o ID aqui. Essa relação será feito por Services.
    private long idWallet;

    @NotNull
    private Date dateTransaction;

    @NotNull
    private Currency value;

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
