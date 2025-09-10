package pl.coderslab.finalprojectcoffeewebsite.grinder;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrinderService {
    private final GrinderRepository grinderRepository;

    public GrinderService(GrinderRepository grinderRepository) {
        this.grinderRepository = grinderRepository;
    }

    public List<Grinder> getGrinders() {
        return grinderRepository.findAll();
    }
}
