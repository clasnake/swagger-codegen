package io.swagger.model


/**
* A User who is purchasing from the pet store
* @param id 
* @param username 
* @param firstName 
* @param lastName 
* @param email 
* @param password 
* @param phone 
* @param userStatus User Status
*/
case class User(id: Option[Long],
        username: Option[String],
        firstName: Option[String],
        lastName: Option[String],
        email: Option[String],
        password: Option[String],
        phone: Option[String],
        userStatus: Option[Int]
        )
