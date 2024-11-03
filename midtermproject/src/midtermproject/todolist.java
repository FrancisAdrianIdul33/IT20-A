package midtermproject;
import javax.swing.*;
import java.util.LinkedList;


public class todolist{

    private LinkedList<String> tasks;

    public todolist() {
        tasks = new LinkedList<>();
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void updateTask(int index, String newTask) {
        if (index >= 0 && index < tasks.size()) {
            tasks.set(index, newTask);
        }
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public void displayTasks() {
        StringBuilder taskList = new StringBuilder("To-Do List:\n");
        for (int i = 0; i < tasks.size(); i++) {
            taskList.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, taskList.toString());
    }

    public static void main(String[] args) {
        todolist toDoList = new todolist();
        String[] options = {"Add Task", "Update Task", "Delete Task", "View Tasks", "Exit"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "To-Do List",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    String newTask = JOptionPane.showInputDialog("Enter a new task:");
                    if (newTask != null && !newTask.trim().isEmpty()) {
                        toDoList.addTask(newTask);
                    }
                    break;
                case 1:
                    String indexToUpdate = JOptionPane.showInputDialog("Enter task number to update:");
                    int updateIndex = Integer.parseInt(indexToUpdate) - 1;
                    String updatedTask = JOptionPane.showInputDialog("Enter the updated task:");
                    if (updatedTask != null && !updatedTask.trim().isEmpty()) {
                        toDoList.updateTask(updateIndex, updatedTask);
                    }
                    break;
                case 2:
                    String indexToDelete = JOptionPane.showInputDialog("Enter task number to delete:");
                    int deleteIndex = Integer.parseInt(indexToDelete) - 1;
                    toDoList.deleteTask(deleteIndex);
                    break;
                case 3:
                    toDoList.displayTasks();
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
}

