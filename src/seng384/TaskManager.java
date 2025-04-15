package seng384;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    // Görevleri dosyadan okuma
    public void loadTasksFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] taskDetails = line.split(",");
                int id = Integer.parseInt(taskDetails[0].trim());
                String title = taskDetails[1].trim();
                String description = taskDetails[2].trim();
                String assignedBy = taskDetails[3].trim();
                LocalDate deadlineDate = LocalDate.parse(taskDetails[4].trim());
                LocalTime deadlineTime = LocalTime.parse(taskDetails[5].trim());
                int points = Integer.parseInt(taskDetails[6].trim());

                Task task = new Task(id, title, description, assignedBy, deadlineDate, deadlineTime, points);
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Görevleri listeleme
    public void listAllTasks() {
        for (Task task : tasks) {
            System.out.println(task);
            System.out.println("-----------------------------");
        }
    }

    // Görevleri günlük/haftalık filtreleme
    public void listFilteredTasks(String filter) {
        LocalDate today = LocalDate.now();
        for (Task task : tasks) {
            if (filter.equals("D") && task.getDeadlineDate().isEqual(today)) {
                System.out.println(task);
            } else if (filter.equals("W") && task.getDeadlineDate().isAfter(today.minusDays(7)) && task.getDeadlineDate().isBefore(today.plusDays(7))) {
                System.out.println(task);
            }
        }
    }

    // Main method to start the application
 // Main method to start the application
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.loadTasksFromFile("src/seng384/Tasks.txt"); // Doğru dizin burası
        taskManager.listAllTasks(); // Tüm görevleri listele
        // Diğer komutları buraya ekleyebilirsiniz
    }

}
