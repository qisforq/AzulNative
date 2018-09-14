package azul.paleblue.foundation.azul.wallet.send

import azul.paleblue.foundation.azul.network.ApiClient
import foundation.paleblue.azul.proto.SendMoneyReply.SendMoneyReplyStatus
import org.jetbrains.anko.doAsyncResult
import java.util.concurrent.Future

class SendMoneyModel(val apiClient: ApiClient) {

  fun sendMoney(destination: String, satoshis: Long): Future<Boolean> {
    return doAsyncResult {
      val reply = apiClient.sendMoney(destination, satoshis)

      when (reply.status) {
        SendMoneyReplyStatus.FAILURE -> false
        SendMoneyReplyStatus.SUCCESS -> {
          true
        }
        else -> {
          throw RuntimeException("Unknown Send Money reply status!")
        }
      }
      false
    }
  }

}
