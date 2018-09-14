package azul.paleblue.foundation.azul.wallet

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SendMoneyViewModel : ViewModel() {

  var model: WalletModel? = null

  private val _balance: MutableLiveData<AccountBalance> = MutableLiveData<AccountBalance>()

  val balance: LiveData<AccountBalance>
    get() {
      if (_balance.value == null) {
        doAsync {
          val future = model!!.currentBalance()
          uiThread {
            _balance.value = future.get()
          }
        }
      }
      return _balance
    }

}
