package azul.paleblue.foundation.azul.invite

import azul.paleblue.foundation.azul.network.ApiClient
import org.jetbrains.anko.doAsyncResult
import java.util.concurrent.Future

class InviteFriendsModel constructor(val apiClient: ApiClient) {

  fun invitesRemaining(): Int {
    return -1
  }
  
  fun generateInvite(): Future<String> {
    return doAsyncResult {
      //apiClient.generateInvite()
      "XYZZY"
    }
  }

  fun redeemInvite(code: String) {

  }
}
