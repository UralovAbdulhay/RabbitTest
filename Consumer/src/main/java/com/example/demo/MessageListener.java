package com.example.demo;

import com.example.demo.payloads.Car;
import com.example.demo.payloads.Student;
import com.example.demo.payloads.Worker;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = "car")
    public void listener(Car message) {
        System.out.println("CAR = " + message);
    }


    @RabbitListener(queues = "student")
    public void listener(Student message) {
        System.out.println("STUDENT = " + message);
    }


    @RabbitListener(queues = "worker")
    public void listener(Worker message) {
        System.out.println("WORKER = " + message);
    }


    @RabbitListener(queues = "message_queue")
    public void listener(Object message) {
        System.out.println("COMMON = " + message);
    }

}
