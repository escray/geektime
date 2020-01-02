import java.util.ArrayList;
import java.util.Arrays;
public class L07Password {
  public static void callLetterList(ArrayList<String> l, ArrayList<String> result) {
    if (result.size() == 4) {
      System.out.println(result);
      return;
    }

    for (String letter : l ) {
      ArrayList<String> newResult = (ArrayList<String>) result.clone();
      newResult.add(letter);
      callLetterList(l, newResult);
    }
  }

  public static void main(String[] args) {
    ArrayList<String> l = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
    callLetterList(l, new ArrayList<>());
  }
}
