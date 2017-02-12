package io.swagger.model


/**
* Describes the result of uploading an image resource
* @param code 
* @param _type 
* @param message 
*/
case class ApiResponse(code: Option[Int],
        _type: Option[String],
        message: Option[String]
        )
