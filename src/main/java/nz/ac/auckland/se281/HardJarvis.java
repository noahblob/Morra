package nz.ac.auckland.se281;

import java.util.ArrayList;

public class HardJarvis implements Jarvis {

  // Initialise variables to be updated/used by Hard Jarvis
  private Strategy strategy;
  private ArrayList<Integer> prevFingers;
  private int fingers;
  private int sum;
  private int round;

  public HardJarvis(ArrayList<Integer> prevFingers) {
    // Set round number and initialise prevFingers array
    round = prevFingers.size();
    this.prevFingers = prevFingers;
  }

  @Override
  public void setStrategy() {
    // Set the Jarvis strategy based on round number
    if (round <= 3) {
      this.strategy = new RandomStrategy();
    } else {
      this.strategy = new TopStrategy(prevFingers);
    }
  }

  @Override
  public void takeTurn() {
    // Get output fingers and sum from strategy to access later
    int[] outputs = strategy.setStrategy();
    fingers = outputs[0];
    sum = outputs[1];

    // Display the outcome
    MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", String.valueOf(fingers), String.valueOf(sum));
  }

  // Some basic getters to access relevant information from hard Jarvis
  @Override
  public int getJarvisFingers() {
    return fingers;
  }

  @Override
  public int getJarvisSum() {
    return sum;
  }
}
