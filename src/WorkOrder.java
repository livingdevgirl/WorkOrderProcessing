import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class WorkOrder {




    Random id = new Random();
    protected Status status; //default value INITIAL
    private String title; //name
    private String contents; // details of work order

    WorkOrder(){


    }


    public static void populate(){
        List<WorkOrder> orders = new ArrayList<> ();
        WorkOrder order= new WorkOrder();
    }
    public Random getId () {
        return id;
    }

    public void setStatus (Status status) {
        this.status = status;
    }

    public void setId (Random id) {
        this.id = id;
    }

    public Status getStatus () {
        return status;
    }

    public String getTitle () {
        return title;
    }

    public String getContents () {
        return contents;
    }


    public void setTitle (String title) {
        this.title = title;
    }

    public void setContents (String contents) {
        this.contents = contents;
    }
}
