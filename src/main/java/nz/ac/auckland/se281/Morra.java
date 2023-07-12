package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Arrays;

import nz.ac.auckland.se281.Main.Difficulty;

public class Morra {

  // Create relevant variables to be used throughout code
  private int roundCount;
  private int winningPoints;
  private int humanPoints;
  private int aiPoints;
  private int diff;
  private boolean game = false;
  private ArrayList<Integer> prevFingers;
  private String playerName;
  private Jarvis jarvis;

  public Morra() {
  }

  public void newGame(Difficulty difficulty, int pointsToWin, String[] options) {
    // Welcome the player
    MessageCli.WELCOME_PLAYER.printMessage(options[0]);

    // Initialise variables to required values for a new game
    roundCount = 1;
    winningPoints = pointsToWin;
    humanPoints = 0;
    aiPoints = 0;
    playerName = options[0];
    game = true;
    prevFingers = new ArrayList<Integer>();

    // Set difficulty so we can use it elsewhere
    switch (difficulty) {
      case EASY:
        diff = 1;
        break;
      case MEDIUM:
        diff = 2;
        break;
      case HARD:
        diff = 3;
        break;
      case MASTER:
        diff = 4;
        break;
    }

  }

  public void play() {

    // Check if a new game has begun
    if (!game) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    // Update round count and prompt player to input their first move
    MessageCli.START_ROUND.printMessage(String.valueOf(roundCount));
    roundCount++;
    MessageCli.ASK_INPUT.printMessage();
    String input = Utils.scanner.nextLine();

    // Check if the input is valid
    boolean checkValidity = inputValid(input);

    // Continuously check if input fingers and sum is valid until the user has made
    // a valid input
    while (!checkValidity) {
      MessageCli.INVALID_INPUT.printMessage();
      MessageCli.ASK_INPUT.printMessage();
      input = Utils.scanner.nextLine();
      checkValidity = inputValid(input);
    }

    // Once input is valid, assign the user fingers and sum
    String[] inputArr = input.split(" ");
    int[] inputNums = Arrays.stream(inputArr).mapToInt(Integer::parseInt).toArray();
    int fingers = inputNums[0];
    int sum = inputNums[1];

    // Update previous fingers array list with current rounds fingers
    prevFingers.add(fingers);

    // Print player info
    MessageCli.PRINT_INFO_HAND.printMessage(playerName, inputArr[0], inputArr[1]);

    // Initialise AI player Jarvis and take it's turn
    jarvis = JarvisFactory.createJarvis(diff, prevFingers);
    jarvis.setStrategy();
    jarvis.takeTurn();
    int jarvisFingers = jarvis.getJarvisFingers();
    int jarvisSum = jarvis.getJarvisSum();

    // Check and print result of the round
    if ((sum == (jarvisFingers + fingers)) && (jarvisSum == (jarvisFingers + fingers))
        || !(sum == (jarvisFingers + fingers)) && !(jarvisSum == (jarvisFingers + fingers))) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("DRAW");
    } else if (sum == (jarvisFingers + fingers) && jarvisSum != (jarvisFingers + fingers)) {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("HUMAN_WINS");
      humanPoints++;
    } else {
      MessageCli.PRINT_OUTCOME_ROUND.printMessage("AI_WINS");
      aiPoints++;
    }

    // Check if a player has won the game
    if (humanPoints == winningPoints) {
      MessageCli.END_GAME.printMessage(playerName, String.valueOf(roundCount - 1));
      game = false;
    } else if (aiPoints == winningPoints) {
      MessageCli.END_GAME.printMessage("Jarvis", String.valueOf(roundCount - 1));
      game = false;
    }

  }

  public void showStats() {
    // If a game has not started, showStats cannot be executed
    if (!game) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    // Show the stats of both players
    MessageCli.PRINT_PLAYER_WINS.printMessage(playerName, String.valueOf(humanPoints),
        String.valueOf(winningPoints - humanPoints));
    MessageCli.PRINT_PLAYER_WINS.printMessage("Jarvis", String.valueOf(aiPoints),
        String.valueOf(winningPoints - aiPoints));

  }

  // This method checks if the user input is valid (i.e. exactly two positive
  // integers within the specified range)
  public boolean inputValid(String input) {
    String[] inputArr = input.split(" ");

    // Check if only one or less inputs was provided
    if (inputArr.length != 2) {
      return false;
    }

    // Check if input is not an integer
    try {
      Integer.parseInt(inputArr[0]);
      Integer.parseInt(inputArr[1]);
    } catch (NumberFormatException e) {
      return false;
    }

    // In the case it is an integer, assign these values
    int fingers = Integer.parseInt(inputArr[0]);
    int sum = Integer.parseInt(inputArr[1]);

    // Check if values are within bounds
    if (!(fingers >= 1 && fingers <= 5) && !(sum >= 1 && sum <= 10)
        || (!(fingers >= 1 && fingers <= 5) || !(sum >= 1 && sum <= 10))) {
      return false;
    }

    return true;
  }

}
