/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rqueue = new RandomizedQueue<>();
        int n = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            String ins = StdIn.readString();
            rqueue.enqueue(ins);
        }

        for (int i = 0; i < n; i++) {
            String ins = rqueue.dequeue();
            StdOut.println(ins);
        }
    }
}
