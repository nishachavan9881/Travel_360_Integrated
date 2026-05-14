package com.travel360.SpringBootApplication.service;

import com.travel360.SpringBootApplication.entity.Notification;

import java.util.List;

public interface NotificationService {

    Notification createNotification(Notification notification);

    List<Notification> getAllNotifications();

    Notification getNotificationById(Long id);

    void deleteNotification(Long id);

    List<Notification> getNotificationsByUserId(Long userId);
}
