package com.coderhouse.controller;

import com.coderhouse.exception.ApiRestException;
import com.coderhouse.model.Message;
import com.coderhouse.service.EmailService;
import com.coderhouse.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coder-house")
public class MessageController {

    Logger logger = LogManager.getLogger(MessageController.class);

    private final EmailService emailService;
    private final MessageService messageService;
    private final List<Message> messages;

    @PostConstruct
    void setMessagesInit() {
        messages.addAll(List.of(
                new Message(1L, "Mensaje-ABCD"),
                new Message(2L, "Mensaje-ABCD"),
                new Message(3L, "Mensaje-ABCD"),
                new Message(4L, "Mensaje-ABCE"),
                new Message(5L, "Mensaje-A<E>BCF"))
        );
    }

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

    @GetMapping("/mensajes/example")
    public String getMensajesString() {
        logger.info("GET Request recibido string");
        return "Ejemplo de respuesta";
    }

    @GetMapping("/mensajes/all")
    public List<Message> getMensajesAll() {
        logger.info("GET Request recibido string");
        return messages;
    }

    @PostMapping("/mensajes")
    public Message createMessage(@RequestBody Message message) {
        logger.info("GET Request recibido string");
        messages.add(message);
        return message;
    }

    @GetMapping("/mensajes")
    public List<Message> getMensajesByDescription(@RequestParam String description) {
        logger.info("GET obtener mensajes que sean iguales a la descripción");
        var msjFiltered = messages.stream()
                .filter(mensajes -> mensajes.getDescription().equalsIgnoreCase(description));
        return msjFiltered.collect(Collectors.toList());
    }

    @GetMapping("/mensajes/{id}")
    public Message getMensajeById(@PathVariable Long id) throws ApiRestException {
        logger.info("GET obtener mensaje por el id");

        if (id == 0) {
            throw ApiRestException.builder().message("El identificador del mensaje debe ser mayor a 0").build();
        }
        var msjFiltered = messages.stream()
                .filter(mensajes -> Objects.equals(mensajes.getId(), id));
        return msjFiltered.findFirst().orElse(new Message(0L, "No existe el mensaje"));
    }

}
