package com.example.test.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class Dto(
    @get:JsonProperty("number_one") var numberOne: Int? = null,
    @get:JsonProperty("number_two") var numberTwo: Int? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
open class Payload<T>(
    @get:JsonProperty("message_id")
    val messageId: String? = null,
    @get:JsonProperty("request_id")
    val requestId: String? = null,
    @get:JsonProperty("message_source")
    val messageSource: String? = null,
    @get:JsonProperty("message_type")
    val messageType: String? = null,
    @get:JsonProperty("data")
    val data: T? = null,
    @get:JsonProperty("error")
    val error: String? = null
) {
    override fun toString(): String {
        return "Payload(message_id=$messageId, data=$data)"
    }
}

data class allSum(
    @get:JsonProperty("sum") var sum: Double? = null,
    @get:JsonProperty("minus") var minus: Double? = null,
    @get:JsonProperty("multiplication") var multiplication : Double? = null,
    @get:JsonProperty("division") var division: Double? = null,
)