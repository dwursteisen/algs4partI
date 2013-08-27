import java.util.Locale;

/**
 * Created by david.wursteisen on 27/08/13.
 */
public class PercolationStats {

    public static final double CONFIDENCE_INTERVAL = 1.96;
    private final double[] thresholds;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        thresholds = new double[T];
        for (int i = 0; i < T; i++) {
            thresholds[i] = doSimulation(N);
        }
    }

    // test client, described below
    public static void main(String[] args) {
        int N = 10;
        if (args.length >= 1) {
            N = Integer.parseInt(args[0]);
        }
        int T = 10;
        if (args.length >= 2) {
            T = Integer.parseInt(args[1]);
        }

        PercolationStats stats = new PercolationStats(N, T);
        // mean = 0.666925
        // stddev = 0.11776536521033558
        // 95% confidence interval = 0.6646167988418774, 0.6692332011581226
        StdOut.println(String.format(Locale.ENGLISH, // \n
                "mean                    = %f", // \n
                stats.mean())); // \n
        StdOut.println(String.format(Locale.ENGLISH, // \n
                "stddev                  = %f", // \n
                stats.stddev())); // \n
        StdOut.println(String.format(Locale.ENGLISH, // \n
                "95%% confidence interval = %f, %f", // \n
                stats.confidenceLo(), // \n
                stats.confidenceHi())); // \n

    }

    private double doSimulation(final int N) {
        Percolation percolation = new Percolation(N);
        int numberOfSiteOpenned = 0;
        do {
            int i = StdRandom.uniform(N) + 1;
            int j = StdRandom.uniform(N) + 1;
            if (percolation.isOpen(i, j)) { // already openned => NEXT !!
                continue;
            }
            percolation.open(i, j);
            numberOfSiteOpenned++;
        } while (!percolation.percolates());

        return numberOfSiteOpenned / (N * N * 1.0);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - // \n
                ((CONFIDENCE_INTERVAL * stddev()) / Math.sqrt(thresholds.length));
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + // \n
                ((CONFIDENCE_INTERVAL * stddev()) / Math.sqrt(thresholds.length));
    }
}
