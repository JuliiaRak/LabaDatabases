package solvd.laba.itcompany.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import solvd.laba.itcompany.patterns.Observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {
    private Long id;
    private Project project;
    private Date meetingDate;
    private int duration;

    private List<Employee> observers = new ArrayList<Employee>();

    public Meeting(Long id, Project project, Date meetingDate, int duration) {
        this.id = id;
        this.project = project;
        this.meetingDate = meetingDate;
        this.duration = duration;
    }

    public void setMeetingDate(Date date) {
        this.meetingDate = date;
        if (observers != null){
            notifyAllObservers();
        }
    }

    public void attach(Employee observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Employee observer : observers) {
            observer.notify(this);
        }
    }
}
