package notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationComposite implements NotificationService {
    private final List<NotificationService> services = new ArrayList<>();

    public void addNotificationService(NotificationService service) {
        services.add(service);
    }

    public void removeNotificationService(NotificationService service) {
        if (!services.remove(service)) {
            throw new IllegalArgumentException("Service not found");
        }
    }

    @Override
    public void sendNotification(String message) {
        for (NotificationService service : services) {
            service.sendNotification(message);
        }
    }
}