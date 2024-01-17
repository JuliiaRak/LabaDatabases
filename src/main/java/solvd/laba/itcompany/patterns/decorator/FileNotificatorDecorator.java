package solvd.laba.itcompany.patterns.decorator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileNotificatorDecorator implements INotificator {

    protected INotificator notificator;

    public FileNotificatorDecorator(INotificator notificator){
        this.notificator = notificator;
    }

    @Override
    public void notify(String notification) {
        notificator.notify(notification);

        String filePath = "notification.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(notification);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
