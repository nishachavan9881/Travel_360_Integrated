package com.travel360.notificationservice.service;

import com.travel360.notificationservice.entity.Notification;
import com.travel360.notificationservice.repository.NotificationRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository repository;

    @InjectMocks
    private NotificationServiceImpl service;

    // ✅ TEST 1: Create Notification
    @Test
    void testCreateNotification() {

        Notification n = new Notification();
        n.setMessage("Test Notification");

        when(repository.save(any(Notification.class))).thenReturn(n);

        Notification result = service.createNotification(n);

        assertNotNull(result);
        assertEquals("Test Notification", result.getMessage());

        verify(repository, times(1)).save(any(Notification.class));
    }

    // ✅ TEST 2: Get All Notifications
    @Test
    void testGetAllNotifications() {

        when(repository.findAll()).thenReturn(List.of(new Notification(), new Notification()));

        List<Notification> result = service.getAllNotifications();

        assertEquals(2, result.size());
    }

    // ✅ TEST 3: Get Notification by ID
    @Test
    void testGetNotificationById() {

        Notification n = new Notification();
        n.setNotificationId(1L);

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(n));

        Notification result = service.getNotificationById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getNotificationId());
    }

    // ✅ TEST 4: Delete Notification
    @Test
    void testDeleteNotification() {

        Long id = 1L;

        when(repository.existsById(id)).thenReturn(true);

        service.deleteNotification(id);

        verify(repository, times(1)).deleteById(id);
    }
}
