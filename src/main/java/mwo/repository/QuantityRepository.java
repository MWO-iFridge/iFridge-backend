package mwo.repository;

import mwo.entity.Quantity;
import mwo.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {
    List<Quantity> findByRecipeId(Long recipeId);
}
