package com.coderhouse.service.impl;

import com.coderhouse.config.ApplicationProperties;
import com.coderhouse.service.MessageService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwilioServiceImpl implements MessageService {

    private final ApplicationProperties properties;

    @Override
    public void sendMessageToSms() {
        Twilio.init(properties.getTwilioAccountId(), properties.getTwilioToken());
        Message message = Message.creator(
                new PhoneNumber("+51991814042"),
                new PhoneNumber(properties.getTwilioNumberSms()),
                "Hello from Coderhouse SMS"
        ).create();

        log.info(message.getSid());
    }

    @Override
    public void sendMessageToWhatsApp() {
        Twilio.init(properties.getTwilioAccountId(), properties.getTwilioToken());
        Message message = Message.creator(
                new PhoneNumber(whatsApp("+51991814042")),
                new PhoneNumber(whatsApp(properties.getTwilioNumberWhatsapp())),
                "Hello from Spring Boot by WhatsApp").create();

        log.info(message.getSid());
    }

    // whatsapp:elNumeroDeTwilio // whatsapp:ElNumeroAEnviarElMensaje
    private String whatsApp(String number) {
        var value =  String.format("%s%s", "whatsapp:", number);
        log.info(value);
        return value;
    }
}
