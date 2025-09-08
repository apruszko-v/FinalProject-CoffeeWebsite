package pl.coderslab.finalprojectcoffeewebsite.grinder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrinderRepository extends JpaRepository<Grinder, Long> {
}
