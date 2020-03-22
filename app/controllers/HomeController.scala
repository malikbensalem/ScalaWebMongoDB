package controllers

import authentication.AuthenticationAction
import javax.inject._
import models.LoginModel
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents, authAction: AuthenticationAction) extends AbstractController(cc) {

  def index: Action[AnyContent] = authAction {
    readAllUsers
    Ok(views.html.index(LoginModel.userList.toString()))
  }
  def readUser(username: String): Unit = {
    for(user<-LoginModel.userList){
      if (user.username==username){
        println("\n\nUsername: "+user.username+"\nPassword: "+user.password)
      }
    }
  }
  def deleteUser(username: String): Unit = {
    for(user<-LoginModel.userList){
      if (user.username==username){
        println("Username: "+user.username+"\nPassword: "+user.password)

      }
    }
  }
  def readAllUsers: Unit = {
    for(user<-LoginModel.userList){
        println("Username"+user.username+"\nPassword"+user.password)
    }
  }

}
