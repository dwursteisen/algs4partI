import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by david.wursteisen on 13/09/13.
 */
public class PointTest {
    @Test
    public void should_be_equal() {
        Point point = new Point(0, 0);
        assertTrue(0 == point.compareTo(point));
    }

    @Test
    public void should_be_less_by_y() {
        assertTrue(-1 == new Point(0, 0).compareTo(new Point(0, 10)));
    }

    @Test
    public void should_be_less_by_x() {
        assertTrue(-1 == new Point(0, 0).compareTo(new Point(10, 0)));
    }

    @Test
    public void should_be_less_by_both() {
        assertTrue(-1 == new Point(0, 0).compareTo(new Point(10, 10)));
    }

    @Test
    public void should_be_less_by_x_and_more_by_y() {
        assertTrue(1 == new Point(0, 10).compareTo(new Point(10, 0)));
    }

    @Test
    public void should_slope_horizontal_line() {
        assertEquals(0, new Point(0, 0).slopeTo(new Point(10, 0)), 0);
    }

    @Test
    public void should_slope_vertical_line() {
        assertEquals(Double.POSITIVE_INFINITY, new Point(0, 0).slopeTo(new Point(0, 10)), 0);
    }

    @Test
    public void should_slope_degenerate_line() {
        assertEquals(Double.NEGATIVE_INFINITY, new Point(0, 0).slopeTo(new Point(0, 0)), 0);
    }

    @Test
    public void should_slope() {
        assertEquals(0.75, new Point(10, 25).slopeTo(new Point(30, 40)), 0.0005);
    }

    @Test
    public void should_slope_order_horizontal_line() {
        assertEquals(-1, new Point(0, 0).SLOPE_ORDER.compare(new Point(10, 0), new Point(20, 20)), 0.00005);
    }

    @Test
    public void should_slope_order_vertical_line() {
        assertEquals(1, new Point(0, 0).SLOPE_ORDER.compare(new Point(0, 10), new Point(20, 20)), 0.00005);
    }

    @Test
    public void should_slope_order_degenerate_line() {
        assertEquals(-1, new Point(0, 0).SLOPE_ORDER.compare(new Point(0, 0), new Point(20, 20)), 0.00005);
    }

    @Test
    public void should_slope_order() {
        assertEquals(-1, new Point(10, 25).SLOPE_ORDER.compare(new Point(30, 40), new Point(50, 60)), 0.00005);
    }
}
