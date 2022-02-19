package forthehat.hashcode2022.practice;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    private List<String> clients = new ArrayList<String>();
    private List<String> ingredients = new ArrayList<String>();
    private List<String> currentSolution = new ArrayList<String>();
    private List<String> unusedIngredients = new ArrayList<String>();
    private static final int LIKED_TO_DISLIKED_RATIO = 5;
    private static final int NEW_INGREDIENT_PER_CHILD = 5;

    Algorithm(List<String> clients) {
        this.clients = clients;
    }

    public List<String> initialSolution() {
        for (String ingredient : ingredients) {
            if (ingredient.isDisliked == 0 || ingredient.isLiked() > ingredient.isDisliked() * LIKED_TO_DISLIKED_RATIO) {
                currentSolution.add(ingredient);
            }
        }
        return currentSolution;
    }

    public List<String> getBetterSolution() {
        for (int i =0; i <1000; i++){
            getFavoritChild();
        }
        return this.currentSolution;
    }

    public List<String> getFavoritChild() {
        List<List<String>> childs = null;
        for (int i = 0; i < 100; i++) {
            childs.add(this.getChild());
        }
        int bestSolution = 0;
        List<String> favoritChild = null;
        for(List<String> child : childs){
            int childSolution = Scoring.scoring(child, clients);
            if(childSolution > bestSolution){
                bestSolution = childSolution;
                favoritChild = child;
            }
        }
        System.out.println(bestSolution);
        return favoritChild;
    }

    public List<String> getChild() {
        List<String> unUsedIngredients = this.getCurrentlyUnusedIngredients();
        for (int i = 0; i < Math.min(Math.random() * NEW_INGREDIENT_PER_CHILD, unUsedIngredients.size()); i++) {
            String ingredient = unUsedIngredients.get((int) Math.random() * unUsedIngredients.size());
            currentSolution.add(unUsedIngredients.get((int) Math.random() * unUsedIngredients.size()));
            unUsedIngredients.remove(ingredient);
        }
        for (int i = 0; i < Math.min(Math.random() * NEW_INGREDIENT_PER_CHILD, currentSolution.size()); i++) {
            currentSolution.remove(currentSolution.get((int) Math.random() * currentSolution.size()));
        }
    }

    public List<String> getCurrentlyUnusedIngredients() {
        unusedIngredients = ingredients;
        for (String usedIngredient : currentSolution) {
            unusedIngredients.remove(usedIngredient);
        }
        return unusedIngredients;
    }
}
