package forthehat.hashcode2022.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class SolutionWriter {
  public static void writeSolution(Path path, IngredientSelection selection) {
    List<String> contents = new LinkedList<>();

    contents.add(String.valueOf(selection.ingredients().size()));

    for (var ingredient : selection.ingredients()) {
      contents.add(ingredient.name());
    }

    if (!Files.isDirectory(path.getParent())) {
      try {
        Files.createDirectory(path.getParent());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    try {
      Files.writeString(path, String.join(" ", contents));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
