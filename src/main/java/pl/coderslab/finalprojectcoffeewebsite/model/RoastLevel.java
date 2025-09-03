package pl.coderslab.finalprojectcoffeewebsite.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roast_levels")
@Data
@NoArgsConstructor
public class RoastLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_level", nullable = false, unique = true)
    private String nameLevel;
}
