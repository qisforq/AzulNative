package azul.paleblue.foundation.azul

import android.app.Application
import azul.paleblue.foundation.azul.invite.InviteFriendsModel
import azul.paleblue.foundation.azul.network.ApiClient
import azul.paleblue.foundation.azul.push.PushModel

val host = "192.168.1.95"
val port = 50051

class AzulApplication : Application() {

  val apiClient: ApiClient = ApiClient(host, port)
  lateinit var inviteFriendsModel: InviteFriendsModel
  val pushModel: PushModel = PushModel(apiClient)

  override fun onCreate() {
    super.onCreate()
 }

}
