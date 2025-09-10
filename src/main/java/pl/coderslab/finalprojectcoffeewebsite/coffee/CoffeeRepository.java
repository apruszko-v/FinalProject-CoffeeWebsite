package pl.coderslab.finalprojectcoffeewebsite.coffee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

    @Query("SELECT DISTINCT o.countryOrigin FROM Coffee c JOIN c.origin o")
    List<String> findDistinctOrigins();

    @Query("SELECT DISTINCT rl.nameLevel FROM Coffee c JOIN c.roastLevel rl")
    List<String> findDistinctRoastLevels();

    @Query("SELECT DISTINCT fn.nameFlavour FROM Coffee c JOIN c.flavourNotes fn")
    List<String> findDistinctFlavourNotes();

    @Query("SELECT DISTINCT bm.nameMethod FROM Coffee c JOIN c.recommendedMethods bm")
    List<String> findDistinctBrewingMethods();

    @Query("""
    SELECT DISTINCT c FROM Coffee c
    WHERE (:origins IS NULL OR LOWER(c.origin.countryOrigin) IN :origins)
      AND (:roastLevels IS NULL OR LOWER(c.roastLevel.nameLevel) IN :roastLevels)
      AND (:flavourNotes IS NULL OR EXISTS (
           SELECT fn FROM c.flavourNotes fn
           WHERE LOWER(fn.nameFlavour) IN :flavourNotes
      ))
      AND (:brewingMethods IS NULL OR EXISTS (
           SELECT bm FROM c.recommendedMethods bm
           WHERE LOWER(bm.nameMethod) IN :brewingMethods
      ))
""")
    List<Coffee> filterCoffees(@Param("origins") List<String> origins,
                               @Param("roastLevels") List<String> roastLevels,
                               @Param("flavourNotes") List<String> flavourNotes,
                               @Param("brewingMethods") List<String> brewingMethods);


    @Query("SELECT c FROM Coffee c JOIN c.roastery r " +
            "WHERE LOWER(c.nameCoffee) LIKE LOWER(CONCAT('%', :coffeeName, '%')) " +
            "AND LOWER(r.nameRoastery) LIKE LOWER(CONCAT('%', :roasteryName, '%'))")
    Optional<Coffee> findByNameAndRoastery(@Param("coffeeName") String coffeeName, @Param("roasteryName") String roasteryName);
}
