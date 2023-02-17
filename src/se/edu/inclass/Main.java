package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

//        printData(tasksData);
        System.out.println();
        System.out.println("Printing deadlines");
//        printDeadlines(tasksData);
//        printDeadlinesUsingStream(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        ArrayList<Task> filteredList = filterTaskUsingStream(tasksData, "11");
        printData(filteredList);

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        System.out.println("Print data");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        tasks.stream()
                .filter(i -> i instanceof Deadline)
                .sorted((a, b) -> a.getDescription().compareToIgnoreCase(b.getDescription()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTaskUsingStream (ArrayList<Task> tasks, String filteredString) {
        return (ArrayList<Task>) tasks.stream()
                .filter(i -> i.getDescription().contains(filteredString))
                .collect(Collectors.toList());
    }

    public static void printDataUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Printing the data using streams");
        tasks.stream()
                .forEach(System.out::println);
    }

    private static int countDeadlinesUsingStreams(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter(i -> i instanceof Deadline)
                .count();
        return count;
    }
}
