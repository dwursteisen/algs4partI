import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by david.wursteisen on 26/08/13.
 */
public class Percolation {
    public static final int INVALID_INDEX = -1;

    private final int gridSize;
    private final boolean[] sites;
    private final WeightedQuickUnionUF engine;
    private final int numberOfSites;

    public Percolation(final int N) {
        this.gridSize = N;
        this.numberOfSites = (gridSize * gridSize) + 2;
        this.sites = new boolean[numberOfSites];
        this.sites[0] = true; // top site
        this.sites[numberOfSites - 1] = true; // bottom site
        this.engine = new WeightedQuickUnionUF(numberOfSites);
    }

    public void open(int i, int j) {
        checkCoordinatesAreValid(i, j);
        sites[getGroupsIndex(i, j)] = true;
        List<Integer> neightBoors = getNeightBoors(i, j);
        for (Integer neightBoor : neightBoors) {
            if (this.sites[neightBoor]) {
                engine.union(getGroupsIndex(i, j), neightBoor);
            }
        }
    }

    public boolean isOpen(int i, int j) {
        checkCoordinatesAreValid(i, j);
        return sites[getGroupsIndex(i, j)];
    }

    public boolean isFull(int i, int j) {
        checkCoordinatesAreValid(i, j);
        return engine.connected(0, getGroupsIndex(i, j));
    }

    public boolean percolates() {
        return engine.connected(0, numberOfSites - 1);
    }

    private void checkCoordinatesAreValid(final int i, final int j) {
        if (!isValidIndex(i) || !isValidIndex(j)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isValidIndex(final int index) {
        return (index > 0) && (index <= gridSize);
    }

    int getGroupsIndex(final int i, final int j) {
        if (i == 0) {
            return 0;
        } else if (i == gridSize + 1) {
            return (gridSize * gridSize) + 1;
        }

        if (!isValidIndex(i) || !(isValidIndex(j))) {
            return INVALID_INDEX;
        }
        return i + gridSize * (j - 1);
    }

    public List<Integer> getNeightBoors(final int i, final int j) {
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
        set.add(getGroupsIndex(i - 1, j));

        set.add(getGroupsIndex(i, j - 1));
        set.add(getGroupsIndex(i, j + 1));

        set.add(getGroupsIndex(i + 1, j));

        set.remove(INVALID_INDEX);
        return new ArrayList<Integer>(set);

    }
}
