package azul.paleblue.foundation.azul.account

import azul.paleblue.foundation.azul.network.ApiClient
import azul.paleblue.foundation.azul.persistence.KeyValueStore
import foundation.paleblue.azul.proto.LoginReply.LoginReplyStatus
import foundation.paleblue.azul.proto.RegisterReply
import foundation.paleblue.azul.proto.RegisterReply.RegisterReplyStatus
import org.jetbrains.anko.doAsyncResult
import java.util.concurrent.Future

class AccountModel(val apiClient: ApiClient, val kvStore: KeyValueStore) {

  fun login(username: String, password: String): Boolean {
    val reply = apiClient.login(username, password)

    return when (reply.status) {
      LoginReplyStatus.LOGIN_FAILURE -> false
      LoginReplyStatus.LOGIN_SUCCESS -> {
        kvStore.storeSessionToken(reply.sessionToken)
        true
      }
      else -> {
        throw RuntimeException("Unknown login reply status!")
      }
    }
  }

  fun register(username: String, password: String): Future<RegisterReply> {
    return doAsyncResult {
      val reply = apiClient.register(username, password)

      when (reply.status) {
        RegisterReplyStatus.SUCCESS -> {
          kvStore.storeSessionToken(reply.sessionToken)
        }
        else -> {
          throw RuntimeException("Unknown register reply status!")
        }
      }
      reply
    }
  }
}
