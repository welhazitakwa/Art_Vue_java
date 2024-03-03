
package models;


import java.util.Date;

public class Calendar {
    
        private int id;

    public Calendar() {
    }

    private String name;
        private Date startdate;
        private Date enddate;


    public void setId(int id) {
        this.id = id;
    }

    public Calendar(int id, String name, Date startdate, Date enddate) {
            this.id = id;
            this.name = name;
            this.startdate = startdate;
            this.enddate = enddate;
        }
    public Calendar( String name, Date startdate, Date enddate) {
        this.name = name;
        this.startdate = startdate;
        this.enddate = enddate;
    }
         
        public String getName() {
            return name;
        }

    public Date getStartdate() {
        return startdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                '}';
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public int getId() {
            return id;
        }


        
        

    }
