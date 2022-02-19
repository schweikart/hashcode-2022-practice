package forthehat.hashcode2022.practice;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    private List<String> clients = new ArrayList<String>();
    private List<String> ingredients = new ArrayList<String>();
    private List<String> currentSolution = new ArrayList<String>();

    Algorithm(List<String> clients){
        this.clients = clients;
    }

    public List<String> initialSolution() {
        for(String ingredient: ingredients){
            if (ingredient.isDisliked == 0 || ingredient.isLiked() > ingredient.isDisliked() * 5){
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

    }

}
