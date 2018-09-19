package azul.paleblue.foundation.azul.account

import azul.paleblue.foundation.azul.network.ApiClient
import azul.paleblue.foundation.azul.persistence.KeyValueStore
import foundation.paleblue.azul.proto.LoginReply.Status as LoginReplyStatus
import foundation.paleblue.azul.proto.RegisterReply
import foundation.paleblue.azul.proto.RegisterReply.Status as RegisterReplyStatus
import org.jetbrains.anko.doAsyncResult
import java.util.concurrent.Future

class AccountModel(val apiClient: ApiClient, val kvStore: KeyValueStore) {

  fun login(username: String, password: String): Boolean {
    val reply = apiClient.login(username, password)

    return when (reply.status) {
      LoginReplyStatus.FAILURE -> false
      LoginReplyStatus.SUCCESS -> {
        kvStore.storeSessionToken(reply.sessionToken)
        true
      }
      else -> {
        throw RuntimeException("Unknown login reply status!")
      }
    }
  }

  fun register(username: String, password: String): String? {
    val reply = apiClient.register(username, password)

    return when (reply.status) {
      RegisterReplyStatus.FAILURE -> reply.message
      RegisterReplyStatus.SUCCESS -> {
        kvStore.storeSessionToken(reply.sessionToken)
        null
      }
      else -> {
        throw RuntimeException("Unknown register reply status!")
      }
    }
  }
}
