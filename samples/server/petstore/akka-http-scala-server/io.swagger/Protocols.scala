package io.swagger

import io.swagger.model._
import spray.json.DefaultJsonProtocol

trait Protocols extends DefaultJsonProtocol {
    implicit val ApiResponseFormat = jsonFormat(ApiResponse, "code", "_type", "message")
    implicit val CategoryFormat = jsonFormat(Category, "id", "name")
    implicit val OrderFormat = jsonFormat(Order, "id", "petId", "quantity", "shipDate", "status", "complete")
    implicit val PetFormat = jsonFormat(Pet, "id", "category", "name", "photoUrls", "tags", "status")
    implicit val TagFormat = jsonFormat(Tag, "id", "name")
    implicit val UserFormat = jsonFormat(User, "id", "username", "firstName", "lastName", "email", "password", "phone", "userStatus")
}

