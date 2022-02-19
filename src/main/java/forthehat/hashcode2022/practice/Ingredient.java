package forthehat.hashcode2022.practice;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
  public String name;
  public List<Client> clients = new ArrayList<Client>();
  public int likeAmount;
  public int dislikeAmount;

  public Ingredient(String name, List<Client> clients){
    this.name = name;
    this.clients = clients;
  }
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

  public int getClientsWhoLikeCount() {
    return likeAmount;
  }

  public int getClientsWhoDislikeCount() {
    return dislikeAmount;
  }

  public int getClientsWhoAcceptCount() {
    return clients.size() - dislikeAmount;
  }

  public List<Client> getClientsWhoAccept() {
    return clients.stream()
        .filter(client -> getClientsWhoLike().contains(client)
            || !getClientsWhoDislike().contains(client))
        .toList();
  }

  @Override
  public String toString() {
    return name;
  }
}
