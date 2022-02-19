package forthehat.hashcode2022.practice;

import java.util.List;

public record Ingredient(String name, List<Client> clients) {
  public List<Client> getClientsWhoLike() {
    return clients.stream()
        .filter(client -> client.likedIngredients().contains(this))
        .toList();
  }

  public List<Client> getClientsWhoDislike() {
    return clients.stream()
        .filter(client -> client.dislikedIngredients().contains(this))
        .toList();
  }

  public List<Client> getClientsWhoAccept() {
    return clients.stream()
        .filter(client -> getClientsWhoLike().contains(client)
            || !getClientsWhoDislike().contains(client))
        .toList();
  }
}
