package com.vivatech.service;

public interface EmailService {

	/**
     * Send a simple email.
     *
     * @param to      Recipient email address.
     * @param subject Email subject.
     * @param body    Email body.
     */
    void sendEmail(String to, String subject, String body);
}
