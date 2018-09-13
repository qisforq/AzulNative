package azul.paleblue.foundation.azul

import android.app.Application
import azul.paleblue.foundation.azul.persistence.InviteCodeStorage
import azul.paleblue.foundation.azul.invite.InviteModel
import azul.paleblue.foundation.azul.invite.RedeemInviteModel
import azul.paleblue.foundation.azul.location.CurrentLocationGetter
import azul.paleblue.foundation.azul.network.ApiClient
import azul.paleblue.foundation.azul.push.PushModel
import azul.paleblue.foundation.azul.wallet.WalletModel

val host = "192.168.1.95"
val port = 50051

class AzulApplication : Application() {

  val apiClient: ApiClient = ApiClient(host, port)

  lateinit var locationGetter: CurrentLocationGetter
  lateinit var inviteCodeStorage: InviteCodeStorage

  val inviteFriendsModel = InviteModel(apiClient)
  val pushModel: PushModel = PushModel(apiClient)
  val walletModel = WalletModel(apiClient)
  
  lateinit var redeemInviteModel: RedeemInviteModel

  override fun onCreate() {
    super.onCreate()

    locationGetter = CurrentLocationGetter(this)
    inviteCodeStorage = InviteCodeStorage(this)
  
    redeemInviteModel = RedeemInviteModel(apiClient, locationGetter, inviteCodeStorage)
  }

}
