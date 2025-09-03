package pl.coderslab.finalprojectcoffeewebsite.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brew_recipes")
@Data
@NoArgsConstructor
public class BrewRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grind_setting")
    private Integer grindSetting;

    @Column(name = "coffee_dose_grams", precision = 5, scale = 2)
    private double coffeeDoseGrams;

    @Column(name = "water_volume_ml")
    private Integer waterVolumeMl;

    @Column(name = "water_temperature_celsius")
    private Integer waterTemperatureCelsius;

    @Column(name = "brewing_time_seconds")
    private Integer brewingTimeSeconds;

    @Lob
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_id", nullable = false)
    private Coffee coffee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grinder_id")
    private Grinder grinder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brewing_method_id")
    private BrewingMethod brewingMethod;
}
