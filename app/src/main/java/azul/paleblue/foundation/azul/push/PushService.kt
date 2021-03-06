package azul.paleblue.foundation.azul.push

import azul.paleblue.foundation.azul.AzulApplication
import azul.paleblue.foundation.azul.network.ApiClient
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.google.firebase.messaging.*
import org.jetbrains.anko.*
import java.util.concurrent.Future


class PushService : FirebaseMessagingService(), AnkoLogger {

  lateinit var pushModel: PushModel

  override fun onCreate() {
    val app = application as AzulApplication
    pushModel = app.pushModel
  }

  /**
   * Called if InstanceID token is updated. This may occur if the security of
   * the previous token had been compromised. Note that this is called when the InstanceID token
   * is initially generated so this is where you would retrieve the token.
   */
  override fun onNewToken(token: String?) {
    debug("Refreshed token: " + token)

    if (token != null) {
      pushModel.registerForPush(token)
    }
  }

  override fun onMessageReceived(message: RemoteMessage?) {
    super.onMessageReceived(message)
    info("Push Received: $message")
  }

}
