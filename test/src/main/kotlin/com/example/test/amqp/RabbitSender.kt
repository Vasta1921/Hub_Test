package com.example.test.amqp

import com.example.test.amqp.RabbitConfig.Companion.BRANCH
import com.example.test.amqp.RabbitConfig.Companion.EXCHANGE
import com.example.test.model.Dto
import com.example.test.model.Payload
import com.example.test.model.allSum
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.connection.CorrelationData
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import java.util.*

@Service
class RabbitSender(
    private val template: RabbitTemplate
) {

    companion object {
        private val logger = LoggerFactory.getLogger(RabbitSender::class.java)
    }

    fun getSum(a: Int,b:Int): allSum? {
            val branchRequest = Dto(
                a,b
            )
        return send(
            exchange = EXCHANGE,
            key = BRANCH,
            messageType = "request",
            data = branchRequest
        )
    }


    private inline fun <reified E> send(exchange: String, key: String, messageType: String, data: E): allSum? {
        val payload = toPayload(messageType, data)
        val correlationId = UUID.randomUUID().toString()
        logger.debug("payload={}", payload)
        try {
            val response = template.convertSendAndReceiveAsType(
                exchange,
                key,
                payload,
                { msg ->
                    msg.messageProperties.correlationId = correlationId
                    msg
                },
                CorrelationData(correlationId),
                object : ParameterizedTypeReference<Payload<allSum>>() {}
            )
            return if (response?.data != null)
                response.data
            else
                null
        } catch (e: Exception) {
            logger.error("Error while sending event to exchange=$exchange with key=$key: $payload", e)
            return null
        }
    }

    private inline fun <reified T> toPayload(messageType: String, data: T): Payload<T> {
        val messageId = UUID.randomUUID().toString()
        val requestId = UUID.randomUUID().toString()
        return Payload(
            messageId = messageId,
            requestId = requestId,
            messageType = "response",
            messageSource = "test",
            data = data
        )
    }
}