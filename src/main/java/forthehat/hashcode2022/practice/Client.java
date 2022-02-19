package forthehat.hashcode2022.practice;

import java.util.List;

public record Client(List<Ingredient> likedIngredients, List<Ingredient> dislikedIngredients) {
}
