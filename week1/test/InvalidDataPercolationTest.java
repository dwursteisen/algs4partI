import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Created by david.wursteisen on 26/08/13.
 */
@RunWith(Parameterized.class)
public class InvalidDataPercolationTest {

    private final int i;
    private final int j;

    public InvalidDataPercolationTest(final int i, final int j) {
        this.i = i;
        this.j = j;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { -10, -20 }, { -20, 10 }, { 20, -10 }, { 101, 102 }, { 101, 50 }, { 60, 666 }, { 0, 1 } };
        return Arrays.asList(data);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_throw_indexoutofbound_exception_when_i_or_j_outside_the_range_with_open() {
        Percolation percolation = new Percolation(100);
        percolation.open(i, j);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_throw_indexoutofbound_exception_when_i_or_j_outside_the_range_with_isOpen() {
        Percolation percolation = new Percolation(100);
        percolation.isOpen(i, j);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void should_throw_indexoutofbound_exception_when_i_or_j_outside_the_range_with_isFull() {
        Percolation percolation = new Percolation(100);
        percolation.isFull(i, j);

    }

}
