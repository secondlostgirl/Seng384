package seng384;

// Task.java
import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private int id;
    private String title;
    private String description;
    private String assignedBy; // "T" for Teacher, "F" for Parent
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;
    private int points;

    public Task(int id, String title, String description, String assignedBy, LocalDate deadlineDate, LocalTime deadlineTime, int points) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assignedBy = assignedBy;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        this.points = points;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public LocalTime getDeadlineTime() {
        return deadlineTime;
    }

    public int getPoints() {
        return points;
    }

 
    public String toString() {
        return "Task ID: " + id + "\nTitle: " + title + "\nDescription: " + description +
               "\nAssigned by: " + assignedBy + "\nDeadline: " + deadlineDate + " " + deadlineTime +
               "\nPoints: " + points;
    }
}
