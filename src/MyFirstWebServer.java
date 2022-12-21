import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

import Urls.*;
import super_simple_web_server.SuperSimpleWebServer;

import java.util.ArrayList;
import java.util.List;

// http://127.0.0.1:1080/
public final class MyFirstWebServer {

    private final static int PORT = 1080;

    public static void main(final String[] args) {
        final Logger logger = Logger.getLogger(MyFirstWebServer.class.getCanonicalName());
        try {
            final SuperSimpleWebServer server = new SuperSimpleWebServer(PORT, logger);

            final List<ICommand> urls = new ArrayList<ICommand>();
            urls.add(new Home("/", logger));
            urls.add(new Profile("shay", logger));
            ToDo toDo = new ToDo("addTask", logger);
            urls.add(new Resume("resume", logger, toDo));
            urls.add(toDo);

            while (true) {
                try (SuperSimpleWebServer.Request request = server.waitForRequest()) {
                    String untruest_uri = request.getUri();//on list?

                    String[] untruest_nameParts = untruest_uri.split("/");
                    try {
                        final String urlRout = (untruest_nameParts[1]).toLowerCase();
                        for (ICommand ICommandInList : urls) {
                            if (ICommandInList.getUrl().equals(urlRout)) {
                                request.getWriter().write(ICommandInList.go(untruest_nameParts));
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        request.getWriter().write(urls.get(0).go(untruest_nameParts));
                    }
                }
            }

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "IOException " + ex.getMessage());
        }

    }

    public static boolean validateHTTP_URI(String uri) {
        final Logger logger = Logger.getLogger(MyFirstWebServer.class.getCanonicalName());
        logger.log(Level.SEVERE, "uri: " + uri);
        return true;
    }
}

