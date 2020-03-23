package eventsg.backend.model;

import java.util.UUID;

public class Registration {
    private UUID userId;
    private UUID eventId;

    public Registration(UUID userId, UUID eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }
}
