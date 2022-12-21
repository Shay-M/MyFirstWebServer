import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

import Urls.*;
import super_simple_web_server.SuperSimpleWebServer;

public final class MyFirstWebServer {

    private final static int PORT = 1080; // http://127.0.0.1:1080/

    public static void main(final String[] args) {
        final Logger logger = Logger.getLogger(MyFirstWebServer.class.getCanonicalName());
        final Router router = new Router(logger);
        try {
            final SuperSimpleWebServer server = new SuperSimpleWebServer(PORT, logger);
            while (true) {
                try (SuperSimpleWebServer.Request request = server.waitForRequest()) {
                    router.update(request);
                }
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "IOException " + ex.getMessage());
        }

    }
}

