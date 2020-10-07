package com.wandera.hw.api;

import com.wandera.hw.service.DataStructureWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DataStructureTest {

    private DataStructureWrapper dataStructureWrapper;

    @BeforeEach
    void setup() {
        dataStructureWrapper = new DataStructureWrapper();
    }


    @Test
    public void dataStructureEmptyTest() {
        Assertions.assertEquals(dataStructureWrapper.size(), 0);
    }

    @Test
    public void dataStructureNonEmptyTest() {
        final Notification notification = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a9e6", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd68", "SITE_BLOCKED", "ADULT", "", "", "", "Site/App block", "Adult", "1390235400000", "1390235423000"));
        dataStructureWrapper.addNotification(notification);
        Assertions.assertEquals(dataStructureWrapper.size(), 1);
    }

    @Test
    public void dataStructureAddTest() {
        final Notification notification = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a9e6", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd68", "SITE_BLOCKED", "ADULT", "", "", "", "Site/App block", "Adult", "1390235400000", "1390235423000"));
        dataStructureWrapper.addNotification(notification);
        Assertions.assertEquals(dataStructureWrapper.getNotification("2affbe87-fd11-4e38-997c-7adf3dc6a9e6"), notification);
    }

    @Test
    public void dataStructureGetTest() {
        final Notification notification1 = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a9e6", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd68", "SITE_BLOCKED", "ADULT", "", "", "", "Site/App block", "Adult", "1390235400000", "1390235423000"));
        final Notification notification2 = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a9e5", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd68", "SITE_BLOCKED", "ADULT", "", "", "", "Site/App block", "Adult", "1390235410000", "1390235423000"));
        final Notification notification3 = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a9e3", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd68", "SITE_BLOCKED", "ADULT", "", "", "", "Site/App block", "Adult", "1390235430000", "1390235423000"));
        dataStructureWrapper.addNotification(notification1);
        dataStructureWrapper.addNotification(notification2);
        dataStructureWrapper.addNotification(notification3);

        Assertions.assertEquals(dataStructureWrapper.size(), 3);
        Assertions.assertEquals(dataStructureWrapper.getNotification("2affbe87-fd11-4e38-997c-7adf3dc6a9e6"), notification1);
        Assertions.assertEquals(dataStructureWrapper.getNotification("2affbe87-fd11-4e38-997c-7adf3dc6a9e5"), notification2);
        Assertions.assertEquals(dataStructureWrapper.getNotification("2affbe87-fd11-4e38-997c-7adf3dc6a9e3"), notification3);
    }

    @Test
    public void dataStructureHierarchyTest() {
        final Notification notification1 = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a9e6", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd68", "SITE_BLOCKED", "ADULT", "", "", "", "Site/App block", "Adult", "1390235400000", "1390235423000"));
        final Notification notification2 = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a9e5", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd68", "SITE_BLOCKED", "ADULT", "", "", "", "Site/App block", "Adult", "1390235410000", "1390235423000"));
        final Notification notification3 = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a9e3", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd68", "CAP_EXTENDED", "ADULT", "", "", "", "Site/App block", "Adult", "1390235430000", "1390235423000"));
        final Notification notification4 = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a956", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd50", "OTHER", "ADULT", "", "", "", "Site/App block", "Adult", "1390235430000", "1390235423000"));
        dataStructureWrapper.addNotification(notification1);
        dataStructureWrapper.addNotification(notification2);
        dataStructureWrapper.addNotification(notification3);
        dataStructureWrapper.addNotification(notification4);

        HashMap<String, TreeSet<Notification>> usersData = dataStructureWrapper.getNotificationStructureByUserId("286416b4-7eac-433c-876c-339dfd8bcd68");
        Assertions.assertNull(dataStructureWrapper.getNotificationStructureByUserId("1234"));
        //we inserted three not. from same user
        Assertions.assertEquals(usersData.size(), 2);

        Assertions.assertEquals(usersData.containsKey("SITE_BLOCKED"), true);
        Assertions.assertEquals(usersData.containsKey("OTHER"), false);
        Assertions.assertEquals(usersData.get("SITE_BLOCKED").size(), 2);
        Assertions.assertEquals(usersData.get("CAP_EXTENDED").size(), 1);

        Assertions.assertTrue(usersData.get("SITE_BLOCKED").contains(notification1));
        Assertions.assertTrue(usersData.get("SITE_BLOCKED").contains(notification2));
    }

    @Test
    public void dataStructureHierarchyOrderTest() {
        final Notification notification1 = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a9e6", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd68", "SITE_BLOCKED", "ADULT", "", "", "", "Site/App block", "Adult", "1390235400000", "1390235423000"));
        final Notification notification2 = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a9e5", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd68", "SITE_BLOCKED", "ADULT", "", "", "", "Site/App block", "Adult", "1390235450000", "1390235423000"));
        final Notification notification3 = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a9e3", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd68", "SITE_BLOCKED", "ADULT", "", "", "", "Site/App block", "Adult", "1390235430000", "1390235423000"));
        final Notification notification4 = new Notification(Arrays.asList("2affbe87-fd11-4e38-997c-7adf3dc6a956", "48f31433-0fd0-433c-ba93-ee1080cb0e09", "286416b4-7eac-433c-876c-339dfd8bcd50", "OTHER", "ADULT", "", "", "", "Site/App block", "Adult", "1390235600000", "1390235423000"));
        dataStructureWrapper.addNotification(notification1);
        dataStructureWrapper.addNotification(notification2);
        dataStructureWrapper.addNotification(notification3);
        dataStructureWrapper.addNotification(notification4);

        HashMap<String, TreeSet<Notification>> usersData = dataStructureWrapper.getNotificationStructureByUserId("286416b4-7eac-433c-876c-339dfd8bcd68");
        List<Notification> orderedList = usersData.get("SITE_BLOCKED").stream().collect(Collectors.toList());

        Assertions.assertEquals("2affbe87-fd11-4e38-997c-7adf3dc6a9e6", orderedList.get(0).getNotificationGuid());
        Assertions.assertEquals("2affbe87-fd11-4e38-997c-7adf3dc6a9e3", orderedList.get(1).getNotificationGuid());
        Assertions.assertEquals("2affbe87-fd11-4e38-997c-7adf3dc6a9e5", orderedList.get(2).getNotificationGuid());
    }
}
