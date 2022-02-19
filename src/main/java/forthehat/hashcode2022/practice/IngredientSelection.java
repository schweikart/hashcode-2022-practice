package forthehat.hashcode2022.practice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record IngredientSelection(List<Ingredient> ingredients, List<Client> clients) {

  public List<Client> getSatisfiedClients() {
    return clients.stream()
        .filter(client -> ingredients.containsAll(client.likedIngredients())
            && client.dislikedIngredients().stream().noneMatch(ingredients::contains))
        .toList();
  }

  public int getNumberOfSatisfiedClients() {
    return getSatisfiedClients().size();
  }

  public int getFasterNumberOfSatisfiedClients() {
    return getSatisfiedClients().size();
  }
}
