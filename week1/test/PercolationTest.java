import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by david.wursteisen on 26/08/13.
 */
public class PercolationTest {

    private Percolation percolation;

    @Before
    public void setUp() throws Exception {
        percolation = new Percolation(5);
    }

    @Test
    public void should_be_open_or_not() {
        percolation.open(1, 1);
        percolation.open(3, 2);
        assertTrue(percolation.isOpen(1, 1));
        assertTrue(percolation.isOpen(3, 2));

        assertFalse(percolation.isOpen(2, 1));
        assertFalse(percolation.isOpen(4, 3));

    }

//    @Test
//    public void should_get_groups_index() {
//        assertEquals(1, percolation.getGroupsIndex(1, 1));
//        assertEquals(2, percolation.getGroupsIndex(2, 1));
//        assertEquals(6, percolation.getGroupsIndex(1, 2));
//        assertEquals(7, percolation.getGroupsIndex(2, 2));
//    }
//
//    @Test
//    public void should_get_groups_index_for_top_and_bottom_row() {
//        assertEquals(0, percolation.getGroupsIndex(0, 0));
//        assertEquals(0, percolation.getGroupsIndex(0, 1));
//        assertEquals(0, percolation.getGroupsIndex(0, 2));
//        assertEquals(-1, percolation.getGroupsIndex(6, 0));
//        assertEquals((5 * 5) + 1, percolation.getGroupsIndex(6, 1));
//        assertEquals((5 * 5) + 2, percolation.getGroupsIndex(6, 2));
//        assertEquals((5 * 5) + 3, percolation.getGroupsIndex(6, 3));
//    }

    @Test
    public void should_be_full() {
        percolation.open(1, 1);
        percolation.open(3, 2);

        assertTrue(percolation.isFull(1, 1));
        assertFalse(percolation.isFull(1, 2));
        assertFalse(percolation.isFull(3, 2));
    }

//    @Test
//    public void should_get_neightboors() {
//        int[] neightBoors = percolation.getNeightBoors(1, 1);
//        assertThat(neightBoors).containsOnly(0, -1, 2, 6);
//    }

    @Test
    public void should_percolate() {
        percolation.open(1, 1);
        assertFalse(percolation.percolates());
        percolation.open(2, 1);
        assertFalse(percolation.percolates());
        percolation.open(3, 1);
        assertFalse(percolation.percolates());
        percolation.open(4, 1);
        assertFalse(percolation.percolates());
        percolation.open(5, 1);
        assertTrue(percolation.percolates());
    }

    @Test
    public void should_manage_backwash_percolate() {
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1);
        percolation.open(5, 1);
        assertTrue(percolation.percolates());
        percolation.open(5, 5);
        assertFalse(percolation.isFull(5, 5));
        percolation.open(5, 4);
        assertFalse(percolation.isFull(5, 5));
        assertFalse(percolation.isFull(5, 4));

        percolation.open(5, 3);
        percolation.open(5, 2);
        percolation.open(5, 1);
        assertTrue(percolation.isFull(5, 4));
        assertTrue(percolation.isFull(5, 5));
    }
}
