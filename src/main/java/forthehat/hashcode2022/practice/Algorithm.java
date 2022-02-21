package forthehat.hashcode2022.practice;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    private List<Client> clients = new ArrayList<Client>();
    private IngredientList ingredients = new IngredientList();
    private IngredientList usableIngredients = new IngredientList();
    private IngredientList currentSolution = new IngredientList();
    private IngredientList unusedIngredients = new IngredientList();
    private IngredientList allTimeFavoritChild = new IngredientList();
    private static final int LIKED_TO_DISLIKED_RATIO = 1;
    private int NEW_INGREDIENT_PER_CHILD = 2;

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
        for (Ingredient ingredient : ingredients) {
            if (!(ingredient.getClientsWhoDislikeCount() == 0)) {
                usableIngredients.add(ingredient);
            }
        }
        System.out.println(usableIngredients.size());
    }

    public IngredientList initialSolution() {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getClientsWhoDislikeCount() == 0 || ingredient.getClientsWhoAcceptCount() > ingredient.getClientsWhoDislikeCount() * LIKED_TO_DISLIKED_RATIO) {
                currentSolution.add(ingredient);
            }
        }
        this.allTimeFavoritChild = new IngredientList(currentSolution);
        System.out.println((new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients());
        return currentSolution;
    }

    public IngredientList getBetterSolution() {
        for (int j = 50; j > 0; j--) {
            this.NEW_INGREDIENT_PER_CHILD = j;
            if (j > 20) {
                for (int i = 0; i < 100; i++) {
                    //getFavoritChild();
                    getFavoritChildChinaVersion();
                    System.out.println("Current Score: " + (new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients());
                    System.out.println(i + " " + j);
                }
            } else if (j > 10) {
                for (int i = 0; i < 4000; i++) {
                    //getFavoritChild();
                    getFavoritChildChinaVersion();
                    System.out.println("Current Score: " + (new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients());
                    System.out.println(i + " " + j);
                }
            } else if (j > 3) {
                for (int i = 0; i < 40000; i++) {
                    //getFavoritChild();
                    getFavoritChildChinaVersion();
                    System.out.println("Current Score: " + (new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients());
                    System.out.println(i + " " + j);
                }
            } else {
                for (int i = 0; i < 300000; i++) {
                    //getFavoritChild();
                    getFavoritChildChinaVersion();
                    System.out.println("Current Score: " + (new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients());
                    System.out.println(i + " " + j);
                }
            }
        }
        //System.out.println("hat size: " +(new IngredientSelection(allTimeFavoritChild, this.clients)).getNumberOfSatisfiedClients());
        System.out.println("hat size: " + (new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients());
        //return this.allTimeFavoritChild;
        return this.currentSolution;

    }

    public IngredientList getFavoritChild() {
        List<IngredientList> childs = new ArrayList<IngredientList>();
        for (int i = 0; i < 100; i++) {
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
        if (bestSolution > (new IngredientSelection(allTimeFavoritChild, this.clients)).getNumberOfSatisfiedClients()) {
            allTimeFavoritChild = favoriteChild;
        }
        System.out.println(bestSolution);
        // System.out.println("size" + favoriteChild.size());
        this.currentSolution = favoriteChild;
        return favoriteChild;
    }

    //less children
    public IngredientList getFavoritChildChinaVersion() {
        List<IngredientList> childs = new ArrayList<IngredientList>();
        for (int i = 0; i < 1; i++) {
            childs.add(this.getChild());
        }
        int currentSolutionCount = (new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients();
        int bestSolution = 0;
        IngredientList favoriteChild = new IngredientList();
        for (IngredientList child : childs) {

            int childSolution = (new IngredientSelection(child, this.clients)).getNumberOfSatisfiedClients();
            if (childSolution >= currentSolutionCount && childSolution > bestSolution) {
                bestSolution = childSolution;
                favoriteChild = child;
                this.currentSolution = favoriteChild;
            }
        }
        if (bestSolution >= (new IngredientSelection(currentSolution, this.clients)).getNumberOfSatisfiedClients()) {
            allTimeFavoritChild = favoriteChild;
        }
        //System.out.println(bestSolution);
        //System.out.println("size" + favoriteChild.size());

        return favoriteChild;
    }

    public IngredientList getChild() {
        //IngredientList unUsedIngredients = this.getCurrentlyUnusedIngredients();
        IngredientList modifiedSolution = new IngredientList(currentSolution);
        //System.out.println("Is so viel kaputt: " + Math.min(Math.random() * NEW_INGREDIENT_PER_CHILD, unUsedIngredients.size()) + " random number " + Math.random() * NEW_INGREDIENT_PER_CHILD + " unused ingredients" +unUsedIngredients.size() );
        boolean didSomething = false;

        if (Math.random() < 0.5) {
            for (int i = 0; i < NEW_INGREDIENT_PER_CHILD; i++) {
                if (usableIngredients.size() != 0) {
                    Ingredient randomIngredient = Helper.getRandomElement(usableIngredients);
                    modifiedSolution.add(randomIngredient);
                    didSomething = true;
                }
            }
        }
        if (Math.random() < 0.5 || !didSomething) {
            for (int i = 0; i < Math.min(NEW_INGREDIENT_PER_CHILD, modifiedSolution.size()); i++) {
                Ingredient randomIngredient = Helper.getRandomElement(modifiedSolution);
                if (usableIngredients.contains(randomIngredient)) {
                    modifiedSolution.remove(randomIngredient);
                }
            }
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
