package solvd.laba.itcompany.patterns.decorator;

public class Notificator {
    public static void notify(String notofication, boolean file, boolean log) {
        INotificator notificator = new BasicNotificator();
        if (file) {
            notificator = new FileNotificatorDecorator(notificator);
        }
        if (log) {
            notificator = new LogNotificatorDecorator(notificator);
        }

        notificator.notify(notofication);
    }
}
