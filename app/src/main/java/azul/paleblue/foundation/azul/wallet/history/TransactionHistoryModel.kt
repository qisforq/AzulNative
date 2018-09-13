package azul.paleblue.foundation.azul.wallet.history

import azul.paleblue.foundation.azul.network.ApiClient
import foundation.paleblue.azul.proto.Transaction
import org.jetbrains.anko.doAsyncResult
import java.util.concurrent.Future

class TransactionHistoryModel(val apiClient: ApiClient) {
  fun transactionHistory(): Future<List<Transaction>> {
    return doAsyncResult {
      listOf(
          Transaction.newBuilder()
              .setSender("Alice")
              .setRecipient("Bob")
              .setSatoshis(1000L)
              .setTimestamp("ISO time goes here")
              .build(),
          Transaction.newBuilder()
              .setSender("Charles")
              .setRecipient("Alice")
              .setSatoshis(1337L)
              .setTimestamp("ISO time goes here")
              .build()
      )
    }
  }
}
