package com.dusk.restaurant.client;

import com.dusk.restaurant.client.dto.ErrorDto;

public sealed interface Result<T>
        permits Result.Ok, Result.Err {

    record Ok<T>(T value) implements Result<T> {
    }

    record Err<T>(ErrorDto error) implements Result<T> {
    }

    static <T> Result<T> ok(T value) {
        return new Ok<>(value);
    }

    static <T> Result<T> err(ErrorDto error) {
        return new Err<>(error);
    }

    default boolean isErr() {
        return this instanceof Err<T>;
    }

    default T unwrap() {
        if (this instanceof Ok<T> ok) {
            return ok.value();
        }
        throw new IllegalStateException("Called unwrap on an Err value");
    }

    default ErrorDto getErrorDto() {
        if (this instanceof Err<T> err) {
            return err.error();
        }
        throw new IllegalStateException("Called unwrapErr on an Ok value");
    }
}
