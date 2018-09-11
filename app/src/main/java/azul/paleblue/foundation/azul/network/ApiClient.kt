package azul.paleblue.foundation.azul.network

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import foundation.paleblue.azul.proto.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import java.io.PrintWriter
import java.io.StringWriter

class ApiClient(host: String, port: Int): AnkoLogger {
    // TODO: Integrate gRPC

    private val channel: ManagedChannel
    private val stub: AzulCoreGrpc.AzulCoreBlockingStub

    init {
      channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build()
      stub = AzulCoreGrpc.newBlockingStub(channel)
    }

    fun generateInvite(): String {
      val request = GenerateInviteRequest.newBuilder()
      .build()
      val reply = stub.generateInvite(request)
      return reply.inviteCode
    }
    
    fun redeemInvite(inviteCode: String): RedeemInviteReply.RedeemInviteResult {
      val request = RedeemInviteRequest.newBuilder()
      .setInviteCode(inviteCode)
      .build()
      val reply = stub.redeemInvite(request)
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

