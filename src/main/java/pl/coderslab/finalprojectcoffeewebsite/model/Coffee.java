package pl.coderslab.finalprojectcoffeewebsite.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "coffees")
@Data
@NoArgsConstructor
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_coffee", nullable = false)
    private String nameCoffee;

    private String description;

    @Column(name = "rating_coffee")
    private Double ratingCoffee;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roastery_id")
    private Roastery roastery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_id")
    private Origin origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roast_level_id")
    private RoastLevel roastLevel;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "coffee_flavour_notes",
            joinColumns = @JoinColumn(name = "coffee_id"),
            inverseJoinColumns = @JoinColumn(name = "flavour_note_id")
    )
    private Set<FlavourNote> flavourNotes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "coffee_recommended_methods",
            joinColumns = @JoinColumn(name = "coffee_id"),
            inverseJoinColumns = @JoinColumn(name = "brewing_method_id")
    )
    private Set<BrewingMethod> recommendedMethods;
}
