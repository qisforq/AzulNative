package azul.paleblue.foundation.azul.invite

import android.location.Location
import azul.paleblue.foundation.azul.network.ApiClient
import foundation.paleblue.azul.proto.RedeemInviteReply
import org.jetbrains.anko.doAsyncResult
import java.util.concurrent.Future

class InviteModel constructor(val apiClient: ApiClient) {

  fun invitesRemaining(): Int {
    return -1
  }
  
  fun generateInvite(): Future<String> {
    return doAsyncResult {
      apiClient.generateInvite()
      //"XYZZY"
    }
  }

  fun redeemInvite(code: String): Future<RedeemInviteReply.RedeemInviteResult> {
    // locationGetter.getCurrentLocation(OnSuccessListener {
    return doAsyncResult {
      // TODO: Pass a real Location
      apiClient.redeemInvite(code, Location("TEST"))
    }
  }
}
