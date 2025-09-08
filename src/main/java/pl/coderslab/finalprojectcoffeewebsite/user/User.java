package pl.coderslab.finalprojectcoffeewebsite.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.finalprojectcoffeewebsite.coffee.Coffee;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,unique = true)
    private String email;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_favorite_coffees",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_id")
    )
    private Set<Coffee> favoriteCoffees;

}
