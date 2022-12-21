package Urls;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Resume extends Command {

    private final ToDo m_toDo;

    public Resume(String url, Logger logger, ToDo toDo) {
        super(url, logger);
        m_toDo = toDo;
    }

    public final String showProfile() {
        getM_logger().log(Level.INFO, "Show Profile");
        String body = "Urls.Resume.";
        body += "<BR>Shay<BR><a href='https://www.hit.ac.il/'>hit</a>";
        body += "<BR> <img width='40%' src='https://i.chzbgr.com/thumb800/16763397/h0B023285/funny-memes-programming-memes-developers-coding-memes-coder-coding-nerdy-memes-memes-java-memes'> <BR>";
        body += "" + listToHtml(m_toDo.getToDoList());
        return body;
    }

    @Override
    public final String go(String[] nameParts) {
        return showProfile();
    }

    private String listToHtml(List<String> toDoList) {
        StringBuilder str;
        str = new StringBuilder("<ul>");

        for (String toDo : toDoList) {
            str.append("<li>").append(toDo).append("</li>");
        }
        str.append("</ul>");
        return str.toString();

    }
}
