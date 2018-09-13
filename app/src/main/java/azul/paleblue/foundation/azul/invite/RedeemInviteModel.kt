package azul.paleblue.foundation.azul.invite

import android.location.Location
import azul.paleblue.foundation.azul.location.CurrentLocationGetter
import azul.paleblue.foundation.azul.network.ApiClient
import azul.paleblue.foundation.azul.persistence.InviteCodeStorage
import com.google.android.gms.tasks.OnSuccessListener
import org.jetbrains.anko.*

class RedeemInviteModel(val apiClient: ApiClient, val locationGetter: CurrentLocationGetter, val inviteCodeStorage: InviteCodeStorage): AnkoLogger
{
  val inviteCode: String = "EXAMPLE"
  var location: Location? = null

  init {
    locationGetter.getCurrentLocation(OnSuccessListener {
      // toast("location retrieved")
      it?.let {
        println(it.latitude)
        println(it.longitude)
        location = it
      }
    })
  }

  fun redeemInvite(username: String, password: String) {
    doAsync {
      val response = apiClient.redeemInvite(inviteCode, location)
      info("response: $response")
    }
  }

}
