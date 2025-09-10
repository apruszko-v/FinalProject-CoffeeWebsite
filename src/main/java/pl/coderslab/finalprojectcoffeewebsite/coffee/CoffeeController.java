package pl.coderslab.finalprojectcoffeewebsite.coffee;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {
    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public List<CoffeeDTO> getAllCoffees() {
        return coffeeService.getAllCoffees();
    }

    @GetMapping("/id/{coffeeId}")
    public CoffeeDTO getCoffeeById(@PathVariable Long coffeeId) {
        return coffeeService.getCoffeeById(coffeeId);
    }

    @GetMapping("/origins")
    public List<String> getOrigins() {
        return coffeeService.getAllOrigins();
    }

    @GetMapping("/brewing-methods")
    public List<String> getBrewingMethods() {
        return coffeeService.getAllBrewingMethods();
    }

    @GetMapping("/flavour-notes")
    public List<String> getFlavourNotes() {
        return coffeeService.getAllFlavourNotes();
    }

    @GetMapping("/roast-levels")
    public List<String> getRoastLevels() {
        return coffeeService.getAllRoastLevels();
    }

    //    filter coffees by origin, roastLevel, flavourNote, brewingMethod
    @GetMapping("/filter")
    public List<CoffeeDTO> getByFilter(
            @RequestParam(required = false) List<String> origin,
            @RequestParam(required = false) List<String> roastLevel,
            @RequestParam(required = false) List<String> flavourNote,
            @RequestParam(required = false) List<String> brewingMethod
    ) {
        return coffeeService.filterCoffees(origin, roastLevel, flavourNote, brewingMethod);
    }
}
