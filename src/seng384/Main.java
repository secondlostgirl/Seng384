package seng384;

public class Main {
    public static void main(String[] args) {
        // TASKS
        TaskManager tm = new TaskManager();
        tm.loadTasksFromFile("src/seng384/Tasks.txt");

        // WISHES
        WishManager wm = new WishManager();
        wm.loadWishesFromFile("src/seng384/Wishes.txt");

        // GÖRÜNTÜLEME
        System.out.println("All Tasks:");
        tm.listAllTasks();

        System.out.println("\nAll Wishes:");
        wm.listAllWishes();
    }
}
