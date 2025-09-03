package pl.coderslab.finalprojectcoffeewebsite.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flavour_notes")
@Data
@NoArgsConstructor
public class FlavourNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_flavour", nullable = false, unique = true)
    private String nameFlavour;
}
