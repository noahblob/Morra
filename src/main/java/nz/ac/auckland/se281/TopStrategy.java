package nz.ac.auckland.se281;

import java.util.ArrayList;

public class TopStrategy implements Strategy {

  // Create relevant variables to be updated during the top strategy execution
  private int fingers;
  private int sum;
  private int top;
  private int cntCommon = 0;
  private ArrayList<Integer> prevFingers;

  public TopStrategy(ArrayList<Integer> prevFingers) {
    // Initialize prevFingers array to find top value from
    this.prevFingers = prevFingers;
  }

  @Override
  public int[] setStrategy() {
    // Initialise an array to store the output fingers and sum in
    int[] out = new int[2];

    fingers = Utils.getRandomNumber(1, 5);
    // Calculate the most common element in the human's previous moves from the
    // prevFingers arraylist
    for (int i = 0; i < prevFingers.size(); i++) {
      int count = 0;
      for (int j = i + 1; j < prevFingers.size(); j++) {
        if (prevFingers.get(i) == prevFingers.get(j)) {
          count++;
        }
      }
      // Assign the top variable
      if (count > cntCommon) {
        cntCommon = count;
        top = prevFingers.get(i);
      }
    }
    // Calculate sum
    sum = fingers + top;

    // Assign outputs
    out[0] = fingers;
    out[1] = sum;
    return out;
  }
}
