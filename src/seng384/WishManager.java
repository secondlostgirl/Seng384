package seng384;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class WishManager {
    private List<Wish> wishes;

    public WishManager() {
        wishes = new ArrayList<>();
    }

    // Yeni bir istek ekleme
    public void addWish(String wishId, String title, String description) {
        Wish wish = new Wish(wishId, title, description);
        wishes.add(wish);
    }

    // Aktivite isteği ekleme (WISH2)
    public void addActivityWish(String wishId, String title, String description, String activityDate, String startTime, String endTime) {
        LocalDate activityDateParsed = LocalDate.parse(activityDate);
        LocalTime startTimeParsed = LocalTime.parse(startTime);
        LocalTime endTimeParsed = LocalTime.parse(endTime);

        Wish wish = new Wish(wishId, title, description, activityDateParsed, startTimeParsed, endTimeParsed);
        wishes.add(wish);
    }

    // Tüm istekleri listeleme
    public void listAllWishes() {
        for (Wish wish : wishes) {
            System.out.println(wish);
            System.out.println("-----------------------------");
        }
    }

    // İstek onaylama
    public void approveWish(String wishId, int levelRequired) {
        for (Wish wish : wishes) {
            if (wish.getWishId().equals(wishId)) {
                wish.setStatus("APPROVED");
                wish.setLevelRequired(levelRequired); // Onaylanan isteğin level'ını belirliyoruz
                System.out.println("Wish " + wishId + " approved at level " + levelRequired);
                return;
            }
        }
        System.out.println("Wish ID not found!");
    }

    // İstek reddetme
    public void rejectWish(String wishId) {
        for (Wish wish : wishes) {
            if (wish.getWishId().equals(wishId)) {
                wish.setStatus("REJECTED");
                System.out.println("Wish " + wishId + " rejected");
                return;
            }
        }
        System.out.println("Wish ID not found!");
    }

    // İsteklerin listelenmesi (onaylı, reddedilmiş, bekleyen)
    public void listWishesByStatus(String status) {
        for (Wish wish : wishes) {
            if (wish.getStatus().equals(status)) {
                System.out.println(wish);
                System.out.println("-----------------------------");
            }
        }
    }
}
