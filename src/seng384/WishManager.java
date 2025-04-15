package seng384;

import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class WishManager {
    private List<Wish> wishes;

    public WishManager() {
        wishes = new ArrayList<>();
    }

    // Dosyadaki istekleri yükleme
    public void loadWishesFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Satırdaki gereksiz boşlukları temizle

                // Ürün isteği (ADD_WISH1)
                if (line.startsWith("ADD_WISH1")) {
                    String[] parts = line.split("\"");
                    String wishId = parts[0].split(" ")[1].trim(); // W102
                    String title = parts[1].trim(); // "Lego Set"
                    String description = parts[2].trim(); // "Price:150TL, Brand:LEGO"
                    addWish(wishId, title, description);
                }
                // Aktivite isteği (ADD_WISH2)
                else if (line.startsWith("ADD_WISH2")) {
                    String[] parts = line.split("\"");
                    String wishId = parts[0].split(" ")[1].trim(); // W103
                    String title = parts[1].trim(); // "Go to the Cinema"
                    String description = parts[2].trim(); // "Price:100TL"

                    // Tarih ve saat bilgilerini doğru şekilde çıkar
                    String[] remainingParts = line.split("\"", 4); 
                    if (remainingParts.length == 4) {
                        String dateTimeString = remainingParts[3].trim();
                        String[] dateParts = dateTimeString.split(" "); // 2025-03-07 14:00 2025-03-07 16:00
                        
                        // Tarih ve saat değerlerini kontrol et
                        if (dateParts.length == 3) {
                            try {
                                LocalDate activityDate = LocalDate.parse(dateParts[0].trim()); // 2025-03-07
                                LocalTime startTime = LocalTime.parse(dateParts[1].trim()); // 14:00
                                LocalTime endTime = LocalTime.parse(dateParts[2].trim()); // 16:00

                                addActivityWish(wishId, title, description, activityDate, startTime, endTime);
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date/time format for wish ID " + wishId + ". Expected format: yyyy-MM-dd HH:mm yyyy-MM-dd HH:mm");
                                System.out.println("Problematic line: " + line);  // Hatalı satırı ekranda göster
                                System.out.println("Error: " + e.getMessage());  // Hata mesajını ekrana yazdır
                            }
                        } else {
                            System.out.println("Invalid date/time format for wish ID " + wishId + ". Missing date/time values.");
                        }
                    } else {
                        System.out.println("Invalid format: Unable to extract date/time from wish ID " + wishId);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ürün isteği ekleme
    public void addWish(String wishId, String title, String description) {
        Wish wish = new Wish(wishId, title, description);
        wishes.add(wish);
    }

    // Aktivite isteği ekleme
    public void addActivityWish(String wishId, String title, String description, LocalDate activityDate, LocalTime startTime, LocalTime endTime) {
        // Aktivite isteğini oluştur
        Wish wish = new Wish(wishId, title, description, activityDate, startTime, endTime);
        wishes.add(wish);
        System.out.println("Activity wish added: " + title);
    }

    // Tüm istekleri listeleme
    public void listAllWishes() {
        System.out.println("All Wishes:");
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
                wish.setLevelRequired(levelRequired);
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

    // Duruma göre istekleri listeleme
    public void listWishesByStatus(String status) {
        System.out.println("Wishes with status " + status + ":");
        for (Wish wish : wishes) {
            if (wish.getStatus().equals(status)) {
                System.out.println(wish);
                System.out.println("-----------------------------");
            }
        }
    }

    // Main method (örnek kullanım)
    public static void main(String[] args) {
        WishManager wm = new WishManager();
        wm.loadWishesFromFile("src/seng384/Wishes.txt");  // Dosya yolunu doğru belirle
        wm.listAllWishes(); // Tüm istekleri göster
    }
}
