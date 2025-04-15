package seng384;

import java.time.LocalDate;
import java.time.LocalTime;

public class Wish {
    private String wishId;
    private String title;
    private String description;
    private String status; // "PENDING", "APPROVED", "REJECTED"
    private String type;   // "WISH1" for product, "WISH2" for activity
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
        this.type = "WISH1";
        this.levelRequired = 0;
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
        this.levelRequired = 0;
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

  
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(wishId).append(" - ").append(title).append(": ").append(description).append("\n");
        sb.append("Type: ").append(type).append("\n");
        sb.append("Status: ").append(status).append("\n");

        if (type.equals("WISH2")) {
            sb.append("Activity Date: ").append(activityDate).append(" ")
              .append(startTime).append(" to ").append(endTime).append("\n");
        }

        sb.append("Level Required: ").append(levelRequired);
        return sb.toString();
    }
}
