package com.coderhouse.controller;

import com.coderhouse.service.EmailService;
import com.coderhouse.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coder-house")
public class MessageController {

    private final EmailService emailService;
    private final MessageService messageService;

    @PostMapping("/mensajes/email/send")
    public void sendEmail() {
        emailService.sendEmail();
    }

    @PostMapping("/mensajes/email/send/attachment")
    public void sendEmailWithAttachment() throws MessagingException {
        emailService.sendEmailWithAttachment();
    }

    @PostMapping("/mensajes/sms/send")
    public void sendSms() {
        messageService.sendMessageToSms();
    }


    @PostMapping("/mensajes/whatsapp/send")
    public void sendMessageToWhatsApp() {
        messageService.sendMessageToWhatsApp();
    }
}
