package azul.paleblue.foundation.azul.wallet.send

import android.arch.lifecycle.LiveData
import azul.paleblue.foundation.azul.network.ApiClient
import foundation.paleblue.azul.proto.Transaction
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import java.math.BigDecimal
import java.util.concurrent.Future

class SendMoneyModel(val apiClient: ApiClient) {

  fun sendMoney(destination: String, satoshis: Long): Future<Boolean> {
    return doAsyncResult {
      val reply = apiClient.sendMoney(destination, satoshis)

      when (reply.status) {
        SendMoneyReplyStatus.FAILURE -> false
        SendMoneyReplyStatus.SUCCESS -> {
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

}
