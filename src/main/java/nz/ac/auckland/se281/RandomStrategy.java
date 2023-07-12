package nz.ac.auckland.se281;

public class RandomStrategy implements Strategy {

  // Create variabes to be updated during the average strategy execution
  private int fingers;
  private int sum;

  @Override
  public int[] setStrategy() {
    // Initialise an array to store the output fingers and sum in
    int[] out = new int[2];

    // Execute the random strategy using the provided Utils methods
    fingers = Utils.getRandomNumber(1, 5);
    sum = Utils.getRandomNumber(fingers + 1, fingers + 5);

    // Assign outputs
    out[0] = fingers;
    out[1] = sum;
    return out;
  }

}
