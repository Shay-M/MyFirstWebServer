package Urls;

import super_simple_web_server.SuperSimpleWebServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Router {
    private final List<Command> urls = new ArrayList<Command>();
    private final int HOME_PAGE = 0;
    private final int PAGE_URI_NAME_INDEX = 1;
    private SuperSimpleWebServer.Request m_request;
    private Logger m_logger;

    public Router(final Logger logger) {
        m_logger = logger;
        initializeList();
    }

    public final void update(SuperSimpleWebServer.Request request) {
        // String untruest_uri = request.getUri();
        final String uri = request.getUri();
        final String[] nameParts = uri.split("/");

        try {
            if (nameParts.length < 2) {
                request.getWriter().write(urls.get(HOME_PAGE).go(nameParts));
            } else {
                final String urlRout = (nameParts[PAGE_URI_NAME_INDEX]).toLowerCase();
                for (Command commandInList : urls) {
                    if (commandInList.getUrl().equals(urlRout)) {
                        request.getWriter().write(commandInList.go(nameParts));
                    }
                }
            }
        } catch (IOException ex) {
            m_logger.log(Level.SEVERE, "IOException " + ex.getMessage());
        }
    }

    private void initializeList() {
        urls.add(new Home("/", m_logger));//?
        urls.add(new Profile("shay", m_logger));
        final ToDo toDo = new ToDo("addTask", m_logger);
        urls.add(new Resume("resume", m_logger, toDo));
        urls.add(toDo);

        assert (urls.size() != 0);
    }
}
