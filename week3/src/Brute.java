import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by david.wursteisen on 13/09/13.
 */
public class Brute {

    private final List<Point> points = new ArrayList<Point>();
    private final String filename;

    public Brute() {
        this.filename = null;
    }

    private Brute(final String filename) {
        this.filename = filename;
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
    }

    public static void main(String[] args) {
        new Brute(args[0]).read().andPrint();
    }

    private Brute read() {
        points.addAll(readInputFile(filename));
        return this;
    }

    private Brute andPrint() {

        int N = points.size();
        List<Point> pointsSloped = new ArrayList<Point>(4);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double slope1 = points.get(i).slopeTo(points.get(j));
                for (int k = j + 1; k < N; k++) {
                    double slope2 = points.get(i).slopeTo(points.get(k));
                    if (slope1 != slope2) {
                        continue;
                    }
                    for (int l = k + 1; l < N; l++) {
                        double slope3 = points.get(i).slopeTo(points.get(l));
                        if (slope1 != slope3) {
                            continue;
                        }

                        pointsSloped.addAll(Arrays.asList(points.get(i), points.get(j), points.get(k), points.get(l)));
                        Collections.sort(pointsSloped);
                        StdOut.println(String.format("%s -> %s -> %s -> %s", pointsSloped.toArray()));
                        points.get(i).drawTo(points.get(j));
                        points.get(j).drawTo(points.get(k));
                        points.get(k).drawTo(points.get(l));
                        pointsSloped.clear();

                    }
                }
            }

        }
        return this;
    }

    private List<Point> readInputFile(final String filename) {
        // read in the input
        In in = new In(filename);
        int N = in.readInt();
        List<Point> points = new ArrayList<Point>(N);
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            p.draw();
            points.add(p);
        }
        Collections.sort(points);
        return points;
    }

}
