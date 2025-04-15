package seng384;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class CommandProcessor {
    private TaskManager taskManager;
    private WishManager wishManager;

    // Constructor
    public CommandProcessor(TaskManager taskManager, WishManager wishManager) {
        this.taskManager = taskManager;
        this.wishManager = wishManager;
    }

    public void processCommands(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // boş satır atla

                List<String> command = splitCommandLine(line);
                if (command.isEmpty()) continue;

                switch (command.get(0)) {
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
                        System.out.println("Unknown command: " + command.get(0));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addTask(List<String> command) {
        // Bu kısım isteğe göre doldurulabilir.
    }

    private void listAllTasks(List<String> command) {
        if (command.size() > 1 && command.get(1).equals("D")) {
            taskManager.listFilteredTasks("D");
        } else if (command.size() > 1 && command.get(1).equals("W")) {
            taskManager.listFilteredTasks("W");
        } else {
            taskManager.listAllTasks();
        }
    }

    private void taskDone(List<String> command) {
        // TASK_DONE işlemleri buraya eklenecek.
    }

    private void addWish(List<String> command) {
        String wishId = command.get(1);
        String title = command.get(2);
        String description = command.get(3);
        wishManager.addWish(wishId, title, description);
        System.out.println("Wish added: " + title);
    }

    private void addActivityWish(List<String> command) {
        try {
            String wishId = command.get(1);
            String title = command.get(2);
            String description = command.get(3);
            String activityDate = command.get(4);
            String startTime = command.get(5);
            String endTime = command.get(6);

            LocalDate activityDateParsed = LocalDate.parse(activityDate);
            LocalTime startTimeParsed = LocalTime.parse(startTime);
            LocalTime endTimeParsed = LocalTime.parse(endTime);

            wishManager.addActivityWish(wishId, title, description, activityDateParsed, startTimeParsed, endTimeParsed);
            System.out.println("Activity wish added: " + title);
        } catch (Exception e) {
            System.out.println("Invalid date/time format for wish ID " + command.get(1) + ". Missing or incorrect date/time values.");
        }
    }

    private void wishChecked(List<String> command) {
        String wishId = command.get(1);
        String status = command.get(2);
        if (status.equals("APPROVED")) {
            int levelRequired = Integer.parseInt(command.get(3));
            wishManager.approveWish(wishId, levelRequired);
        } else {
            wishManager.rejectWish(wishId);
        }
    }

    private List<String> splitCommandLine(String line) {
        List<String> tokens = new ArrayList<>();
        Matcher m = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(line);
        while (m.find()) {
            if (m.group(1) != null) {
                tokens.add(m.group(1)); // Tırnak içindeki ifade
            } else {
                tokens.add(m.group(2)); // Normal kelime
            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        WishManager wishManager = new WishManager();

        taskManager.loadTasksFromFile("src/seng384/Tasks.txt");
        wishManager.loadWishesFromFile("src/seng384/Wish.txt");

        CommandProcessor processor = new CommandProcessor(taskManager, wishManager);
        processor.processCommands("src/seng384/Commands.txt");
    }
}
