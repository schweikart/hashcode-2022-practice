package forthehat.hashcode2022.practice;

import java.util.List;

public class Scoring {

    public static int scoring(List<String> pizza ,List<String> clients) {
        int score = 0;
        for(String client : clients) {
            for(String dislikedIngredient : client.getDislikedIngredients()) {
                if(pizza.contains(dislikedIngredient)) {
                    break;
                }
            }
            for(String likedIngredient : client.getLikedIngredients()) {
                if(!pizza.contains(likedIngredient)) {
                    break;
                }
            }
            score++;
        }
        return score;
    }
}
