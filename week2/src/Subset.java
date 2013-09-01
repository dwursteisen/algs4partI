/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 02/09/13
 * Time: 00:02
 * To change this template use File | Settings | File Templates.
 */
public class Subset {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        for (String str : StdIn.readStrings()) {
            queue.enqueue(str);
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }

    }
}
