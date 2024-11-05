package midterm.project;

import javax.swing.*;
import java.util.*;

public class TodoListManager {

    static LinkedList<String> todo = new LinkedList<>(), done = new LinkedList<>();
    static Stack<String> undoStack = new Stack<>();

    public static void main(String[] args) {
        while (true) {
            String menu = "1. Add Task\n2. Mark Task as Done\n3. Undo\n4. View To-Do List\n5. View Completed Tasks\n6. Exit\n\n";
            String choice = JOptionPane.showInputDialog(null, "Please input the number of single operation below." + "\n\n" + menu, "TodoList Manager", JOptionPane.PLAIN_MESSAGE);

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    markDone();
                    break;
                case "3":
                    undo();
                    break;
                case "4":
                    viewList(todo, "To-Do List");
                    break;
                case "5":
                    viewList(done, "Completed Tasks");
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, "Thank You!");
                    return;
            }
        }
    }

    static void addTask() {
        String task = JOptionPane.showInputDialog("Enter task:");
        if (task != null) {
            todo.add(task);
            undoStack.push("add:" + task);
        }
        JOptionPane.showMessageDialog(null, "Task Sucessfully Added.", "Task: " + task, JOptionPane.INFORMATION_MESSAGE);

    }

    static void markDone() {
        if (todo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks to mark as done.", "List Status: Empty", JOptionPane.WARNING_MESSAGE);
            return;
        }
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < todo.size(); i++) {
            list.append((i + 1) + ". " + todo.get(i) + "\n");
        }
        int index = Integer.parseInt(JOptionPane.showInputDialog("Select task number to mark as done:\n" + list));
        if (index > 0 && index <= todo.size()) {
            String task = todo.remove(index - 1);
            done.add(task + "  ✔");
            undoStack.push("done:" + task);
            JOptionPane.showMessageDialog(null, "Marked as done.", "Task: " + task, JOptionPane.INFORMATION_MESSAGE);
        }
    }

   static void undo() {
    if (undoStack.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No tasks to undo.", "List Status: Empty", JOptionPane.WARNING_MESSAGE);
        return;
    }
    String action = undoStack.pop();
    String[] parts = action.split(":");
    if (parts.length < 2) {
        JOptionPane.showMessageDialog(null, "Invalid action format.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (parts[0].equals("add")) {
        todo.remove(parts[1]);
        JOptionPane.showMessageDialog(null, "The recently added task has been removed.", "Message", JOptionPane.INFORMATION_MESSAGE);
    } else if (parts[0].equals("remove")) {
        done.add(parts[1]);
        JOptionPane.showMessageDialog(null, "The recently removed task has been restored.", "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}

    static void viewList(LinkedList<String> list, String title) {
        StringBuilder output = new StringBuilder();
        int taskNumber = 1;

        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nothing to see here.", "List Status: Empty", JOptionPane.WARNING_MESSAGE);
        } else {
            for (String task : list) {
                output.append(taskNumber + ". " + task + "\n");
                taskNumber++;
            }
            JOptionPane.showMessageDialog(null, title + ":\n" + output.toString());
        }
    }
}
