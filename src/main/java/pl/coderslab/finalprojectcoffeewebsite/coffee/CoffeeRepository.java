package pl.coderslab.finalprojectcoffeewebsite.coffee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

// niestandardowe metody w Repository - JPQL, Query

    //    filtrowanie po roast level
    @Query("SELECT c FROM Coffee c JOIN c.roastLevel rl WHERE rl.nameLevel = :roastLevelName")
    List<Coffee> findByRoastLevel(@Param("roastLevelName") String roastLevelName);

    //    filtrowanie po smaku
    @Query("SELECT c FROM Coffee c JOIN c.flavourNotes fn WHERE fn.nameFlavour = :nameFlavour")
    List<Coffee> findByNameFlavour(@Param("nameFlavour") String nameFlavour);

    @Query("SELECT DISTINCT c FROM Coffee c " +
            "LEFT JOIN c.origin o " +
            "LEFT JOIN c.roastLevel rl " +
            "LEFT JOIN c.flavourNotes fn " +
            "LEFT JOIN c.recommendedMethods bm " +
            "WHERE (:origin IS NULL OR :origin = '' OR LOWER(o.countryOrigin) = LOWER(:origin)) " +
            "AND (:roastLevel IS NULL OR :roastLevel = '' OR LOWER(rl.nameLevel) = LOWER(:roastLevel)) " +
            "AND (:flavourNote IS NULL OR :flavourNote = '' OR LOWER(fn.nameFlavour) = LOWER(:flavourNote)) " +
            "AND (:brewingMethod IS NULL OR :brewingMethod = '' OR LOWER(bm.nameMethod) = LOWER(:brewingMethod))")
    List<Coffee> filterCoffees(@Param("origin") String origin,
                               @Param("roastLevel") String roastLevel,
                               @Param("flavourNote") String flavourNote,
                               @Param("brewingMethod") String brewingMethod);

    @Query("SELECT c FROM Coffee c JOIN c.roastery r " +
            "WHERE LOWER(c.nameCoffee) LIKE LOWER(CONCAT('%', :coffeeName, '%')) " +
            "AND LOWER(r.nameRoastery) LIKE LOWER(CONCAT('%', :roasteryName, '%'))")
    Optional<Coffee> findByNameAndRoastery(@Param("coffeeName") String coffeeName, @Param("roasteryName") String roasteryName);

}
