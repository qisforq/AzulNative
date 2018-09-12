package azul.paleblue.foundation.azul.invite

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class InviteFriendsViewModel : ViewModel() {

  var model: InviteFriendsModel? = null

  private val _inviteCode: MutableLiveData<String> = MutableLiveData<String>()

  val inviteCode: LiveData<String>
    get() {
      if (_inviteCode.value == null) {
        doAsync {
          val future = model!!.generateInvite()
          uiThread {
            _inviteCode.value = future.get()
          }
        }
      }
      return _inviteCode
    }

  val invitesRemaining: MutableLiveData<Int> = MutableLiveData<Int>()

  init {
    invitesRemaining.value = 0
  }

}
