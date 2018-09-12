package azul.paleblue.foundation.azul

import android.app.Application
import azul.paleblue.foundation.azul.invite.InviteModel
import azul.paleblue.foundation.azul.network.ApiClient
import azul.paleblue.foundation.azul.push.PushModel

val host = "192.168.1.95"
val port = 50051

class AzulApplication : Application() {

  val apiClient: ApiClient = ApiClient(host, port)
  val inviteFriendsModel = InviteModel(apiClient)
  val pushModel: PushModel = PushModel(apiClient)

  override fun onCreate() {
    super.onCreate()
  }

}
