import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Created by david.wursteisen on 26/08/13.
 */
@RunWith(Parameterized.class)
public class InvalidDataPercolationStatsTest {

    private final int N;
    private final int T;

    public InvalidDataPercolationStatsTest(final int N, final int T) {
        this.N = N;
        this.T = T;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { -23, 42 }, { 23, 0 }, { -42, 0 } };
        return Arrays.asList(data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_indexoutofbound_exception_when_i_or_j_outside_the_range_with_open() {
        PercolationStats percolationStats = new PercolationStats(N, T);

    }

}
