package controllers

import javax.inject.{Inject, Singleton}
import models.SignUpModel
import play.api.mvc._

@Singleton
class SignUpController @Inject() (cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def signUp(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.signup(SignUpModel.signUpForm))
  }

  def signUpSubmit(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    SignUpModel.signUpForm.bindFromRequest.fold({ formWithErrors =>
      BadRequest(views.html.signup(formWithErrors))
    }, { signUpDetails =>
      if (SignUpModel.checkUserDoesNotExist(signUpDetails)) {
        SignUpModel.addUser(signUpDetails.username, signUpDetails.confirmedPassword)
        Redirect(routes.HomeController.index()).withSession(request.session + ("username" -> signUpDetails.username))
      } else {
        BadRequest("This Username already exists")
      }
    })
  }

}
