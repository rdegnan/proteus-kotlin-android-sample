package io.netifi.proteus.kotlin.sample.protobuf;

@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.8.9-SNAPSHOT)",
    comments = "Source: io/netifi/proteus/kotlin/app/simpleservice.proto")
@io.netifi.proteus.kotlin.annotations.internal.ProteusGenerated(
    type = io.netifi.proteus.kotlin.annotations.internal.ProteusResourceType.CLIENT,
    referencedClass = InteractionsService.class)
public final class InteractionsServiceClient implements InteractionsService {
  private final io.rsocket.kotlin.RSocket rSocket;
  private final io.reactivex.functions.Function<java.util.Map<String, String>,io.reactivex.FlowableTransformer<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse, io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse>> requestResponseTrace;
  private final io.reactivex.functions.Function<java.util.Map<String, String>,io.reactivex.FlowableTransformer<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse, io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse>> serverStreamTrace;
  private final io.reactivex.functions.Function<java.util.Map<String, String>,io.reactivex.FlowableTransformer<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse, io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse>> clientStreamTrace;
  private final io.reactivex.functions.Function<java.util.Map<String, String>,io.reactivex.FlowableTransformer<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse, io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse>> channelTrace;

  public InteractionsServiceClient(io.rsocket.kotlin.RSocket rSocket) {
    this.rSocket = rSocket;
    this.requestResponseTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.trace();
    this.serverStreamTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.trace();
    this.clientStreamTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.trace();
    this.channelTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.trace();
  }


