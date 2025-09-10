package pl.coderslab.finalprojectcoffeewebsite.brewingmethod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brewing-methods")
public class BrewingMethodController {
    private final BrewingMethodService brewingMethodService;

    public BrewingMethodController(BrewingMethodService brewingMethodService) {
        this.brewingMethodService = brewingMethodService;
    }

    @GetMapping
    public List<BrewingMethod> getBrewingMethods() {
        return brewingMethodService.getBrewingMethods();
    }
}
