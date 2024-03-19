package com.mercadolivro.application.kafka.producers

import com.mercadolivro.application.kafka.events.book.BookEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*

@Component
class BookProducer(private val kafkaTemplate: KafkaTemplate<String, BookEvent>) {
    private val topicName = "book-created-event";

    fun sendEvent(event: BookEvent) {
        val uuid = UUID.randomUUID();
        val message = createMessageWithHeaders(uuid.toString(), event, topicName)
        kafkaTemplate.send(message)
    }

    private fun createMessageWithHeaders(
        messageId: String,
        event: BookEvent,
        topic: String
    ): Message<BookEvent> {
        return MessageBuilder.withPayload(event)
            .setHeader("hash", event.hashCode())
            .setHeader("version", "1.0.0")
            .setHeader("endOfLife", LocalDate.now().plusDays(1L))
            .setHeader("type", "fct")
            .setHeader("cid", messageId)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader(KafkaHeaders.KEY, messageId)
            .build()
    }
}