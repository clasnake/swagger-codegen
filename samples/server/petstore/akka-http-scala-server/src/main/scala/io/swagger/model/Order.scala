package io.swagger.model

import java.time.LocalDateTime

/**
* An order for a pets from the pet store
* @param id 
* @param petId 
* @param quantity 
* @param shipDate 
* @param status Order Status
* @param complete 
*/
case class Order(id: Option[Long],
        petId: Option[Long],
        quantity: Option[Int],
        shipDate: Option[LocalDateTime],
        status: Option[String],
        complete: Option[Boolean]
        )