  public InteractionsServiceClient(io.rsocket.kotlin.RSocket rSocket, io.opentracing.Tracer tracer) {
    this.rSocket = rSocket;
    this.requestResponseTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.trace(tracer, InteractionsService.METHOD_REQUEST_RESPONSE, io.netifi.proteus.kotlin.tracing.Tags.of("proteus.service", InteractionsService.SERVICE), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.type", "client"), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.version", "0.8.9-SNAPSHOT"));
    this.serverStreamTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.trace(tracer, InteractionsService.METHOD_SERVER_STREAM, io.netifi.proteus.kotlin.tracing.Tags.of("proteus.service", InteractionsService.SERVICE), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.type", "client"), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.version", "0.8.9-SNAPSHOT"));
    this.clientStreamTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.trace(tracer, InteractionsService.METHOD_CLIENT_STREAM, io.netifi.proteus.kotlin.tracing.Tags.of("proteus.service", InteractionsService.SERVICE), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.type", "client"), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.version", "0.8.9-SNAPSHOT"));
    this.channelTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.trace(tracer, InteractionsService.METHOD_CHANNEL, io.netifi.proteus.kotlin.tracing.Tags.of("proteus.service", InteractionsService.SERVICE), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.type", "client"), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.version", "0.8.9-SNAPSHOT"));
  }

  @io.netifi.proteus.kotlin.annotations.internal.ProteusGeneratedMethod(returnTypeClass = io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse.class)
  public io.reactivex.Single<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse> requestResponse(io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest message) {
    return requestResponse(message, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  @io.netifi.proteus.kotlin.annotations.internal.ProteusGeneratedMethod(returnTypeClass = io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse.class)
  public io.reactivex.Single<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse> requestResponse(final io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest message, final io.netty.buffer.ByteBuf metadata) {
  final java.util.Map<String, String> map = new java.util.HashMap<String, String>();
    return io.reactivex.Single.defer(new java.util.concurrent.Callable<io.reactivex.Single<io.rsocket.kotlin.Payload>>() {
      @java.lang.Override
      public io.reactivex.Single<io.rsocket.kotlin.Payload> call() {
        final io.netty.buffer.ByteBuf tracingMetadata = io.netifi.proteus.kotlin.tracing.ProteusTracing.mapToByteBuf(io.netty.buffer.ByteBufAllocator.DEFAULT, map);
        final io.netty.buffer.ByteBuf metadataBuf = io.netifi.proteus.kotlin.frames.ProteusMetadata.encode(io.netty.buffer.ByteBufAllocator.DEFAULT, InteractionsService.SERVICE, InteractionsService.METHOD_REQUEST_RESPONSE, tracingMetadata, metadata);
        io.netty.buffer.ByteBuf data = serialize(message);
        tracingMetadata.release();
        return rSocket.requestResponse(new io.rsocket.kotlin.DefaultPayload(data, metadataBuf));
      }
    }).map(deserializer(io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse.parser())).toFlowable().compose(io.netifi.proteus.kotlin.util.Transformers.apply(requestResponseTrace, map)).firstOrError();
  }

  @io.netifi.proteus.kotlin.annotations.internal.ProteusGeneratedMethod(returnTypeClass = io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse.class)
  public io.reactivex.Flowable<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse> serverStream(io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest message) {
    return serverStream(message, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  @io.netifi.proteus.kotlin.annotations.internal.ProteusGeneratedMethod(returnTypeClass = io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse.class)
  public io.reactivex.Flowable<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse> serverStream(final io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest message, final io.netty.buffer.ByteBuf metadata) {
  final java.util.Map<String, String> map = new java.util.HashMap<String, String>();
    return io.reactivex.Flowable.defer(new java.util.concurrent.Callable<io.reactivex.Flowable<io.rsocket.kotlin.Payload>>() {
      @java.lang.Override
      public io.reactivex.Flowable<io.rsocket.kotlin.Payload> call() {
        final io.netty.buffer.ByteBuf tracingMetadata = io.netifi.proteus.kotlin.tracing.ProteusTracing.mapToByteBuf(io.netty.buffer.ByteBufAllocator.DEFAULT, map);
        final io.netty.buffer.ByteBuf metadataBuf = io.netifi.proteus.kotlin.frames.ProteusMetadata.encode(io.netty.buffer.ByteBufAllocator.DEFAULT, InteractionsService.SERVICE, InteractionsService.METHOD_SERVER_STREAM, tracingMetadata, metadata);
        io.netty.buffer.ByteBuf data = serialize(message);
        tracingMetadata.release();
        return rSocket.requestStream(new io.rsocket.kotlin.DefaultPayload(data, metadataBuf));
      }
    }).map(deserializer(io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse.parser())).compose(io.netifi.proteus.kotlin.util.Transformers.apply(serverStreamTrace, map));
  }

  @io.netifi.proteus.kotlin.annotations.internal.ProteusGeneratedMethod(returnTypeClass = io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse.class)
  public io.reactivex.Single<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse> clientStream(org.reactivestreams.Publisher<io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest> messages) {
    return clientStream(messages, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  @io.netifi.proteus.kotlin.annotations.internal.ProteusGeneratedMethod(returnTypeClass = io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse.class)
  public io.reactivex.Single<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse> clientStream(final org.reactivestreams.Publisher<io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest> messages, final io.netty.buffer.ByteBuf metadata) {
  final java.util.Map<String, String> map = new java.util.HashMap<String, String>();
    return rSocket.requestChannel(io.reactivex.Flowable.fromPublisher(messages).map(
      new io.reactivex.functions.Function<com.google.protobuf.MessageLite, io.rsocket.kotlin.Payload>() {
        private final java.util.concurrent.atomic.AtomicBoolean once = new java.util.concurrent.atomic.AtomicBoolean(false);

        @java.lang.Override
        public io.rsocket.kotlin.Payload apply(final com.google.protobuf.MessageLite message) {
          io.netty.buffer.ByteBuf data = serialize(message);
          if (once.compareAndSet(false, true)) {
            final io.netty.buffer.ByteBuf metadataBuf = io.netifi.proteus.kotlin.frames.ProteusMetadata.encode(io.netty.buffer.ByteBufAllocator.DEFAULT, InteractionsService.SERVICE, InteractionsService.METHOD_CLIENT_STREAM, metadata);
            return new io.rsocket.kotlin.DefaultPayload(data, metadataBuf);
          } else {
            return new io.rsocket.kotlin.DefaultPayload(data, io.netty.buffer.Unpooled.EMPTY_BUFFER);
          }
        }
      })).map(deserializer(io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse.parser())).compose(io.netifi.proteus.kotlin.util.Transformers.apply(clientStreamTrace, map)).firstOrError();
  }

  @io.netifi.proteus.kotlin.annotations.internal.ProteusGeneratedMethod(returnTypeClass = io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse.class)
  public io.reactivex.Flowable<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse> channel(org.reactivestreams.Publisher<io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest> messages) {
    return channel(messages, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  @io.netifi.proteus.kotlin.annotations.internal.ProteusGeneratedMethod(returnTypeClass = io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse.class)
  public io.reactivex.Flowable<io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse> channel(final org.reactivestreams.Publisher<io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest> messages, final io.netty.buffer.ByteBuf metadata) {
  final java.util.Map<String, String> map = new java.util.HashMap<String, String>();
    return rSocket.requestChannel(io.reactivex.Flowable.fromPublisher(messages).map(
      new io.reactivex.functions.Function<com.google.protobuf.MessageLite, io.rsocket.kotlin.Payload>() {
        private final java.util.concurrent.atomic.AtomicBoolean once = new java.util.concurrent.atomic.AtomicBoolean(false);

        @java.lang.Override
        public io.rsocket.kotlin.Payload apply(final com.google.protobuf.MessageLite message) {
          io.netty.buffer.ByteBuf data = serialize(message);
          if (once.compareAndSet(false, true)) {
            final io.netty.buffer.ByteBuf metadataBuf = io.netifi.proteus.kotlin.frames.ProteusMetadata.encode(io.netty.buffer.ByteBufAllocator.DEFAULT, InteractionsService.SERVICE, InteractionsService.METHOD_CHANNEL, metadata);
            return new io.rsocket.kotlin.DefaultPayload(data, metadataBuf);
          } else {
            return new io.rsocket.kotlin.DefaultPayload(data, io.netty.buffer.Unpooled.EMPTY_BUFFER);
          }
        }
      })).map(deserializer(io.netifi.proteus.kotlin.sample.protobuf.SimpleResponse.parser())).compose(io.netifi.proteus.kotlin.util.Transformers.apply(channelTrace,map));
  }

  private static io.netty.buffer.ByteBuf serialize(final com.google.protobuf.MessageLite message) {
    int length = message.getSerializedSize();
    io.netty.buffer.ByteBuf byteBuf = io.netty.buffer.ByteBufAllocator.DEFAULT.buffer(length);
    try {
      message.writeTo(com.google.protobuf.CodedOutputStream.newInstance(byteBuf.internalNioBuffer(0, length)));
      byteBuf.writerIndex(length);
      return byteBuf;
    } catch (Throwable t) {
      byteBuf.release();
      throw new RuntimeException(t);
    }
  }

  private static <T> io.reactivex.functions.Function<io.rsocket.kotlin.Payload, T> deserializer(final com.google.protobuf.Parser<T> parser) {
    return new io.reactivex.functions.Function<io.rsocket.kotlin.Payload, T>() {
      @java.lang.Override
      public T apply(io.rsocket.kotlin.Payload payload) {
        try {
          com.google.protobuf.CodedInputStream is = com.google.protobuf.CodedInputStream.newInstance(payload.getData());
          return parser.parseFrom(is);
        } catch (Throwable t) {
          throw new RuntimeException(t);
        }
      }
    };
  }
}
