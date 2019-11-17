package mwo.service;

import mwo.entity.Quantity;
import mwo.repository.QuantityRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class QuantityService {

    private QuantityRepository quantityRepository;

    public QuantityService(QuantityRepository quantityRepository) {
        this.quantityRepository = quantityRepository;
    }

    public List<Quantity> getAllQuantities() {
        return quantityRepository.findAll();
    }

    private Optional<Quantity> findQuantityById(Long id) {
        return quantityRepository.findById(id);
    }

    private List<Quantity> findQuantityByRecipe(Long id) {
        return quantityRepository.findByRecipeId(id);
    }

    public List<Long> getAllIngredientsFromRecipeByRecipeId(Long id){
        List<Quantity> quantities = findQuantityByRecipe(id);
        List<Long> ingredientIds = new LinkedList<>();
        for(Quantity quantity : quantities) {
                ingredientIds.add(quantity.getIngredientId());
        }
        return ingredientIds;
    }
}
