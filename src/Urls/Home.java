package Urls;

import Urls.ICommand;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Home extends Command {

    public Home(String url, Logger logger) {
        super(url, logger);
    }

    public final String showHome() {
        getM_logger().log(Level.INFO, "Show Profile");
        String bady = "<li><a href='/shay'>shay</a> </li> <li><a href='/resume'>resume</a> </li> ";

        return bady;
    }

    @Override
    public String go(String[] nameParts) {
        return showHome();
    }
}
