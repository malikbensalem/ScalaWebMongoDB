package controllers

import javax.inject.{Inject, Singleton}
import models.LoginModel
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}

@Singleton
class LoginController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def login(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.login(LoginModel.loginForm))
  }

  def loginSubmit(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    LoginModel.loginForm.bindFromRequest.fold({ formWithErrors =>
      BadRequest(views.html.login(formWithErrors))
    }, { loginDetails =>
      if (LoginModel.checkIfUserIsValid(loginDetails))
        Redirect(routes.HomeController.index()).withSession(request.session + ("username" -> loginDetails.username))
      else
        BadRequest("Incorrect username or password")
    })
  }

}
