package nz.ac.auckland.se281;

public class EasyJarvis implements Jarvis {

  // Initialise variables to be updated/used by Easy Jarvis
  private Strategy strategy;
  private int fingers;
  private int sum;

  @Override
  public void setStrategy() {
    this.strategy = new RandomStrategy();
  }

  @Override
  public void takeTurn() {

    // Initialise outputs from running the strategy
    int[] outputs = strategy.setStrategy();

    // Set random strategy for easy jarvis and update fingers/sum accordingly
    fingers = outputs[0];
    sum = outputs[1];

    // Display the outcome
    MessageCli.PRINT_INFO_HAND.printMessage("Jarvis", String.valueOf(fingers), String.valueOf(sum));
  }

  // Some basic getters to access relevant information from easy Jarvis
  @Override
  public int getJarvisFingers() {
    return fingers;
  }

  @Override
  public int getJarvisSum() {
    return sum;
  }
}
