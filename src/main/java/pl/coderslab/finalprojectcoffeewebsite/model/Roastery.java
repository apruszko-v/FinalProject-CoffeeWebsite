package pl.coderslab.finalprojectcoffeewebsite.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roasteries")
@Data
@NoArgsConstructor
public class Roastery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name_roastery", nullable = false, unique = true)
    private String nameRoastery;

    @Column(name ="country_roastery")
    private String countryRoastery;
}
