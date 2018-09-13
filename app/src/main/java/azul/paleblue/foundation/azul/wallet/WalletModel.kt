package azul.paleblue.foundation.azul.wallet

import android.arch.lifecycle.LiveData
import azul.paleblue.foundation.azul.network.ApiClient
import foundation.paleblue.azul.proto.Transaction
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import java.math.BigDecimal
import java.util.concurrent.Future

class WalletModel(val apiClient: ApiClient) {

  fun currentBalance(): Future<AccountBalance> {
    return doAsyncResult {
      //val reply = apiClient.checkBalance()
      //val bitcoin = CurrencyBalance("BTC", satoshisToBtc(reply.satoshis))

      val bitcoin = CurrencyBalance("BTC", satoshisToBtc(100000))

      AccountBalance(listOf(bitcoin))
    }
  }

  private fun satoshisToBtc(satoshis: Long): BigDecimal {
    return BigDecimal(satoshis).divide(BigDecimal(100_000_000))
  }

  // fun sendMoney(destination: String, satoshis: Long): Future<Boolean> {
  //   return doAsyncResult {
  //     val reply = apiClient.sendMoney(destination, satoshis)

  //     when (reply.status) {
  //       SendMoneyReplyStatus.FAILURE -> false
  //       SendMoneyReplyStatus.SUCCESS -> {
  //         storeSessionToken(username, reply.sessionToken)
  //         true
  //       }
  //       else -> {
  //         throw RuntimeException("Unknown login reply status!")
  //       }
  //     }
  //     false
  //   }
  // }
  //


}
