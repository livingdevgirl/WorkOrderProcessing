
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Creator {
    public List<WorkOrder> workOrdersList = new ArrayList<> ();
    public void createWorkOrders() throws IOException {
        // read input, create work orders and write as json files

            //make the order
            WorkOrder newWorkOrder = new WorkOrder() {};
            //set the state to the Status
            newWorkOrder.setStatus (Status.INITIAL);
            //Scanner new scanner
            Scanner input = new Scanner(System.in);
            System.out.println ("Please enter your name: ");
            newWorkOrder.setTitle(input.next ());
            System.out.println ("Describe the issue in detail ");
            newWorkOrder.setContents(input.next());
            //ObjectMapper to write json file
            ObjectMapper mapper = new ObjectMapper(){};
            File workOrderFile = new File(newWorkOrder.getId () + ".json");
            String json = mapper.writeValueAsString ("workOrder Ticket Number " + newWorkOrder.getId () + " " + newWorkOrder.getTitle ().toString ()  + " " + newWorkOrder.getContents ().toString () + " " + newWorkOrder.toString ());

        try {
            mapper.writeValue(workOrderFile, json);
            System.out.println ("the following work order has been created" + " " + newWorkOrder.id);
            workOrdersList.add(newWorkOrder);
            } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public static void main(String args[]) throws IOException {
        Creator creator = new Creator();
        try {
            creator.createWorkOrders();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
