package solvd.laba.itcompany.patterns.decorator;

public class BasicNotificator implements INotificator {
    @Override
    public void notify(String notification) {
        System.out.println(notification);
    }
}
