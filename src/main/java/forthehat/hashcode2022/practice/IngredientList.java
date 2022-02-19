package forthehat.hashcode2022.practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class IngredientList extends ArrayList<Ingredient> {

  public IngredientList(Collection<Ingredient> ingredients) {
    super(ingredients);
  }

  public Ingredient getIngredientByName(String name) {
    Optional<Ingredient> optionalIngredient = this.stream()
        .filter(ingredient -> ingredient.name().equals(name))
        .findAny();
    return optionalIngredient.orElse(null);
  }


}
