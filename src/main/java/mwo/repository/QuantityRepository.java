package mwo.repository;

import mwo.entity.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Long> {
    List<Quantity> findByRecipeId(Long recipeId);
}