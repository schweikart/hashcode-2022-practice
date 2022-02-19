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

    Algorithm(List<String> clients){
        this.clients = clients;
    }

    public List<String> initialSolution() {
        for(String ingredient: ingredients){
            if (ingredient.isDisliked == 0 || ingredient.isLiked() > ingredient.isDisliked() * LIKED_TO_DISLIKED_RATIO){
                currentSolution.add(ingredient);
            }
        }
        return currentSolution;
    }

    public List<String> evolutionarySolution() {

    }

    public List<String> getChild(){

    }

    public List<String> getCurrentlyUnusedIngredients(){
        unusedIngredients = ingredients;
        for(String usedIngredient : currentSolution) {
            unusedIngredients.remove(usedIngredient);
        }
        return unusedIngredients;
    }

}
