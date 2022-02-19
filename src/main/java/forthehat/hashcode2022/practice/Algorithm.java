package forthehat.hashcode2022.practice;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    private List<Client> clients = new ArrayList<Client>();
    private IngredientList ingredients = new IngredientList();
    private IngredientList currentSolution = new IngredientList();
    private IngredientList unusedIngredients = new IngredientList();
    private static final int LIKED_TO_DISLIKED_RATIO = 1;
    private static final int NEW_INGREDIENT_PER_CHILD = 5;

    Algorithm(Problem problem) {
        this.clients = problem.clients();
        this.ingredients = problem.ingredients();
    }

    public IngredientList initialSolution2() {
        for (Ingredient ingredient : ingredients) {
            if (Math.random() < 0.5) {
                currentSolution.add(ingredient);
            }
        }
        System.out.println((new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients());
        return currentSolution;
    }

    public IngredientList initialSolution() {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getClientsWhoDislikeCount() == 0 /*|| ingredient.getClientsWhoAcceptCount() > ingredient.getClientsWhoDislikeCount() * LIKED_TO_DISLIKED_RATIO*/) {
                currentSolution.add(ingredient);
            }
        }
        System.out.println((new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients());
        return currentSolution;
    }

    public IngredientList getBetterSolution() {
        for (int i = 0; i < 1000; i++) {
            getFavoritChild();
        }
        return this.currentSolution;
    }

    public IngredientList getFavoritChild() {
        List<IngredientList> childs = new ArrayList<IngredientList>();
        for (int i = 0; i < 100; i++) {
            childs.add(this.getChild());
        }
        int bestSolution = 0;
        IngredientList favoriteChild = new IngredientList();
        for (IngredientList child : childs) {
            int childSolution =  (new IngredientSelection(child, this.clients)).getNumberOfSatisfiedClients();
            if (childSolution > bestSolution) {
                bestSolution = childSolution;
                favoriteChild = child;
            }
        }
        System.out.println(bestSolution);
        return favoriteChild;
    }

    public IngredientList getChild() {
        IngredientList unUsedIngredients = this.getCurrentlyUnusedIngredients();
        for (int i = 0; i < Math.min(Math.random() * NEW_INGREDIENT_PER_CHILD, unUsedIngredients.size()); i++) {
            Ingredient ingredient = unUsedIngredients.get((int) (Math.random() * unUsedIngredients.size()));
            currentSolution.add(ingredient);
            unUsedIngredients.remove(ingredient);
        }
        for (int i = 0; i < Math.min(Math.random() * NEW_INGREDIENT_PER_CHILD, currentSolution.size()); i++) {
            currentSolution.remove(currentSolution.get((int) (Math.random() * currentSolution.size())));
        }
        return this.currentSolution;
    }

    public IngredientList getCurrentlyUnusedIngredients() {
        unusedIngredients = ingredients;
        for (Ingredient usedIngredient : currentSolution) {
            unusedIngredients.remove(usedIngredient);
        }
        return unusedIngredients;
    }
}
