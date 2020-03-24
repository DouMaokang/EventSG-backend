package eventsg.backend.controller;

import eventsg.backend.model.Event;
import eventsg.backend.model.Notification;
import eventsg.backend.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/notification")
@RestController
public class NotificationController {

    private NotificationServiceImpl notificationService;

    @Autowired
    public NotificationController(NotificationServiceImpl notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * get notification list
     * @param userId id of user
     * @return list of notification objects
     */
    @RequestMapping(value="{userId}",method=RequestMethod.GET)
    public List<Notification> getNotificationList(@PathVariable("userId") UUID userId) {
        return notificationService.getNotificationList(userId);
    }

}
