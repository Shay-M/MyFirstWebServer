package Urls;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Router {
    public Router(Logger logger) {
        final List<ICommand> urls = new ArrayList<ICommand>();
        urls.add(new Home("/", logger));
        urls.add(new Profile("shay", logger));
        ToDo toDo = new ToDo("addTask", logger);
        urls.add(new Resume("resume", logger, toDo));
        urls.add(toDo);

        assert (urls.size() != 0);


    }
}
