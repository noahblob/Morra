package nz.ac.auckland.se281;

import java.util.ArrayList;

public class MediumJarvis implements Jarvis {

  // Initialise variables to be updated/used by Medium Jarvis
  private Strategy strategy;
  private int fingers;
  private int sum;
  private int round;
  private int totalHumanFingers;

  public MediumJarvis(ArrayList<Integer> prevFingers) {

    // Calculate total fingers played in all rounds thus far
    for (int i = 0; i < prevFingers.size() - 1; i++) {
      totalHumanFingers += prevFingers.get(i);
    }

    // Set current round
    round = prevFingers.size();
  }

  @Override
  public void setStrategy() {
    // Set Jarvis strategy based on round number
    if (round <= 3) {
      this.strategy = new RandomStrategy();
    } else {
      this.strategy = new AverageStrategy(round, totalHumanFingers);
    }
  }

  @Override
  public void takeTurn() {
    // Set fingers and sum based on which strategy is chosen during the round
    int[] outputs = strategy.setStrategy();
    fingers = outputs[0];
    sum = outputs[1];

    // Display the outcome
    MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", String.valueOf(fingers), String.valueOf(sum));
  }

  // Some basic getters to access relevant information from medium Jarvis
  @Override
  public int getJarvisFingers() {
    return fingers;
  }

  @Override
  public int getJarvisSum() {
    return sum;
  }
}
