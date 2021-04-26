# ISSUE 16779
## Error handler returns empty body on native build

### usage information
* Build native docker image: `./create-image.sh`
* Run native docker image **(replace your username)**: `docker run --rm -p 8081:8081 --name="issue_16779" MY_USER_NAME/quarkus_16779:1.0-SNAPSHOT`

### Technical information
* [x] Behaviour is tested before native build
* [x] Calling endpoint with a valid user `GET http://localhost:8081/users/16779` returns a User object
* [x] Calling endpoint with an unknown user `GET http://localhost:8081/users/200888` returns an error object
* [x] Calling endpoint on a native build with a valid user `GET http://localhost:8081/users/16779` returns a User object
* [ ] FIXME: Calling endpoint on a native build with an unknown user `GET http://localhost:8081/users/200888` returns an empty body but should return an error object

### Exception \[only on native build\]
```java
2021-04-26 12:29:55.919 ERROR [i.q.v.h.r.QuarkusErrorHandler] (executor-thread-1)  HTTP Request to /users/200888 failed, error id: a550b9b4-72e3-4f6b-a6be-87980c9d02ce-1: org.jboss.resteasy.spi.UnhandledException: com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class issue.native_rest_error_handling.model.ApiError and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
        at org.jboss.resteasy.core.SynchronousDispatcher.writeException(SynchronousDispatcher.java:230)
        at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:519)
        at org.jboss.resteasy.core.SynchronousDispatcher.lambda$invoke$4(SynchronousDispatcher.java:261)
        at org.jboss.resteasy.core.SynchronousDispatcher.lambda$preprocess$0(SynchronousDispatcher.java:161)
        at org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:364)
        at org.jboss.resteasy.core.SynchronousDispatcher.preprocess(SynchronousDispatcher.java:164)
        at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:247)
        at io.quarkus.resteasy.runtime.standalone.RequestDispatcher.service(RequestDispatcher.java:73)
        at io.quarkus.resteasy.runtime.standalone.VertxRequestHandler.dispatch(VertxRequestHandler.java:138)
        at io.quarkus.resteasy.runtime.standalone.VertxRequestHandler.access$000(VertxRequestHandler.java:41)
        at io.quarkus.resteasy.runtime.standalone.VertxRequestHandler$1.run(VertxRequestHandler.java:93)
        at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2415)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1452)
        at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
        at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
        at java.lang.Thread.run(Thread.java:834)
        at org.jboss.threads.JBossThread.run(JBossThread.java:501)
        at com.oracle.svm.core.thread.JavaThreads.threadStartRoutine(JavaThreads.java:519)
        at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:192)
Caused by: com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class issue.native_rest_error_handling.model.ApiError and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
        at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1276)
        at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:400)
        at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.failForEmpty(UnknownSerializer.java:71)
        at com.fasterxml.jackson.databind.ser.impl.UnknownSerializer.serialize(UnknownSerializer.java:33)
        at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        at com.fasterxml.jackson.databind.ObjectWriter$Prefetch.serialize(ObjectWriter.java:1514)
        at com.fasterxml.jackson.databind.ObjectWriter.writeValue(ObjectWriter.java:1006)
        at org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider.writeTo(ResteasyJackson2Provider.java:329)
        at org.jboss.resteasy.core.messagebody.AsyncBufferedMessageBodyWriter.asyncWriteTo(AsyncBufferedMessageBodyWriter.java:24)
        at org.jboss.resteasy.core.interception.jaxrs.ServerWriterInterceptorContext.writeTo(ServerWriterInterceptorContext.java:87)
        at org.jboss.resteasy.core.interception.jaxrs.AbstractWriterInterceptorContext.asyncProceed(AbstractWriterInterceptorContext.java:203)
        at org.jboss.resteasy.core.interception.jaxrs.AbstractWriterInterceptorContext.getStarted(AbstractWriterInterceptorContext.java:166)
        at org.jboss.resteasy.core.interception.jaxrs.ServerWriterInterceptorContext.lambda$getStarted$0(ServerWriterInterceptorContext.java:73)
        at org.jboss.resteasy.core.interception.jaxrs.ServerWriterInterceptorContext.aroundWriteTo(ServerWriterInterceptorContext.java:93)
        at org.jboss.resteasy.core.interception.jaxrs.ServerWriterInterceptorContext.getStarted(ServerWriterInterceptorContext.java:73)
        at org.jboss.resteasy.core.ServerResponseWriter.lambda$writeNomapResponse$3(ServerResponseWriter.java:163)
        at org.jboss.resteasy.core.interception.jaxrs.ContainerResponseContextImpl.filter(ContainerResponseContextImpl.java:404)
        at org.jboss.resteasy.core.ServerResponseWriter.executeFilters(ServerResponseWriter.java:252)
        at org.jboss.resteasy.core.ServerResponseWriter.writeNomapResponse(ServerResponseWriter.java:101)
        at org.jboss.resteasy.core.ServerResponseWriter.writeNomapResponse(ServerResponseWriter.java:74)
        at org.jboss.resteasy.core.SynchronousDispatcher.writeException(SynchronousDispatcher.java:226)
        ... 18 more
```