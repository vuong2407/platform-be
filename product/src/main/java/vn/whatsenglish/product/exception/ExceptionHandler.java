package vn.whatsenglish.product.exception;

import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ExceptionHandler {

    @GrpcExceptionHandler(RuntimeException.class)
    public StatusRuntimeException handleResourceNotFoundException(RuntimeException cause) {
        Status status = Status.newBuilder()
                .setCode(Code.NOT_FOUND.getNumber())
                .setMessage(cause.getMessage())
                .build();
        return StatusProto.toStatusRuntimeException(status);
    }
}
