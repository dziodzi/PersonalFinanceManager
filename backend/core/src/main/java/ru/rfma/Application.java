package ru.rfma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ru.rfma.services.EmailSenderService;

import javax.mail.MessagingException;

@SpringBootApplication
public class Application {

//    @Autowired
//    private EmailSenderService senderService;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerMail() throws MessagingException {
//        senderService.sendSimpleEmail("finance.management.app@gmail.com",
//                "This is email body",
//                "This is email subject");
//
//    }
}
