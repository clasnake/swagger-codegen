package io.swagger.api

import java.io._
import java.util.Date
import io.swagger._
import io.swagger.model._
import scala.collection.immutable.Seq
import io.swagger.model.User
import io.finch.circe._
import io.circe.generic.semiauto._
import com.twitter.concurrent.AsyncStream
import com.twitter.finagle.Service
import com.twitter.finagle.Http
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.http.exp.Multipart.{FileUpload, InMemoryFileUpload, OnDiskFileUpload}
import com.twitter.util.Future
import com.twitter.io.Buf
import io.finch._, items._
import java.io.File

object UserApi {
/**
* Compiles all service endpoints.
* @return Bundled compilation of all service endpoints.
*/
def routes(da: DataAccessor) =
        createUser(da) ~
        createUsersWithArrayInput(da) ~
        createUsersWithListInput(da) ~
        deleteUser(da) ~
        getUserByName(da) ~
        loginUser(da) ~
        logoutUser(da) ~
        updateUser(da)

        /**
        * 
        * @return A route representing a Unit
        */
        private def createUser(da: DataAccessor) =
        path("user"  / entity(as[User])) = { { (body) =>
        post {
            da.User_createUser(body)
            NoContent[Unit]
        } handle {
        case e: Exception => BadRequest(e)
        }
        }

        /**
        * 
        * @return A route representing a Unit
        */
        private def createUsersWithArrayInput(da: DataAccessor) =
        path("user" / "createWithArray"  / entity(as[Seq[User]])) = { { (body) =>
        post {
            da.User_createUsersWithArrayInput(body)
            NoContent[Unit]
        } handle {
        case e: Exception => BadRequest(e)
        }
        }

        /**
        * 
        * @return A route representing a Unit
        */
        private def createUsersWithListInput(da: DataAccessor) =
        path("user" / "createWithList"  / entity(as[Seq[User]])) = { { (body) =>
        post {
            da.User_createUsersWithListInput(body)
            NoContent[Unit]
        } handle {
        case e: Exception => BadRequest(e)
        }
        }

        /**
        * 
        * @return A route representing a Unit
        */
        private def deleteUser(da: DataAccessor) =
        path("user" / string ) = { { (username) =>
        delete {
            da.User_deleteUser(username)
            NoContent[Unit]
        } handle {
        case e: Exception => BadRequest(e)
        }
        }

        /**
        * 
        * @return A route representing a User
        */
        private def getUserByName(da: DataAccessor) =
        path("user" / string ) = { { (username) =>
        get {
            Ok(da.User_getUserByName(username))
        } handle {
        case e: Exception => BadRequest(e)
        }
        }

        /**
        * 
        * @return A route representing a String
        */
        private def loginUser(da: DataAccessor) =
        path("user" / "login"  / String / String) = { { (username, password) =>
        get {
            Ok(da.User_loginUser(username, password))
        } handle {
        case e: Exception => BadRequest(e)
        }
        }

        /**
        * 
        * @return A route representing a Unit
        */
        private def logoutUser(da: DataAccessor) =
        path("user" / "logout" ) = { {  =>
        get {
            da.User_logoutUser()
            NoContent[Unit]
        } handle {
        case e: Exception => BadRequest(e)
        }
        }

        /**
        * 
        * @return A route representing a Unit
        */
        private def updateUser(da: DataAccessor) =
        path("user" / string  / entity(as[User])) = { { (username, body) =>
        put {
            da.User_updateUser(username, body)
            NoContent[Unit]
        } handle {
        case e: Exception => BadRequest(e)
        }
        }


implicit private def fileUploadToFile(fileUpload: FileUpload) : File = {
fileUpload match {
case upload: InMemoryFileUpload =>
bytesToFile(Buf.ByteArray.Owned.extract(upload.content))
case upload: OnDiskFileUpload =>
upload.content
case _ => null
}
}

private def bytesToFile(input: Array[Byte]): java.io.File = {
val file = File.createTempFile("tmpUserApi", null)
val output = new FileOutputStream(file)
output.write(input)
file
}

// This assists in params(string) application (which must be Seq[A] in parameter list) when the param is used as a List[A] elsewhere.
implicit def seqList[A](input: Seq[A]): List[A] = input.toList