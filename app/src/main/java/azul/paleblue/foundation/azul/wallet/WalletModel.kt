package azul.paleblue.foundation.azul.wallet

import android.arch.lifecycle.LiveData
import azul.paleblue.foundation.azul.network.ApiClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import java.math.BigDecimal
import java.util.concurrent.Future

class WalletModel(val apiClient: ApiClient) {

  //val balance: LiveData<AccountBalance> = LiveData<AccountBalance>()
  //val transactionHistory: LiveData<Tran>
  
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

//  fun checkBalance(username: String, password: String): Future<Boolean> {
////    doAsync {
////      val reply = apiClient.checkBalance()
////
////    }
//  }
  
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
  // fun transactionHistory(): Future<List<Transaction>> {
  //   return doAsyncResult {
  //     val reply = apiClient.login(username, password)

  //     when (reply.status) {
  //       LoginReplyStatus.LOGIN_FAILURE -> false
  //       LoginReplyStatus.LOGIN_SUCCESS -> {
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
  
}
