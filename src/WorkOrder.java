import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class WorkOrder {




    protected Random id = new Random();
    protected Status status; //default value INITIAL
    private String title; //name
    private String contents; // details of work order

    WorkOrder(){


    }


    public Random getId () {
        return id;
    }

    public void setStatus (Status status) {
        this.status = status;
    }

    public void setId () {
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
