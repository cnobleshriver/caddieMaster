package app;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.IOException;
import priorityqueue.*;

public class AppMain {

    public static void main(String[] args) {

        String fileName = "test_input.txt";
        CaddieSheetParser caddieSheetParser = new CaddieSheetParser();
        try {
            caddieSheetParser.readCaddieSheet(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        ArrayList<Caddie> caddies = caddieSheetParser.getCaddies();

        Comparator<Caddie> comparator = new daysSatComparator();
        // Swap in the RankComparator to prioritize on rank rather than condition.
        //Comparator<Caddie> comparator = new RankComparator();

        // Change priority from max to min.
        boolean isMaxHeap = true;

        PriorityQueueADT<Caddie> queue = new Heap<Caddie>(comparator, isMaxHeap);
        for (Caddie c : caddies) {
            queue.enqueueElement(c);
        }
        System.out.println("\n");
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeueElement());
        }
        System.out.println("\n");
    }
}
