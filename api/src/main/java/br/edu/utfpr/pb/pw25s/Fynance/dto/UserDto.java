package br.edu.utfpr.pb.pw25s.Fynance.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

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
