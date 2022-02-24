package com.example.demo;

import com.example.demo.payloads.Car;
import com.example.demo.payloads.Student;
import com.example.demo.payloads.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final RabbitTemplate template;

    @PostMapping("/common")
    public Object postCommon(@RequestBody Object message) {

        System.out.println(message);
        template.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "common", message);

        return "Tugadi :)" + message.toString();
    }

    @PostMapping("/car")
    public Object postCar(@RequestBody Car message) {

        System.out.println(message);
        message.setId(UUID.randomUUID().toString());

        template.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "car", message);

        return "Tugadi :)" + message.toString();
    }


    @PostMapping("/student")
    public Object post_student(@RequestBody Student message) {
        System.out.println(message);
        message.setId(UUID.randomUUID().toString());
        template.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "student", message);
        return "Tugadi :)" + message.toString();
    }


    @PostMapping("/worker")
    public Object post_worker(@RequestBody Worker message) {
        System.out.println(message);
        message.setId(UUID.randomUUID().toString());
        template.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "worker", message);
        return "Tugadi :)" + message.toString();
    }


}
