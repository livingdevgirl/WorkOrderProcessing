
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.TimeZoneSerializer;

import java.io.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Creator {


    public List<WorkOrder> workOrdersList = new ArrayList<> ();

    public List<WorkOrder> getWorkOrdersList () {
        return workOrdersList;
    }

    public void createWorkOrders() throws IOException, InterruptedException {
        // read input, create work orders and write as json files
            try{
            //make the order
            WorkOrder newWorkOrder = new WorkOrder() {};
//            //set the state to the Status and ID
            Scanner scanner = new Scanner(System.in);
            newWorkOrder.setStatus (Status.INITIAL);
            newWorkOrder.setId ();
            //Scanner new scanner
            Scanner input = new Scanner(System.in);
            System.out.println ("Please enter your name: ");
            String orderAuthor = scanner.nextLine ();
            System.out.println ("Describe the issue in detail ");
            String orderContents = scanner.nextLine ();

            newWorkOrder.setTitle (orderAuthor);
            newWorkOrder.setContents (orderContents);

            //ObjectMapper to write json file
            ObjectMapper mapper = new ObjectMapper(){};
            File workOrderFile = new File(newWorkOrder.getId () + ".json");
            String json = mapper.writeValueAsString ("workOrder Ticket Number " + newWorkOrder.getId () + newWorkOrder.getStatus () + newWorkOrder.getContents () + newWorkOrder.getTitle ());
            System.out.println (newWorkOrder.status + " " + "Work Created By " + newWorkOrder.getTitle () + " " + "Contents" + " " + newWorkOrder.getContents ());

            mapper.writeValue(workOrderFile, newWorkOrder);
            System.out.println ("the following work order has been created"  + " " + workOrderFile);
            workOrdersList.add(newWorkOrder);

//                Processor processor = new Processor ();
//                processor.putWorkOrderInMap (Status.INITIAL, newWorkOrder);


            } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public static void main(String args[]) throws IOException {
        Creator creator = new Creator();
            try{
                while(true){
                    creator.createWorkOrders();
                    Processor processor = new Processor ();
                    processor.readIt ();
                    Thread.sleep(5000);
                }
            }catch (InterruptedException e){
                e.printStackTrace ();
            }


    }
}
