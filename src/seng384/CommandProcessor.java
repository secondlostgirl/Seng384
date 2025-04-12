package seng384;

//CommandProcessor.java
import java.io.*;
import java.util.*;

public class CommandProcessor {
 private TaskManager taskManager;
 private WishManager wishManager;

 public CommandProcessor(TaskManager taskManager, WishManager wishManager) {
     this.taskManager = taskManager;
     this.wishManager = wishManager;
 }

 // Komut dosyasını okuma ve işlemleri başlatma
 public void processCommands(String filePath) {
     try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
         String line;
         while ((line = br.readLine()) != null) {
             String[] command = line.split(" ");
             switch (command[0]) {
                 case "ADD_TASK1":
                 case "ADD_TASK2":
                     addTask(command);
                     break;
                 case "LIST_ALL_TASKS":
                     listAllTasks(command);
                     break;
                 case "TASK_DONE":
                     taskDone(command);
                     break;
                 case "ADD_WISH1":
                     addWish(command);
                     break;
                 case "ADD_WISH2":
                     addActivityWish(command);
                     break;
                 case "WISH_CHECKED":
                     wishChecked(command);
                     break;
                 case "LIST_ALL_WISHES":
                     wishManager.listAllWishes();
                     break;
                 default:
                     System.out.println("Unknown command: " + command[0]);
             }
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
 }

 // Görev ekleme işlemi
 private void addTask(String[] command) {
     // ADD_TASK1 / ADD_TASK2 işlemi için parametreleri al
     // Task oluştur ve TaskManager'a ekle
 }

 // Tüm görevleri listeleme
 private void listAllTasks(String[] command) {
     if (command.length > 1 && command[1].equals("D")) {
         taskManager.listFilteredTasks("D");
     } else if (command.length > 1 && command[1].equals("W")) {
         taskManager.listFilteredTasks("W");
     } else {
         taskManager.listAllTasks();
     }
 }

 // Görev tamamlanma işlemi
 private void taskDone(String[] command) {
     // TASK_DONE işlemi için gerekli işlemleri yap
 }

 // İstek ekleme işlemi
 private void addWish(String[] command) {
     String wishId = command[1];
     String title = command[2];
     String description = command[3];
     wishManager.addWish(wishId, title, description);
     System.out.println("Wish added: " + title);
 }

 // Aktivite isteği ekleme işlemi
 private void addActivityWish(String[] command) {
     String wishId = command[1];
     String title = command[2];
     String description = command[3];
     String activityDate = command[4];
     String startTime = command[5];
     String endTime = command[6];
     wishManager.addActivityWish(wishId, title, description, activityDate, startTime, endTime);
     System.out.println("Activity wish added: " + title);
 }

 // İstek onaylama işlemi
 private void wishChecked(String[] command) {
     String wishId = command[1];
     String status = command[2];
     if (status.equals("APPROVED")) {
         int levelRequired = Integer.parseInt(command[3]);
         wishManager.approveWish(wishId, levelRequired);
     } else {
         wishManager.rejectWish(wishId);
     }
 }
}

