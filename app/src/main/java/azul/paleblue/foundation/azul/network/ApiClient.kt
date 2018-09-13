package azul.paleblue.foundation.azul.network

import android.location.Location
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import foundation.paleblue.azul.proto.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import java.io.PrintWriter
import java.io.StringWriter

class ApiClient constructor(host: String, port: Int) : AnkoLogger {
  private val channel: ManagedChannel
  private val stub: AzulGrpc.AzulBlockingStub

  init {
    channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build()
    stub = AzulGrpc.newBlockingStub(channel)
  }
  
  fun login(username: String, password: String): String {
    val request = LoginRequest.newBuilder()
        .build()
    val reply = stub.login(request)
    return reply.sessionToken
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
      request.setLatitude(location.latitude)
          .setLongitude(location.longitude)
    }

    val reply = stub.redeemInvite(request.build())
    return reply.result
  }

  fun registerForPush(pushToken: String): RegisterForPushReply.RegisterForPushResult {
    val request = RegisterForPushRequest.newBuilder()
        .setToken(pushToken)
        .build()
    val reply = stub.registerForPush(request)
    return reply.result
  }

  fun sendMessage(message: String): String {
    try {
      val request = HelloRequest.newBuilder().setName(message).build()
      val reply = stub.sayHello(request)
      debug("Got reply: $reply")
      return reply.message
    } catch (e: Exception) {
      val sw = StringWriter()
      val pw = PrintWriter(sw)
      e.printStackTrace(pw)
      pw.flush()
      return String.format("Failed... : %n%s", sw)
    }
  }
}

