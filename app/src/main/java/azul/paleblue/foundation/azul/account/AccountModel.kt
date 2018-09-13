package azul.paleblue.foundation.azul.account

import azul.paleblue.foundation.azul.network.ApiClient
import azul.paleblue.foundation.azul.persistence.InviteCodeStorage
import foundation.paleblue.azul.proto.LoginReply
import foundation.paleblue.azul.proto.LoginReply.LoginReplyStatus
import foundation.paleblue.azul.proto.RegisterReply
import foundation.paleblue.azul.proto.RegisterReply.RegisterReplyStatus
import org.jetbrains.anko.doAsyncResult
import java.util.concurrent.Future

class AccountModel(val apiClient: ApiClient, val kvStore: InviteCodeStorage) {

  fun login(username: String, password: String): Future<Boolean> {
    return doAsyncResult {
      val reply = apiClient.login(username, password)

      when (reply.status) {
        LoginReplyStatus.LOGIN_FAILURE -> false
        LoginReplyStatus.LOGIN_SUCCESS -> {
          storeSessionToken(username, reply.sessionToken)
          true
        }
        else -> {
          throw RuntimeException("Unknown login reply status!")
        }
      }
      false
    }
  }

  private fun storeSessionToken(username: String, sessionToken: String)  {
    // Should we also store the username?'
    kvStore.storeSessionToken(sessionToken)
  }

  fun register(username: String, password: String): Future<RegisterReply> {
    return doAsyncResult {
      val reply = apiClient.register(username, password)

      when (reply.status) {
        RegisterReplyStatus.SUCCESS -> {
          storeSessionToken(username, reply.sessionToken)
        }
        else -> {
          throw RuntimeException("Unknown register reply status!")
        }
      }
      reply
    }
  }
}
