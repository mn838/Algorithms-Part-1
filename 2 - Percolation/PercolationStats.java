/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] vals;
    private int numTrials;

    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException("n should be greater than 0");
        if (trials <= 0) throw new IllegalArgumentException("trials should be greater than 0");
        vals = new double[trials];
        numTrials = trials;

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int pid = StdRandom.uniformInt(n) + 1;
                int qid = StdRandom.uniformInt(n) + 1;
                p.open(pid, qid);
            }
            vals[i] = ((double) p.numberOfOpenSites() / (n * n));
        }
    }

    public double mean() {
        return StdStats.mean(vals);
    }

    public double stddev() {
        return StdStats.stddev(vals);
    }

    public double confidenceLo() {
        return StdStats.mean(vals) - ((1.96 * StdStats.stddev(vals)) / Math.sqrt(numTrials));
    }

    public double confidenceHi() {
        return StdStats.mean(vals) + ((1.96 * StdStats.stddev(vals)) / Math.sqrt(numTrials));
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("number of arguments should be 2.");
        }
        int n = Integer.parseInt(args[0]);
        int numTrials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, numTrials);

        System.out.printf("mean                    = %.16f%n", ps.mean());
        System.out.printf("stddev                  = %.16f%n", ps.stddev());
        System.out.printf("95%% confidence interval = [%.16f, %.16f]%n", ps.confidenceLo(),
                          ps.confidenceHi());
    }
}
