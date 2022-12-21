package Urls;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToDo extends Command {
    private final List<String> toDoList;

    public ToDo(final String url, final Logger logger) {
        super(url, logger);
        toDoList = new ArrayList<String>();
    }

    public final String addTaskToList(final String task) {
        getM_logger().log(Level.INFO, task + " add to list");
        toDoList.add(task);
        final String body = "Hello, " + task + " add to list!";

        return body;
    }

    public final List<String> getToDoList() {
        return toDoList;
    }


    @Override
    public final String go(final String[] untrust_nameParts) {
        return addTaskToList(untrust_nameParts[2]);
    }


}
