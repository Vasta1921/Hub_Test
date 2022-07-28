package com.example.test.controller

import com.example.test.amqp.RabbitSender
import com.example.test.model.allSum
import com.example.test.services.RestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class RestController {

    @Autowired
    lateinit var rabbitSender:RabbitSender
    @Autowired
    lateinit var restService: RestService



    @GetMapping("/rabbitallsum")
    fun getRabbitAllSum(@RequestParam a:Int,b:Int):allSum?{
        return rabbitSender.getSum(a,b)
    }

    @GetMapping("/test")
    fun getTestRest() : String? {
        return restService.getRestTest()
    }
}