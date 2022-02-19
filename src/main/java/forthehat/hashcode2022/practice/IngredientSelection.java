package forthehat.hashcode2022.practice;

import java.util.List;

public record IngredientSelection(List<Ingredient> ingredients, List<Client> clients) {

  public List<Client> getSatisfiedClients() {
    return clients.stream()
        .filter(client -> client.likedIngredients().containsAll(ingredients)
            && client.dislikedIngredients().stream().noneMatch(ingredients::contains))
        .toList();
  }

  public int getNumberOfSatisfiedClients() {
    return getSatisfiedClients().size();
  }
}
