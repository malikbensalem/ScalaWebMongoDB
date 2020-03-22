package models

import play.api.data.Form
import play.api.data.Forms._

import scala.collection.mutable.ListBuffer

case class LoginModel(username: String, password: String)

object LoginModel {

  var userList: ListBuffer[LoginModel] = ListBuffer(LoginModel("admin", "password"))

  val loginForm: Form[LoginModel] = Form(
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )(LoginModel.apply)(LoginModel.unapply)
  )

  def checkIfUserIsValid(userDetails: LoginModel): Boolean = userList.contains(userDetails)

  def getUsername(username: String): Option[LoginModel] = userList.find(user => user.username == username)



}
