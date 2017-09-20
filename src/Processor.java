import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Processor {

    Map<Status, Set<WorkOrder>> workOrderMap = new HashMap<> ();
    Set<WorkOrder> workOrderSet = workOrderMap.get (Status.INITIAL);


    public Map<Status, Set<WorkOrder>> getWorkOrderMap () {
        return workOrderMap;
    }


    public void processWorkOrders () throws InterruptedException {
        try {
            moveIt ();
            readIt ();
            Thread.sleep (5000);
            processWorkOrders ();



        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }

    private void moveIt () {


        // move work orders in map from one state to another
        Set<WorkOrder> workOrderSetInitial = workOrderMap.get (Status.DONE);
        Set<WorkOrder> workOrderSetAssigned = workOrderMap.get (Status.INITIAL);
        Set<WorkOrder> workOrderSetInProgress = workOrderMap.get (Status.ASSIGNED);
        Set<WorkOrder> workOrderSetDone = workOrderMap.get (Status.IN_PROGRESS);
        System.out.println (workOrderMap);
        System.out.println (workOrderSetAssigned);

        //initialize
//        Iterator iterator = workOrderSetAssigned.iterator();
//        while(iterator.hasNext()) {
//            Map.Entry me = (Map.Entry)iterator.next();
//            System.out.print("Key is: "+ me.getKey() +
//                    "& Value is: "+me.getValue()+"\n");
//        }

        //Default value hashmap

        workOrderMap.put (Status.INITIAL, new HashSet<> ());
        workOrderMap.put (Status.ASSIGNED, new HashSet<> ());
        workOrderMap.put (Status.IN_PROGRESS, new HashSet<> ());
        workOrderMap.put (Status.DONE, new HashSet<> ());

        workOrderMap.put (Status.ASSIGNED, workOrderSetAssigned);
        workOrderMap.put (Status.IN_PROGRESS, workOrderSetInProgress);
        workOrderMap.put (Status.DONE, workOrderSetDone);
        System.out.println (getWorkOrderMap ());


         //print the map
        System.out.println (workOrderMap);}


    protected void readIt () {
        // read the json files into WorkOrders and put in map
        File currentDirectory = new File ("../");
        File files[] = currentDirectory.listFiles ();
        for (File f : files) {
            if (f.getName ().endsWith (".json")) {
                // f is a reference to a json file


                    ObjectMapper mapper = new ObjectMapper ();
                try {
                    WorkOrder readFile = mapper.readValue (new File(f.getName ()), WorkOrder.class);
                    String writeFile = mapper.writeValueAsString (readFile);

                        Status status = readFile.getStatus ();
                        putWorkOrderInMap (status, readFile);
                        workOrderSet.add (readFile);




                    System.out.println (workOrderMap);



                } catch (IOException e) {
                    e.printStackTrace ();
                }

                //will delete the file
                f.delete ();
            }
        }
    }

    public static void main (String[] args) throws InterruptedException {
        Processor processor = new Processor ();
        processor.processWorkOrders ();
        try {
            processor.processWorkOrders ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }

    public void putWorkOrderInMap () {
        putWorkOrderInMap ();
    }

    public void putWorkOrderInMap(Status status, WorkOrder workOrder) {
        Set<WorkOrder> workOrderSet = workOrderMap.get(status);
        workOrderSet.add(workOrder);
        workOrderMap.put(status, workOrderSet);
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

