import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

import Urls.*;
import super_simple_web_server.SuperSimpleWebServer;

public final class MyFirstWebServer {


    public static void main(final String[] args) {
        final Logger logger = Logger.getLogger(MyFirstWebServer.class.getCanonicalName());
        final Router router = new Router(logger);
        try (final SuperSimpleWebServer server = new SuperSimpleWebServer(PORT, logger)) { //try with resource
            while (true) {
                try (final SuperSimpleWebServer.Request request = server.waitForRequest()) {
                    router.update(request);
                }
            }
        } catch (final IOException ex) {
            logger.log(Level.SEVERE, "IOException " + ex.getMessage());
        }

    }
}

