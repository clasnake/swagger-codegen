package io.swagger.model

import io.swagger.model.Category
import io.swagger.model.Tag
import scala.collection.immutable.Seq

/**
* A pet for sale in the pet store
* @param id 
* @param category 
* @param name 
* @param photoUrls 
* @param tags 
* @param status pet status in the store
*/
case class Pet(id: Option[Long],
        category: Option[Category],
        name: String,
        photoUrls: Seq[String],
        tags: Option[Seq[Tag]],
        status: Option[String]
        )
