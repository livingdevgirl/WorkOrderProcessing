import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.jvm.hotspot.utilities.ObjectReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Processor {
    public void processWorkOrders () {
        try {
            moveIt ();
            readIt ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        try {
            Thread.sleep (5000l);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }

    List<WorkOrder> workOrdersList = new ArrayList<> ();


    private void moveIt () throws InterruptedException {
        // move work orders in map from one state to another
        List<WorkOrder> in_progress = new ArrayList<> ();

        List<WorkOrder> assigned = new ArrayList<> ();

        List<WorkOrder> completed = new ArrayList<> ();
//IS FOR LOOP OR STREAMS HERE?

        //LAMBDAS!


    }



    private void readIt () {
        // read the json files into WorkOrders and put in map
        File currentDirectory = new File(".");
        File files[] = currentDirectory.listFiles();
        for (File f : files) {
            if (f.getName().endsWith(".json")) {
                // f is a reference to a json file
                ObjectMapper mapper = new ObjectMapper ();
                try{
                    WorkOrder workOrder = mapper.readValue(new File(f.getName()), WorkOrder.class);
                    workOrder.setStatus (Status.INITIAL);
                } catch (JsonParseException e) {
                    e.printStackTrace ();
                } catch (JsonMappingException e) {
                    e.printStackTrace ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
                // f.delete(); will delete the file
            }
        }
    }

    public static void main (String args[]) {
        Processor processor = new Processor ();
        processor.processWorkOrders ();
    }
}


//    The processWorkOrders method should
//
//    Run forever in a loop. Have the loop sleep for a five seconds (or longer).
//        Have a map with Status as the key and a Set of work orders for the value
//        print out the map
//        move work orders from one map entry to the next (i.e., From IN_PROGRESS to DONE; from ASSIGNED TO IN_PROGRESS; from INITIAL to ASSIGNED). A work order should only transitioned once per loop.
//        print out the map
//        Check for new work orders (files). For each new work order
//        reads the file into a WorkOrder object
//        removes the file
//        print out the work order
//        puts the work order in the INITIAL map entry.
//        Here's the code to sleep for 5 seconds (5000 miliseconds)
//

