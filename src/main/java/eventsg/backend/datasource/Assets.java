package eventsg.backend.datasource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Assets {


    private static Assets single_instance=null;

    private ArrayList<String> userImage = new ArrayList<>(
            Arrays.asList(
                    "assets/male-1.jpg",
                    "assets/female-1.jpg",
                    "assets/male-2.jpg",
                    "assets/female-2.jpg",
                    "assets/male-3.jpg",
                    "assets/female-3.jpg",
                    "assets/male-4.jpg",
                    "assets/female-4.jpg"
            )
    );


    private ArrayList<String> eventImage = new ArrayList<>(
            Arrays.asList(
                    "assets/event-1.jpg",
                    "assets/event-2.jpg",
                    "assets/event-3.jpg",
                    "assets/event-4.jpg",
                    "assets/event-5.jpg",
                    "assets/event-6.jpg",
                    "assets/event-7.jpg",
                    "assets/event-8.jpg",
                    "assets/event-9.jpg",
                    "assets/event-10.jpg",
                    "assets/event-11.jpg",
                    "assets/event-12.jpg",
                    "assets/event-13.jpg",
                    "assets/event-14.jpg",
                    "assets/event-15.jpg",
                    "assets/event-16.jpg",
                    "assets/event-17.jpg",
                    "assets/event-18.jpg",
                    "assets/event-19.jpg",
                    "assets/event-20.jpg"
            )
    );

    private ArrayList<String> venueImage = new ArrayList<>(
            Arrays.asList(
                    "assets/venue-1.jpg",
                    "assets/venue-2.jpg",
                    "assets/venue-3.jpg",
                    "assets/venue-4.jpg",
                    "assets/venue-5.jpg",
                    "assets/venue-6.jpg",
                    "assets/venue-7.jpg",
                    "assets/venue-8.jpg",
                    "assets/venue-9.jpg",
                    "assets/venue-10.jpg",
                    "assets/venue-11.jpg",
                    "assets/venue-12.jpg",
                    "assets/venue-13.jpg",
                    "assets/venue-14.jpg",
                    "assets/venue-15.jpg",
                    "assets/venue-16.jpg",
                    "assets/venue-17.jpg",
                    "assets/venue-18.jpg",
                    "assets/venue-19.jpg",
                    "assets/venue-20.jpg"
            )
    );

    private Assets() {}

    // static method to create instance of Singleton class
    public static Assets Assets()
    {
        // To ensure only one instance is created
        if (single_instance == null)
        {
            single_instance = new Assets();
        }
        return single_instance;
    }

    public String getUserImage() {
        Random random = new Random();
        return userImage.get( random.nextInt(userImage.size()) ).toString();
    }

    public String getEventImage() {
        Random random = new Random();
        return eventImage.get( random.nextInt(eventImage.size()) ).toString();
    }

    public String getVenueImage() {
        Random random = new Random();
        return venueImage.get( random.nextInt(venueImage.size()) ).toString();
    }
}
