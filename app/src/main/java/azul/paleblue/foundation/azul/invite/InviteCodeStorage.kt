package azul.paleblue.foundation.azul.invite

import android.content.Context

val prefsPath = "foundation.paleblue.azul"

class InviteCodeStorage(val context: Context) {

  val inviteKey = "INVITE_CODE"

  val preferences = context.getSharedPreferences(prefsPath, Context.MODE_PRIVATE)

  fun storeCode(code: String) {
    preferences.edit().putString(inviteKey, code).apply()
  }

  fun getCode(): String? {
    return preferences.getString(inviteKey, null)
  }

}
