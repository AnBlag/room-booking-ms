package ru.roombooking.mail.service.mail;

public interface MailSenderService {
    void send(String emailTo, String subject, String message);
}
