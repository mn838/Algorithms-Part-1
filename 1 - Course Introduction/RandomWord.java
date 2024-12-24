/* *****************************************************************************
 *  Name:              Mahi Nuthanapati
 *  Coursera User ID:  Mahi Nuthanapati
 *  Last modified:     December 23rd, 2024
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        int i = 1;
        while (!StdIn.isEmpty()) {
            String current = StdIn.readString();
            boolean currProb = StdRandom.bernoulli(1 / (i + 0.0));
            if (currProb) {
                champion = current;
            }
            i++;
        }

        StdOut.println(champion);
    }
}
