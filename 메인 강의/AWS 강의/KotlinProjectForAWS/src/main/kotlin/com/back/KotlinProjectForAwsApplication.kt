package com.back

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinProjectForAwsApplication

fun main(args: Array<String>) {
	runApplication<KotlinProjectForAwsApplication>(*args)
}
