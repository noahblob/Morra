package nz.ac.auckland.se281;

import java.util.ArrayList;

public class MasterJarvis implements Jarvis {

  // Initialise variables to be updated/used by MASTER Jarvis
  private Strategy strategy;
  private int fingers;
  private int sum;
  private int round;
  private int totalHumanFingers;
  private ArrayList<Integer> prevFingers;

  public MasterJarvis(ArrayList<Integer> prevFingers) {

    // Set the current round being played and initialise prevFingers
    round = prevFingers.size();
    this.prevFingers = prevFingers;

    // Calculate total fingers played in all rounds thus far
    for (int i = 0; i < prevFingers.size() - 1; i++) {
      totalHumanFingers += prevFingers.get(i);
    }
  }

  @Override
  public void setStrategy() {
    // Set strategy based on round number
    if (round <= 3) {
      strategy = new RandomStrategy();
    } else if (round % 2 == 0) {
      strategy = new AverageStrategy(round, totalHumanFingers);
    } else if (round % 2 == 1) {
      strategy = new TopStrategy(prevFingers);
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
