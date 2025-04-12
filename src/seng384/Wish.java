package seng384;

import java.time.LocalDate;
import java.time.LocalTime;

public class Wish {
    private String wishId;
    private String title;
    private String description;
    private String status; // "PENDING", "APPROVED", "REJECTED"
    private String type; // "WISH1" for product, "WISH2" for activity
    private LocalDate activityDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int levelRequired; // Level of approval required for the wish

    // Constructor for product wish (WISH1)
    public Wish(String wishId, String title, String description) {
        this.wishId = wishId;
        this.title = title;
        this.description = description;
        this.status = "PENDING";
        this.type = "WISH1"; // Default type is product
        this.levelRequired = 0; // Default level
    }

    // Constructor for activity wish (WISH2)
    public Wish(String wishId, String title, String description, LocalDate activityDate, LocalTime startTime, LocalTime endTime) {
        this.wishId = wishId;
        this.title = title;
        this.description = description;
        this.status = "PENDING";
        this.type = "WISH2";
        this.activityDate = activityDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.levelRequired = 0; // Default level
    }

    // Getter & Setter methods
    public String getWishId() {
        return wishId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setLevelRequired(int levelRequired) {
        this.levelRequired = levelRequired;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    @Override
    public String toString() {
        String wishDetails = wishId + " - " + title + ": " + description +
                "\nType: " + type +
                "\nStatus: " + status;
        if (type.equals("WISH2")) {
            wishDetails += "\nActivity Date: " + activityDate + " " + startTime + " to " + endTime;
        }
        wishDetails += "\nLevel Required: " + levelRequired;
        return wishDetails;
    }
}
