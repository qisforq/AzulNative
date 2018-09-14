package azul.paleblue.foundation.azul.invite

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import azul.paleblue.foundation.azul.persistence.KeyValueStore
import org.jetbrains.anko.*

class InstallReceiver: BroadcastReceiver(), AnkoLogger {
  override fun onReceive(context: Context?, intent: Intent?) {
    verbose("InstallReceiver got intent")
    if (intent!!.action == "com.android.vending.INSTALL_REFERRER") {
      val referrer = intent.extras.getString("referrer")
      val inviteCode = intent.extras.getString("inviteCode")
      info("InstallReceiver got install with referrer: $referrer and code: $inviteCode")
      KeyValueStore(context!!).storeCode(inviteCode)
    }
  }

}
