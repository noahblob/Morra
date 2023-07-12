package nz.ac.auckland.se281;

import java.util.ArrayList;

public class JarvisFactory {

  public static Jarvis createJarvis(int diff, ArrayList<Integer> prevFingers) {

    // Return the correct difficulty jarvis based on the input integer diff, and
    // previous Fingers if required for strategies like average and top
    switch (diff) {
      case 1:
        return new EasyJarvis();
      case 2:
        return new MediumJarvis(prevFingers);
      case 3:
        return new HardJarvis(prevFingers);
      case 4:
        return new MasterJarvis(prevFingers);
    }
    // In the case a correct difficulty is not specified, which is not possible
    // anyway
    return null;
  }
}
