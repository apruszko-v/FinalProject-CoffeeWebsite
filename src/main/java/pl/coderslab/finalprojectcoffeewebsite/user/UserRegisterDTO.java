package pl.coderslab.finalprojectcoffeewebsite.user;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {
    private String username;
    private String email;
    private String password;
}
