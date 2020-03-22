package models

import play.api.data.Form
import play.api.data.Forms._

import scala.collection.mutable.ListBuffer

case class SignUpModel(username: String, confirmedPassword: String)

object SignUpModel {

  val signUpForm: Form[SignUpModel] = Form(
    mapping(
      "username" -> nonEmptyText,
      "enterPassword" -> nonEmptyText,
     ) (SignUpModel.apply)(SignUpModel.unapply)
  )
  def checkUserDoesNotExist(userDetails: SignUpModel): Boolean = {
    for (i <- 0 until LoginModel.userList.length) {
      if (LoginModel.userList(i).username == userDetails.username) {
        false
      }
    }
    true
  }

  def addUser(username: String, password: String): ListBuffer[LoginModel] = {
    LoginModel.userList += LoginModel(username, password)
  }


}
