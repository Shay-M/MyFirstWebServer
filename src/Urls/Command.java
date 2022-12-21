package Urls;

import java.util.logging.Logger;

public abstract class Command implements ICommand {

    private final String m_url;
    private final Logger m_logger;

    protected Command(final String url, final Logger logger) {
        m_url = url;
        m_logger = logger;
    }

    @Override
    public String getUrl() {
        return m_url.toLowerCase();
    }

    protected Logger getM_logger() {
        return m_logger;
    }

}
