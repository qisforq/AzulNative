package azul.paleblue.foundation.azul.invite

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.jetbrains.anko.doAsync

class InviteFriendsViewModel : ViewModel() {

  var model: InviteFriendsModel

  val inviteCode: LiveData<String>
    get() {
      if (_inviteCode.value == null) {
        doAsync {
          _inviteCode.value = model.generateInvite().get()
        }
      }
      return _inviteCode
    }

  private val _inviteCode: MutableLiveData<String> = MutableLiveData<String>()
  val invitesRemaining: MutableLiveData<Int> = MutableLiveData<Int>()

  init {
    invitesRemaining.value = 0
  }

}
