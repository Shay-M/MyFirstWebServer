import java.net.URL;
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
            Router router = new Router(logger);

            final List<ICommand> urls = new ArrayList<ICommand>();
            urls.add(new Home("/", logger));
            urls.add(new Profile("shay", logger));
            ToDo toDo = new ToDo("addTask", logger);
            urls.add(new Resume("resume", logger, toDo));
            urls.add(toDo);


            while (true) {
                try (SuperSimpleWebServer.Request request = server.waitForRequest()) {
                    String untruest_uri = request.getUri();
                    String uri = request.getUri();
                    String[] nameParts = uri.split("/");
                    try {
                        final String urlRout = (nameParts[1]).toLowerCase();
                        for (ICommand ICommandInList : urls) {
                            if (ICommandInList.getUrl().equals(urlRout)) {
                                request.getWriter().write(ICommandInList.go(nameParts));
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        request.getWriter().write(urls.get(0).go(nameParts));
                    }
                }
            }

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "IOException " + ex.getMessage());
        }

    }

    public static boolean validateHTTP_URI(String uri) {
        final URL url;
        try {
            url = new URL(uri);
        } catch (Exception e1) {
            return false;
        }
        return "http".equals(url.getProtocol());
    }
}

