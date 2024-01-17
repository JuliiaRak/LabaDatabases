package solvd.laba.itcompany.patterns.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogNotificatorDecorator implements INotificator {

    private static final Logger LOGGER = LogManager.getLogger(LogNotificatorDecorator.class);

    protected INotificator notificator;

    public LogNotificatorDecorator(INotificator notificator){
        this.notificator = notificator;
    }

    @Override
    public void notify(String notification) {
        notificator.notify(notification);
        LOGGER.info(notification);
    }
}
