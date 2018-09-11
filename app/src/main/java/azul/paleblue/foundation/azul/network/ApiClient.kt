package azul.paleblue.foundation.azul.network

import android.text.TextUtils
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
//import foundation.paleblue.azul.proto.*

import io.grpc.examples.helloworld.GreeterGrpc
import io.grpc.examples.helloworld.HelloRequest

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import java.io.PrintWriter
import java.io.StringWriter

class ApiClient : AnkoLogger {
    // TODO: Integrate gRPC

    private var channel: ManagedChannel? = null

    init {

    }

    //val defaultHost = "10.40.255.252"
    val defaultHost = "192.168.1.95"
    val defaultPort = "50051"

    fun sendMessage(host: String, portStr: String, message: String): String {
    //fun sendMessage(message: String): String {

        //val host = defaultHost
        //val portStr = defaultPort

        val port = if (TextUtils.isEmpty(portStr)) 0 else Integer.valueOf(portStr)
        debug("Got port: $port")
        try {
            channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build()
            val stub = GreeterGrpc.newBlockingStub(channel)
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

