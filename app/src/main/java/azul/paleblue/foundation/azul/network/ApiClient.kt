package azul.paleblue.foundation.azul.network

import android.location.Location
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import foundation.paleblue.azul.proto.*
import org.jetbrains.anko.*

class ApiClient constructor(host: String, port: Int) : AnkoLogger {
  private val channel: ManagedChannel
  private val stub: AzulGrpc.AzulBlockingStub

  init {
    channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build()
    stub = AzulGrpc.newBlockingStub(channel)
  }
  
  fun login(username: String, password: String): LoginReply {
    val request = LoginRequest.newBuilder()
        .build()
    val reply = stub.login(request)
    return reply
  }
  
  fun register(username: String, password: String): RegisterReply {
    val request = RegisterRequest.newBuilder()
        .build()
    val reply = stub.register(request)
    return reply
  }

  fun generateInvite(): String {
    val request = GenerateInviteRequest.newBuilder()
        .build()
    val reply = stub.generateInvite(request)
    return reply.inviteCode
  }

  fun redeemInvite(inviteCode: String, location: Location?): RedeemInviteReply.RedeemInviteResult {
    val request = RedeemInviteRequest.newBuilder()
        .setInviteCode(inviteCode)

    if (location != null) {
      request.setLocation(foundation.paleblue.azul.proto.Location.newBuilder()
          .setLatitude(location.latitude)
          .setLongitude(location.longitude)
          .build())
    }

    val reply = stub.redeemInvite(request.build())
    return reply.result
  }

  fun registerForPush(pushToken: String): RegisterForPushReply.RegisterForPushResult {
    val request = RegisterForPushRequest.newBuilder()
        .setFirebaseMessagingToken(pushToken)
        .build()
    val reply = stub.registerForPush(request)
    return reply.result
  }

  fun sendMessage(message: String): String {
    warn("sendMessage was removed from the protocol")
    return "NOP"
  }
}

