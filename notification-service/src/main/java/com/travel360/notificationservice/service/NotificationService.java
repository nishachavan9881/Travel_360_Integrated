package com.travel360.notificationservice.service;

import com.travel360.notificationservice.entity.Notification;

import java.util.List;

public interface NotificationService {

    List<Notification> getNotificationsByUserId(Long userId);

    Notification createNotification(Notification notification);

    List<Notification> getAllNotifications();

    Notification getNotificationById(Long id);

    void deleteNotification(Long id);
}
