//package eventsg.backend.controller;
//
//import eventsg.backend.model.Event;
//import eventsg.backend.model.Notification;
//import eventsg.backend.service.NotificationServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RequestMapping("api/notification")
//@RestController
//public class NotificationController {
//
//    private NotificationServiceImpl notificationService;
//
//    @Autowired
//    public NotificationController(NotificationServiceImpl notificationService) {
//        this.notificationService = notificationService;
//    }
//
//    /**
//     * get notification list
//     * @param userId id of user
//     * @return list of notification objects
//     */
//    @RequestMapping(value="{userId}",method=RequestMethod.GET)
//    public List<Notification> getNotificationList(@PathVariable("userId") UUID userId) {
//        return notificationService.getNotificationList(userId);
//    }
//
//    /**
//     * check and add into notification list if needed when register a new event
//     * @param notificationList current list of notification objects
//     * @param event the new registered event
//     * @return list of notification objects
//     */
//    @RequestMapping(value="{Notification}",method=RequestMethod.GET,consumes="notificationList/json",produces="notificationList/json")
//    public List<Notification> addEventToNotificationList(@RequestParam(value="notification") List<Notification> notificationList,@RequestBody Event event) {
//        notificationService.addEventToNotificationList(true,notificationList,event);
//        return notificationList;
//    }
//
//    /**
//     * check and delete from notification list if needed when cancel a registration
//     * @param notificationList current list of notification objects
//     * @param event event that is canceled
//     * @return list of notification objects
//     */
//    @RequestMapping(value="{Notification}",method=RequestMethod.GET,consumes="notificationList/json",produces="notificationList/json")
//    public List<Notification> deleteEventFromNotificationList(@RequestParam(value="notification") List<Notification> notificationList,@RequestBody Event event) {
//        notificationService.deleteEventFromNotificationList(notificationList,event);
//        return notificationList;
//    }
//
//}
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
