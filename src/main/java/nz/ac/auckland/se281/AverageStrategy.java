package nz.ac.auckland.se281;

public class AverageStrategy implements Strategy {

  // Create variabes to be updated during the average strategy execution
  private int fingers;
  private int sum;
  private float numFingers;
  private float roundCount;

  public AverageStrategy(int round, int fingers) {
    // Initialize values to be used to calculate average
    numFingers = fingers;
    roundCount = round;
  }

  @Override
  public int[] setStrategy() {
    // Initialise an array to store the output fingers and sum in
    int[] out = new int[2];

    // Calculate fingers randomly and sum based on average value of fingers played
    // by user
    fingers = Utils.getRandomNumber(1, 5);
    numFingers = numFingers / (roundCount - 1);
    int avg = Math.round(numFingers);
    sum = fingers + avg;

    // Assign outputs
    out[0] = fingers;
    out[1] = sum;
    return out;
  }
}
