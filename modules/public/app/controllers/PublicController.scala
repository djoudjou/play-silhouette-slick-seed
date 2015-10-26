package controllers.public

import com.mohiva.play.silhouette.api.{ Environment, Silhouette }
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.impl.providers.SocialProviderRegistry
import javax.inject.Inject
import models.User
import play.api.i18n.MessagesApi
import scala.concurrent.Future

/** Controls actions that do not require authenticated users.
  * @param messagesApi Localized messages for this webapp.
  * @param env The Silhouette environment.
  * @param socialProviderRegistry The social provider registry. */
class PublicController @Inject() (
  val messagesApi: MessagesApi,
  val env: Environment[User, CookieAuthenticator],
  socialProviderRegistry: SocialProviderRegistry)
  extends Silhouette[User, CookieAuthenticator] {

  /** Handles administrative user functions. */
  def index = SecuredAction.async { implicit request =>
    Future.successful(Ok(views.html.publicHome(request.identity)))
  }
}
