package io.netifi.proteus.kotlin.sample.protobuf;

@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.8.9-SNAPSHOT)",
    comments = "Source: io/netifi/proteus/kotlin/app/simpleservice.proto")
@io.netifi.proteus.kotlin.annotations.internal.ProteusGenerated(
    type = io.netifi.proteus.kotlin.annotations.internal.ProteusResourceType.SERVICE,
    referencedClass = InteractionsService.class)
@javax.inject.Named(
    value ="InteractionsServiceServer")
public final class InteractionsServiceServer extends io.netifi.proteus.kotlin.AbstractProteusService {
  private final InteractionsService service;
  private final io.opentracing.Tracer tracer;
  private final io.reactivex.functions.Function<io.opentracing.SpanContext, io.reactivex.FlowableTransformer<io.rsocket.kotlin.Payload, io.rsocket.kotlin.Payload>> requestResponseTrace;
  private final io.reactivex.functions.Function<io.opentracing.SpanContext, io.reactivex.FlowableTransformer<io.rsocket.kotlin.Payload, io.rsocket.kotlin.Payload>> serverStreamTrace;
  private final io.reactivex.functions.Function<io.opentracing.SpanContext, io.reactivex.FlowableTransformer<io.rsocket.kotlin.Payload, io.rsocket.kotlin.Payload>> clientStreamTrace;
  private final io.reactivex.functions.Function<io.opentracing.SpanContext, io.reactivex.FlowableTransformer<io.rsocket.kotlin.Payload, io.rsocket.kotlin.Payload>> channelTrace;
  @javax.inject.Inject
  public InteractionsServiceServer(InteractionsService service, io.netifi.proteus.kotlin.util.Optional<io.opentracing.Tracer> tracer) {
    this.service = service;
    if (!tracer.isPresent()) {
      this.tracer = null;
      this.requestResponseTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.traceAsChild();
      this.serverStreamTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.traceAsChild();
      this.clientStreamTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.traceAsChild();
      this.channelTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.traceAsChild();
    } else {
      this.tracer = tracer.get();
      this.requestResponseTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.traceAsChild(this.tracer, InteractionsService.METHOD_REQUEST_RESPONSE, io.netifi.proteus.kotlin.tracing.Tags.of("proteus.service", InteractionsService.SERVICE), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.type", "server"), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.version", "0.8.9-SNAPSHOT"));
      this.serverStreamTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.traceAsChild(this.tracer, InteractionsService.METHOD_SERVER_STREAM, io.netifi.proteus.kotlin.tracing.Tags.of("proteus.service", InteractionsService.SERVICE), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.type", "server"), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.version", "0.8.9-SNAPSHOT"));
      this.clientStreamTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.traceAsChild(this.tracer, InteractionsService.METHOD_CLIENT_STREAM, io.netifi.proteus.kotlin.tracing.Tags.of("proteus.service", InteractionsService.SERVICE), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.type", "server"), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.version", "0.8.9-SNAPSHOT"));
      this.channelTrace = io.netifi.proteus.kotlin.tracing.ProteusTracing.traceAsChild(this.tracer, InteractionsService.METHOD_CHANNEL, io.netifi.proteus.kotlin.tracing.Tags.of("proteus.service", InteractionsService.SERVICE), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.type", "server"), io.netifi.proteus.kotlin.tracing.Tags.of("proteus.version", "0.8.9-SNAPSHOT"));
    }

  }

  @java.lang.Override
  public String getService() {
    return InteractionsService.SERVICE;
  }

  @java.lang.Override
  public Class<?> getServiceClass() {
    return service.getClass();
  }

  @java.lang.Override
  public io.reactivex.Completable fireAndForget(final io.rsocket.kotlin.Payload payload) {
    return io.reactivex.Completable.error(new UnsupportedOperationException("Fire and forget not implemented."));
  }

  @java.lang.Override
  public io.reactivex.Single<io.rsocket.kotlin.Payload> requestResponse(final io.rsocket.kotlin.Payload payload) {
    try {
      io.netty.buffer.ByteBuf metadata = io.netty.buffer.Unpooled.wrappedBuffer(payload.getMetadata());
      io.opentracing.SpanContext spanContext = io.netifi.proteus.kotlin.tracing.ProteusTracing.deserializeTracingMetadata(tracer, metadata);
      String method = io.netifi.proteus.kotlin.frames.ProteusMetadata.getMethod(metadata);
        if(InteractionsService.METHOD_REQUEST_RESPONSE.equals(method)) {
          com.google.protobuf.CodedInputStream is = com.google.protobuf.CodedInputStream.newInstance(payload.getData());
          return service.requestResponse(io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest.parseFrom(is), metadata).map(serializer).toFlowable().compose(requestResponseTrace.apply(spanContext)).firstOrError();
        }
        else {
          return io.reactivex.Single.error(new UnsupportedOperationException());
        }
      } catch (Throwable t) {
        return io.reactivex.Single.error(t);
      }
    }

    @java.lang.Override
    public io.reactivex.Flowable<io.rsocket.kotlin.Payload> requestStream(final io.rsocket.kotlin.Payload payload) {
      try {
        io.netty.buffer.ByteBuf metadata = io.netty.buffer.Unpooled.wrappedBuffer(payload.getMetadata());
        io.opentracing.SpanContext spanContext = io.netifi.proteus.kotlin.tracing.ProteusTracing.deserializeTracingMetadata(tracer, metadata);
        String method = io.netifi.proteus.kotlin.frames.ProteusMetadata.getMethod(metadata);
          if(InteractionsService.METHOD_SERVER_STREAM.equals(method)) {
            com.google.protobuf.CodedInputStream is = com.google.protobuf.CodedInputStream.newInstance(payload.getData());
            return service.serverStream(io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest.parseFrom(is), metadata).map(serializer).compose(serverStreamTrace.apply(spanContext));
          }
          else {
            return io.reactivex.Flowable.error(new UnsupportedOperationException());
          }
        } catch (Throwable t) {
          return io.reactivex.Flowable.error(t);
        }
      }

      @java.lang.Override
      public io.reactivex.Flowable<io.rsocket.kotlin.Payload> requestChannel(final io.rsocket.kotlin.Payload payload, final io.reactivex.Flowable<io.rsocket.kotlin.Payload> publisher) {
        try {
          io.netty.buffer.ByteBuf metadata = io.netty.buffer.Unpooled.wrappedBuffer(payload.getMetadata());
          io.opentracing.SpanContext spanContext = io.netifi.proteus.kotlin.tracing.ProteusTracing.deserializeTracingMetadata(tracer, metadata);
          String method = io.netifi.proteus.kotlin.frames.ProteusMetadata.getMethod(metadata);
            if(InteractionsService.METHOD_CLIENT_STREAM.equals(method)) {
              io.reactivex.Flowable<io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest> messages =
                publisher.startWith(payload).map(deserializer(io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest.parser()));
              return service.clientStream(messages, metadata).map(serializer).toFlowable().compose(clientStreamTrace.apply(spanContext));
            }
            if(InteractionsService.METHOD_CHANNEL.equals(method)) {
              io.reactivex.Flowable<io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest> messages =
                publisher.startWith(payload).map(deserializer(io.netifi.proteus.kotlin.sample.protobuf.SimpleRequest.parser()));
              return service.channel(messages, metadata).map(serializer).compose(channelTrace.apply(spanContext));
            }
            else {
              return io.reactivex.Flowable.error(new UnsupportedOperationException());
            }
          } catch (Throwable t) {
            return io.reactivex.Flowable.error(t);
          }
        }

        @java.lang.Override
        public io.reactivex.Flowable<io.rsocket.kotlin.Payload> requestChannel(final org.reactivestreams.Publisher<io.rsocket.kotlin.Payload> payloads) {
          return io.netifi.proteus.kotlin.util.StreamSplitter.split(payloads).flatMap(new io.reactivex.functions.Function<io.netifi.proteus.kotlin.util.StreamSplitter.Split, org.reactivestreams.Publisher<? extends io.rsocket.kotlin.Payload>>() {
            @java.lang.Override
            public org.reactivestreams.Publisher<? extends io.rsocket.kotlin.Payload> apply(final io.netifi.proteus.kotlin.util.StreamSplitter.Split split) throws Exception {
              return requestChannel(split.getHead(), split.getTail());
            }
          });
        }

        private static final io.reactivex.functions.Function<com.google.protobuf.MessageLite, io.rsocket.kotlin.Payload> serializer =
          new io.reactivex.functions.Function<com.google.protobuf.MessageLite, io.rsocket.kotlin.Payload>() {
            @java.lang.Override
            public io.rsocket.kotlin.Payload apply(final com.google.protobuf.MessageLite message) {
              int length = message.getSerializedSize();
              io.netty.buffer.ByteBuf byteBuf = io.netty.buffer.ByteBufAllocator.DEFAULT.buffer(length);
              try {
                message.writeTo(com.google.protobuf.CodedOutputStream.newInstance(byteBuf.internalNioBuffer(0, length)));
                byteBuf.writerIndex(length);
                return new io.rsocket.kotlin.DefaultPayload(io.netifi.proteus.kotlin.util.ByteBufs.asByteBuffer(byteBuf), io.netifi.proteus.kotlin.util.ByteBufs.EMPTY);
              } catch (Throwable t) {
                throw new RuntimeException(t);
              }
            }
          };

        private static <T> io.reactivex.functions.Function<io.rsocket.kotlin.Payload, T> deserializer(final com.google.protobuf.Parser<T> parser) {
          return new io.reactivex.functions.Function<io.rsocket.kotlin.Payload, T>() {
            @java.lang.Override
            public T apply(final io.rsocket.kotlin.Payload payload) {
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
