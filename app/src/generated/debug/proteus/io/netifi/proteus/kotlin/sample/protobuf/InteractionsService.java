package io.netifi.proteus.kotlin.sample.protobuf;

/**
 * <pre>
 * A simple service for test.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.8.9-SNAPSHOT)",
    comments = "Source: io/netifi/proteus/kotlin/app/simpleservice.proto")
@io.netifi.proteus.kotlin.annotations.internal.ProteusGenerated(
    type = io.netifi.proteus.kotlin.annotations.internal.ProteusResourceType.CONTRACT,
    referencedClass = InteractionsServiceServer.class)
public interface InteractionsService {
  String SERVICE = "io.netifi.proteus.kotlin.sample.protobuf.InteractionsService";
  String METHOD_REQUEST_RESPONSE = "RequestResponse";
  String METHOD_SERVER_STREAM = "ServerStream";
  String METHOD_CLIENT_STREAM = "ClientStream";
  String METHOD_CHANNEL = "Channel";

  /**
   * <pre>
   * Simple unary RPC.
   * </pre>
   */
  io.reactivex.Single<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse> requestResponse(io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest message, final io.netty.buffer.ByteBuf metadata);

  /**
   * <pre>
   * Simple server-to-client streaming RPC.
   * </pre>
   */
  io.reactivex.Flowable<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse> serverStream(io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest message, final io.netty.buffer.ByteBuf metadata);

  /**
   * <pre>
   * Simple unary RPC.
   * </pre>
   */
  io.reactivex.Single<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse> clientStream(org.reactivestreams.Publisher<io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest> messages, final io.netty.buffer.ByteBuf metadata);

  /**
   * <pre>
   * Simple bidirectional streaming RPC.
   * </pre>
   */
  io.reactivex.Flowable<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse> channel(org.reactivestreams.Publisher<io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest> messages, final io.netty.buffer.ByteBuf metadata);
}
