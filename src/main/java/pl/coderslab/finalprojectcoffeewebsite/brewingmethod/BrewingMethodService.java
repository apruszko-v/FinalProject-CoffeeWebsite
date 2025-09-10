package pl.coderslab.finalprojectcoffeewebsite.brewingmethod;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrewingMethodService {
    private final BrewingMethodRepository brewingMethodRepository;
    public BrewingMethodService(BrewingMethodRepository brewingMethodRepository) {
        this.brewingMethodRepository = brewingMethodRepository;
    }

    public List<BrewingMethod> getBrewingMethods() {
        return brewingMethodRepository.findAll();
    }
}
