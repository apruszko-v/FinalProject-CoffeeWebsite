package pl.coderslab.finalprojectcoffeewebsite.grinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/grinders")
public class GrinderController {
    private final GrinderService grinderService;
    public GrinderController(GrinderService grinderService) {
        this.grinderService = grinderService;
    }

    @GetMapping
    public List<Grinder> getGrinders() {
        return grinderService.getGrinders();
    }
}
