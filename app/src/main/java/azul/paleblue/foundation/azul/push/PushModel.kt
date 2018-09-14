package azul.paleblue.foundation.azul.push

import azul.paleblue.foundation.azul.network.ApiClient
import azul.paleblue.foundation.azul.persistence.KeyValueStore
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import foundation.paleblue.azul.proto.LoginReply.LoginReplyStatus
import org.jetbrains.anko.doAsyncResult
import java.util.concurrent.Future

class PushModel(val apiClient: ApiClient, val keyValueStore: KeyValueStore) {

  init {
    registerForPushIfNecessary()
  }

  fun registerForPushIfNecessary() {
    if (null == keyValueStore.getPushToken()) {
      getCurrentToken().addOnSuccessListener {
        registerForPush(it.token)
      }
    }
  }

  // Unclear if we need to invoke this on app install in order to properly pass the push token to the server
  private fun getCurrentToken(): Task<InstanceIdResult> {
    return FirebaseInstanceId.getInstance().instanceId
  }

  fun registerForPush(token: String): Future<Boolean> {
    return doAsyncResult {
      if (token == keyValueStore.getPushToken()) {
        true
      } else {
        when (apiClient.registerForPush(token)) {
          LoginReplyStatus.LOGIN_FAILURE -> false
          LoginReplyStatus.LOGIN_SUCCESS -> {
            keyValueStore.storePushToken(token)
            true
          }
          else -> {
            throw RuntimeException("Unknown registerForPush reply status!")
          }
        }
        false
      }
    }
  }
}
