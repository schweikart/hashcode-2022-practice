package forthehat.hashcode2022.practice;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    private List<Client> clients = new ArrayList<Client>();
    private IngredientList ingredients = new IngredientList();
    private IngredientList currentSolution = new IngredientList();
    private IngredientList unusedIngredients = new IngredientList();
    private IngredientList allTimeFavoritChild = new IngredientList();
    private static final int LIKED_TO_DISLIKED_RATIO = 1;
    private static final int NEW_INGREDIENT_PER_CHILD = 5;

    Algorithm(Problem problem) {
        this.clients = problem.clients();
        this.ingredients = problem.ingredients();
        for (Client client : clients) {
            for (Ingredient ingredient : client.likedIngredients()) {
                ingredient.likeAmount++;
            }
            for (Ingredient ingredient : client.dislikedIngredients()) {
                ingredient.dislikeAmount++;
            }
        }
    }

    public IngredientList initialSolution() {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getClientsWhoDislikeCount() == 0 || ingredient.getClientsWhoAcceptCount() > ingredient.getClientsWhoDislikeCount() * LIKED_TO_DISLIKED_RATIO) {
                currentSolution.add(ingredient);
            }
        }
        System.out.println((new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients());
        return currentSolution;
    }

    public IngredientList getBetterSolution() {
        for (int i = 0; i < 100; i++) {
            getFavoritChild();
        }
        return this.currentSolution;
    }

    public IngredientList getFavoritChild() {
        List<IngredientList> childs = new ArrayList<IngredientList>();
        for (int i = 0; i < 50; i++) {
            childs.add(this.getChild());
        }
        int currentSolutionCount = (new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients();
        int bestSolution = 0;
        IngredientList favoriteChild = new IngredientList();
        for (IngredientList child : childs) {

            int childSolution = (new IngredientSelection(child, this.clients)).getNumberOfSatisfiedClients();
            if (childSolution > bestSolution) {
                bestSolution = childSolution;
                favoriteChild = child;
            }
        }
        if (bestSolution > (new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients()) {
            allTimeFavoritChild = favoriteChild;
        }
        System.out.println(bestSolution);
        System.out.println("size" + favoriteChild.size());
        this.currentSolution = favoriteChild;
        return favoriteChild;
    }

    public IngredientList getChild() {
        //IngredientList unUsedIngredients = this.getCurrentlyUnusedIngredients();
        IngredientList modifiedSolution = new IngredientList(currentSolution);
        //System.out.println("Is so viel kaputt: " + Math.min(Math.random() * NEW_INGREDIENT_PER_CHILD, unUsedIngredients.size()) + " random number " + Math.random() * NEW_INGREDIENT_PER_CHILD + " unused ingredients" +unUsedIngredients.size() );
        for (int i = 0; i < Math.random() * NEW_INGREDIENT_PER_CHILD; i++) {
            Ingredient randomIngredient = ingredients.get((int) (Math.random() * ingredients.size()));
            if (!modifiedSolution.contains(randomIngredient)) {
                modifiedSolution.add(randomIngredient);
            }
        }
        for (int i = 0; i < Math.min(Math.random() * NEW_INGREDIENT_PER_CHILD, modifiedSolution.size()); i++) {
            modifiedSolution.remove(modifiedSolution.get((int) (Math.random() * modifiedSolution.size())));
        }
        return modifiedSolution;
    }

    public IngredientList getCurrentlyUnusedIngredients() {
        unusedIngredients = new IngredientList(ingredients);
        for (Ingredient usedIngredient : currentSolution) {
            unusedIngredients.remove(usedIngredient);
        }
        return unusedIngredients;
    }
}
