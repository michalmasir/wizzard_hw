package com.wandera.hw.service;

import com.wandera.hw.api.Notification;
import com.wandera.hw.api.User;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DataStructureWrapper {
    private final HashMap<String, Notification> notificationMap;
    private final HashMap<String, HashMap<String, TreeSet<Notification>>> groupedStructure;

    public DataStructureWrapper() {
        this.notificationMap = new HashMap<>();
        this.groupedStructure = new HashMap<>();
    }

    public int size(){
        return notificationMap.size();
    }

    public void addNotification(Notification notification) {
        notificationMap.put(notification.getNotificationGuid(), notification);

        if (groupedStructure.containsKey(notification.getUserGuid())) {
            //user exists
            if (groupedStructure.get(notification.getUserGuid()).containsKey(notification.getEventType())) {
                //type exists
                groupedStructure.get(notification.getUserGuid()).get(notification.getEventType()).add(notification);
            } else {
                //type doesnt exists
                groupedStructure.get(notification.getUserGuid()).put(notification.getEventType(), createNewSet(notification));
            }
        } else {
            //user doesnt exist
            HashMap<String, TreeSet<Notification>> newMap = new HashMap<>();
            newMap.put(notification.getEventType(), createNewSet(notification));
            groupedStructure.put(notification.getUserGuid(), newMap);
        }
    }

    public Notification getNotification(String notificationGuid) {
        return notificationMap.get(notificationGuid);
    }

    public HashMap<String, TreeSet<Notification>> getNotificationStructureByUserId(String userGuid) {
        return groupedStructure.get(userGuid);
    }

    public boolean deleteNotification(String notificationGuid) {

        if (notificationMap.containsKey(notificationGuid)) {
            Notification notification = notificationMap.get(notificationGuid);
            groupedStructure.get(notificationGuid).get(notification.getEventType()).remove(notificationGuid);
            notificationMap.remove(notificationGuid);
            return true;
        }else{
            return false;
        }
    }

    public List<User> getUserList(){
        return groupedStructure.keySet().stream().map(u -> new User(u)).collect(Collectors.toList());
    }

    private TreeSet<Notification> createNewSet(Notification notification) {
        TreeSet<Notification> set = new TreeSet<>();
        set.add(notification);
        return set;
    }
}
