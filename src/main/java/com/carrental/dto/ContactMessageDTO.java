package com.carrental.dto;

public record ContactMessageDTO(Long id, String senderName, String messageTitle, String messageBody, String email) {
}
