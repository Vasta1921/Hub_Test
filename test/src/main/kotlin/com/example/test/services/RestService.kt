package com.example.test.services

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.slf4j.MDC
import org.springframework.stereotype.Service

@Service
class RestService {

    val url = "http://localhost:8081"


    fun getRestTest(): String{
        val transport = HttpGet( "http://localhost:8081/re/test")
        transport.setHeader("Accept-Charset", "UTF-8")
        val httpClient = HttpClients.custom().build()
        val responseHttp = httpClient.execute(transport)
        val answer = EntityUtils.toString(responseHttp.entity)
        val response = ObjectMapper().readValue(answer,String :: class.java)
        return response

    }
}