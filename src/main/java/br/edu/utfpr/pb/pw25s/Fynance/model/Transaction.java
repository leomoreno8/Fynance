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
    // Validação de só poder E ou S aqui futuramente
    private char ES;

    @NotNull // Pode ser ''
    private String description;

    @NotNull // Pode ser 'outros'
    private String category;

    @NotNull // Pode ser 0,00
    private Double fees;

    @NotNull
    private Double total;

}
