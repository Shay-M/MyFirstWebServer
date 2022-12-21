package Urls;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Profile extends Command {
    public Profile(String url, Logger logger) {
        super(url, logger);
    }

    public final String showProfile() {
        getM_logger().log(Level.INFO, "Show Profile");
        final String body = "My name is Shay.<BR> You killed my java , prepare to die!";

        return body;
    }

    @Override
    public final String go(final String[] nameParts) {
        return showProfile();
    }
}
