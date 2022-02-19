package forthehat.hashcode2022.practice;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Helper {
    public static <E>
    E getRandomElement(Set<? extends E> set)
    {

        Random random = new Random();

        // Generate a random number using nextInt
        // method of the Random class.
        int randomNumber = random.nextInt(set.size());

        Iterator<? extends E> iterator = set.iterator();

        int currentIndex = 0;
        E randomElement = null;

        // iterate the HashSet
        while (iterator.hasNext()) {

            randomElement = iterator.next();

            // if current index is equal to random number
            if (currentIndex == randomNumber)
                return randomElement;

            // increase the current index
            currentIndex++;
        }

        return randomElement;
    }
}
