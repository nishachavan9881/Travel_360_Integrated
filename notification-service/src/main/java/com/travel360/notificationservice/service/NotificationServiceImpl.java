package com.travel360.notificationservice.service;

import com.travel360.notificationservice.entity.Notification;
import com.travel360.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;

    public NotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Notification createNotification(Notification notification) {
        notification.setCreatedDate(LocalDateTime.now());
        return repository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }

    @Override
    public Notification getNotificationById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Notification not found"));
    }

    @Override
    public void deleteNotification(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Notification not found");
        }

        repository.deleteById(id);

    }

    @Override
    public List<Notification> getNotificationsByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

}
