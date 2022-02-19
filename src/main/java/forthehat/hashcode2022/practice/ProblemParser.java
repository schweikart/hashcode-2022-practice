package forthehat.hashcode2022.practice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ProblemParser {
  private final Iterator<String> inputLineIterator;
  private IngredientList allIngredientsList;
  private List<Client> clientList;

  public ProblemParser(Iterator<String> inputLineIterator) {
    this.inputLineIterator = Objects.requireNonNull(inputLineIterator);
  }

  public Problem parseProblem() {
    int clientAmount = parseClientAmount(inputLineIterator.next());
    this.clientList = new ArrayList<>(clientAmount);
    this.allIngredientsList = new IngredientList();

    for (int i = 0; i < clientAmount; i++) {
      parseAndAddClient(inputLineIterator.next(), inputLineIterator.next());
    }

    if (inputLineIterator.hasNext()) {
      throw new RuntimeException("input is too long!");
    }

    System.out.printf("Parsed problem with %d clients and %d different ingredients%n",
        this.clientList.size(), this.allIngredientsList.size());
    return new Problem(allIngredientsList, clientList);
  }

  private void parseAndAddClient(String likedIngredientsInput, String dislikedIngredientsInput) {
    var client = new Client(
        parseIngredientList(likedIngredientsInput),
        parseIngredientList(dislikedIngredientsInput)
    );
    this.clientList.add(client);
  }

  private IngredientList parseIngredientList(String input) {
    String[] ingredientNames = input.split(" ");
    int ingredientAmount = Integer.parseInt(ingredientNames[0]);

    IngredientList ingredientList = new IngredientList();
    for (int i = 0; i < ingredientAmount; i++) {
      String ingredientName = ingredientNames[i + 1]; // ignore amount in the front
      ingredientList.add(getOrCreateIngredientForName(ingredientName));
    }

    return ingredientList;
  }

  private Ingredient getOrCreateIngredientForName(String ingredientName) {
    Ingredient ingredient = this.allIngredientsList.getIngredientByName(ingredientName);
    if (ingredient == null) {
      ingredient = new Ingredient(ingredientName, this.clientList);
      this.allIngredientsList.add(ingredient);
    }
    return ingredient;
  }

  private int parseClientAmount(String input) {
    return Integer.parseInt(input);
  }
}
