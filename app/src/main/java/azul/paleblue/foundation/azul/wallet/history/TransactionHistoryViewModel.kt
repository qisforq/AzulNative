package azul.paleblue.foundation.azul.wallet.history

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import foundation.paleblue.azul.proto.Transaction
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TransactionHistoryViewModel: ViewModel() {

  var model: TransactionHistoryModel? = null

  private val _transactions: MutableLiveData<List<Transaction>> = MutableLiveData<List<Transaction>>()

  init {
    _transactions.value = mutableListOf()
  }

  val transactions: LiveData<List<Transaction>>
    get() {
      if (_transactions.value!!.isEmpty()) {
        doAsync {
          val future = model!!.transactionHistory()
          uiThread {
            _transactions.value = future.get()
          }
        }
      }
      return _transactions
    }

}
