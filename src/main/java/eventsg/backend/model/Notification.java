package eventsg.backend.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {

    private UUID notificationId;

    //maybe can have event object for more information
    private UUID eventId;

    //type 1,2,3 for review, update and capacity notification respectively
    private int type;

    //only for review notification
    private UUID reviewId;

    //only for capacity notification, 1 for 80%, 2 for 100%
    private int capacityLevel;


}
