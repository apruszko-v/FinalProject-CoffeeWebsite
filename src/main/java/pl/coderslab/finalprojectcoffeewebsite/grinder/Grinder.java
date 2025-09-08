package pl.coderslab.finalprojectcoffeewebsite.grinder;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grinders")
@Data
@NoArgsConstructor
public class Grinder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_grinder", nullable = false, unique = true)
    private String nameGrinder;
}
