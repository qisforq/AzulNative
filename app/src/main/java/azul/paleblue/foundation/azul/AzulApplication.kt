package azul.paleblue.foundation.azul

import android.app.Application
import azul.paleblue.foundation.azul.persistence.KeyValueStore
import azul.paleblue.foundation.azul.invite.InviteModel
import azul.paleblue.foundation.azul.invite.RedeemInviteModel
import azul.paleblue.foundation.azul.account.AccountModel
import azul.paleblue.foundation.azul.location.CurrentLocationGetter
import azul.paleblue.foundation.azul.network.ApiClient
import azul.paleblue.foundation.azul.push.PushModel
import azul.paleblue.foundation.azul.wallet.WalletModel
import azul.paleblue.foundation.azul.wallet.history.TransactionHistoryModel
import azul.paleblue.foundation.azul.wallet.send.SendMoneyModel

val host = "192.168.1.95"
val port = 50051

class AzulApplication : Application() {

  lateinit var locationGetter: CurrentLocationGetter
  lateinit var keyValueStore: KeyValueStore

  lateinit var apiClient: ApiClient
  lateinit var pushModel: PushModel
  lateinit var inviteModel: InviteModel
  lateinit var walletModel: WalletModel
  lateinit var transactionHistoryModel: TransactionHistoryModel
  lateinit var sendMoneyModel: SendMoneyModel
  lateinit var accountModel: AccountModel

  val inviteFriendsModel = InviteModel(apiClient)

  lateinit var redeemInviteModel: RedeemInviteModel

  override fun onCreate() {
    super.onCreate()

    locationGetter = CurrentLocationGetter(this)
    keyValueStore = KeyValueStore(this)

    apiClient = ApiClient(keyValueStore, host, port)
    inviteModel = InviteModel(apiClient)
    pushModel = PushModel(apiClient)
    walletModel = WalletModel(apiClient)
    transactionHistoryModel = TransactionHistoryModel(apiClient)
    sendMoneyModel = SendMoneyModel(apiClient)
    redeemInviteModel = RedeemInviteModel(apiClient, locationGetter, keyValueStore)
    accountModel = AccountModel(apiClient, keyValueStore)
  }

}
