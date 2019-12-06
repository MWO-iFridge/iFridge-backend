package mwo.repository;

import mwo.entity.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Long> {

    @Query(value = "select * from QUANTITY where recipe_id = ?", nativeQuery = true)
    List<Quantity> findQuantityListByIdOfRecipe(Long idOfRecipe);
}
