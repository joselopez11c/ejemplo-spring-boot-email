package com.coderhouse.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties
public class ApplicationProperties {

    @Value(value = "${twilio.account.sid}")
    private String twilioAccountId;

    @Value(value = "${twilio.auth.token}")
    private String twilioToken;

    @Value(value = "${twilio.number-sms}")
    private String twilioNumberSms;

    @Value(value = "${twilio.number-whatsapp}")
    private String twilioNumberWhatsapp;

}
