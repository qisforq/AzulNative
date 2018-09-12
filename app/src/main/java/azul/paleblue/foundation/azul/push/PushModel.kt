package azul.paleblue.foundation.azul.push

import azul.paleblue.foundation.azul.network.ApiClient

class PushModel(val apiClient: ApiClient) {

  fun registerForPush(token: String) {
    apiClient.registerForPush(token)
  }

}