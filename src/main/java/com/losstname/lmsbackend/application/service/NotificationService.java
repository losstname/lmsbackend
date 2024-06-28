package com.losstname.lmsbackend.application.service;

import com.losstname.lmsbackend.domain.model.notification.Notification;
import com.losstname.lmsbackend.domain.repository.NotificationRepository;
import com.losstname.lmsbackend.interfaces.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by umrwhk(umrwhk@gmail.com)
 */
@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<NotificationDto> getAllNotifications() {
        return notificationRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<NotificationDto> getNotificationsByUser(Long userId) {
        return notificationRepository.findByUserId(userId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public NotificationDto getNotificationById(Long id) {
        return notificationRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    public NotificationDto createNotification(NotificationDto notificationDto) {
        Notification notification = convertToEntity(notificationDto);
        Notification savedNotification = notificationRepository.save(notification);
        return convertToDto(savedNotification);
    }

    public NotificationDto updateNotification(Long id, NotificationDto notificationDto) {
        return notificationRepository.findById(id).map(notification -> {
            notification.setMessage(notificationDto.getMessage());
            notification.setRead(notificationDto.isRead());
            Notification updatedNotification = notificationRepository.save(notification);
            return convertToDto(updatedNotification);
        }).orElse(null);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    private NotificationDto convertToDto(Notification notification) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notification.getId());
        notificationDto.setUserId(notification.getUser().getId());
        notificationDto.setMessage(notification.getMessage());
        notificationDto.setRead(notification.isRead());
        return notificationDto;
    }

    private Notification convertToEntity(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setId(notificationDto.getId());
        notification.setMessage(notificationDto.getMessage());
        notification.setRead(notificationDto.isRead());
        return notification;
    }
}
