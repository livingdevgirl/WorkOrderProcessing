import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.File;
import java.io.IOException;
import java.util.*;

import java.util.LinkedList;

public class Processor {

    Map<Status, Set<WorkOrder>> workOrderMap = new HashMap<> ();
    Set<WorkOrder> workOrderSet = workOrderMap.get (Status.INITIAL);

    public Collection<Status> values () {
        return null;
    }

    Deque workOrderDeque = new LinkedList<WorkOrder>();

    public Map<Status, Set<WorkOrder>> getWorkOrderMap () {
        return workOrderMap;
    }


    public void processWorkOrders () throws InterruptedException {
        try {
            readIt ();
            moveIt ();

            Thread.sleep (5000);
            processWorkOrders ();



        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }

    public void initialWorkOrderMap(){
        //Default value hashmap

        workOrderMap.put (Status.INITIAL, new HashSet<> ());
        workOrderMap.put (Status.ASSIGNED, new HashSet<> ());
        workOrderMap.put (Status.IN_PROGRESS, new HashSet<> ());
        workOrderMap.put (Status.DONE, new HashSet<> ());
    }

    private void moveIt () {

        File currentFile = new File(".");
        File files[] = currentFile.listFiles();


        // move work orders in map from one state to another
        Set<WorkOrder> workOrderSetInitial = workOrderMap.get(Status.INITIAL);
        Set<WorkOrder> workOrderSetAssigned = workOrderMap.get(Status.ASSIGNED);
        Set<WorkOrder> workOrderSetInProgress = workOrderMap.get(Status.IN_PROGRESS);
        Set<WorkOrder> workOrderSetDone = workOrderMap.get(Status.DONE);

        //second state

        System.out.println ("processing " + workOrderSetAssigned.size () + " work orders.");
//        remove from initial and into assigned
        workOrderMap.put (Status.ASSIGNED, workOrderSetInitial);
        workOrderSetInitial.removeAll (// STOPSHIP: 9/24/17 how to remove these items and just move them... if the key value is Status.INITIAL);
        System.out.println (workOrderMap);
        workOrderMap.put (Status.IN_PROGRESS, workOrderSetAssigned);
        System.out.println (workOrderMap);
        workOrderMap.put (Status.DONE, workOrderSetInProgress);
        workOrderMap.remove (Status.DONE, workOrderSetDone);


        System.out.println (workOrderMap);


        //initialize
//        Iterator iterator = workOrderSetAssigned.iterator();
//        while(iterator.hasNext()) {
//            Map.Entry me = (Map.Entry)iterator.next();
//            System.out.print("Key is: "+ me.getKey() +
//                    "& Value is: "+me.getValue()+"\n");
//        }



        System.out.println (workOrderSet);



         //print the map
        System.out.println (workOrderMap);}


    protected void readIt () {
        // read the json files into WorkOrders and put in map
        File currentDirectory = new File (".");
        File files[] = currentDirectory.listFiles ();
        for (File f : files) {
            if (f.getName ().endsWith (".json")) {
                // f is a reference to a json file
                System.out.println (f.getName ());

                    ObjectMapper mapper = new ObjectMapper ();
                try {
                    WorkOrder readFile = mapper.readValue (new File(f.getName ()), WorkOrder.class);
//                    String writeFile = mapper.writeValueAsString (values ());

                        Status thisstatus = readFile.getStatus ();
                        putWorkOrderInMap (thisstatus, readFile);





                    f.delete();


                        System.out.println (workOrderMap);



                } catch (IOException e) {
                    e.printStackTrace ();
                }

                //will delete the file

            }
        }
    }

    public static void main (String[] args) throws InterruptedException {
        Processor processor = new Processor ();
        processor.initialWorkOrderMap();
        processor.processWorkOrders ();
        try {
            processor.processWorkOrders ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
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

