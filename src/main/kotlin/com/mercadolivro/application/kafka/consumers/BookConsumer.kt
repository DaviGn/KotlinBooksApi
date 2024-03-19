package com.mercadolivro.application.kafka.consumers

import com.mercadolivro.application.kafka.events.book.BookEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class BookConsumer {
    private val logger: Logger = LoggerFactory.getLogger(BookConsumer::class.java);

    @KafkaListener(topics = ["book-created-event"])
    fun consume(@Payload event: BookEvent) {
        logger.info("New Book received | $event");
    }
}