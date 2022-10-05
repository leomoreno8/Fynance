package br.edu.utfpr.pb.pw25s.Fynance.dto;

import br.edu.utfpr.pb.pw25s.Fynance.validation.UniqueUsername;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Currency;

@Data
public class UserDto {

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String displayName;

    @NotNull
    private String email;

    @NotNull
    private String password;

}
