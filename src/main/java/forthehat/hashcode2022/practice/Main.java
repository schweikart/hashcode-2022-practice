package forthehat.hashcode2022.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Objects;

public class Main {
  public static void main(String[] args) throws IOException {
    System.out.println("FOR THE HAT!");

    Iterator<String> lineIteratorA = getLineIteratorForResource("a_an_example.in.txt");
    Problem problemA = new ProblemParser(lineIteratorA).parseProblem();

    Iterator<String> lineIteratorB = getLineIteratorForResource("b_basic.in.txt");
    Problem problemB = new ProblemParser(lineIteratorB).parseProblem();
  }

  private static Iterator<String> getLineIteratorForResource(String resourceName) {
    InputStream inputStream = Main.class.getResourceAsStream(resourceName);
    Objects.requireNonNull(inputStream);

    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    return reader.lines().iterator();
  }
}
