import java.util.Arrays;

/**
 * Created by david.wursteisen on 26/08/13.
 */
public class Percolation {
    private static final int INVALID_INDEX = -1;
    private final int gridSize;
    private final boolean[] sites;
    private final WeightedQuickUnionUF engine;
    private final int numberOfSites;

    public Percolation(final int N) {
        this.gridSize = N;
        this.numberOfSites = (gridSize * gridSize) + 1 + gridSize;
        this.sites = new boolean[numberOfSites];
        this.sites[0] = true; // top site
        Arrays.fill(this.sites, gridSize * gridSize + 1, numberOfSites, true); // bottom site
        this.engine = new WeightedQuickUnionUF(numberOfSites);
    }

    public void open(int i, int j) {
        checkCoordinatesAreValid(i, j);
        sites[getGroupsIndex(i, j)] = true;
        int[] neightBoors = getNeightBoors(i, j);
        for (int neightBoor : neightBoors) {
            if (neightBoor == INVALID_INDEX) {
                continue;
            }
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
        boolean isPercolates = false;
        for (int index = 0; index < gridSize; index++) {
            isPercolates |= engine.connected(0, index + gridSize * gridSize + 1);
        }
        return isPercolates;
    }

    private void checkCoordinatesAreValid(final int i, final int j) {
        if (!isValidIndex(i) || !isValidIndex(j)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isValidIndex(final int index) {
        return (index > 0) && (index <= gridSize);
    }

    private int getGroupsIndex(final int i, final int j) {
        if (i == 0) {
            return 0;
        } else if (i == gridSize + 1 && isValidIndex(j)) {
            return (gridSize * gridSize) + j;
        }

        if (!isValidIndex(i) || !(isValidIndex(j))) {
            return INVALID_INDEX;
        }
        return i + gridSize * (j - 1);
    }

    private int[] getNeightBoors(final int i, final int j) {
        int[] neightboors = new int[4];
        neightboors[0] = getGroupsIndex(i - 1, j);
        neightboors[1] = getGroupsIndex(i, j - 1);
        neightboors[2] = getGroupsIndex(i, j + 1);
        neightboors[3] = getGroupsIndex(i + 1, j);
        return neightboors;

    }

}
