package forthehat.hashcode2022.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Main {
  public static void main(String[] args) throws IOException {
    System.out.println("FOR THE HAT!");

   // solveProblem("a_an_example");
   // solveProblem("b_basic");
    //solveProblem("c_coarse");
   solveProblem("d_difficult");
    //solveProblem("e_elaborate");
  }

  private static void solveProblem(String problemName) {
    Iterator<String> lineIterator = getLineIteratorForResource(String.format("%s.in.txt", problemName));
    Problem problem = new ProblemParser(lineIterator).parseProblem();
    Algorithm algorithm = new Algorithm(problem);

    IngredientList ingredients = algorithm.initialSolution();
    ingredients = algorithm.getBetterSolution();

    IngredientSelection solution = new IngredientSelection(ingredients, problem.clients());
    SolutionWriter.writeSolution(Path.of(String.format("./out/%s.out.txt", problemName)), solution);
  }

  private static Iterator<String> getLineIteratorForResource(String resourceName) {
    InputStream inputStream = Main.class.getResourceAsStream(resourceName);
    Objects.requireNonNull(inputStream);

    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    return reader.lines().iterator();
  }
}
