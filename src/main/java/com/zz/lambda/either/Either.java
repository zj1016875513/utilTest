package com.zz.lambda.either;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;
import java.util.function.Function;

/**
 * 使用流时如果发生异常不希望停止处理流，Either类型是函数式语言中的常见类型而不是Java的一部分。
 * 与Java中的Optional类型类似，Either是具有两种可能性的通用包装器。
 * 例如，如果我们有一个Either值，那么这个值可以包含String类型或Integer类型Either<String,Integer>。
 * @param <L>
 * @param <R>
 */

public class Either<L, R> {
    private final L left;
    private final R right;
    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }
    public static <L,R> Either<L,R> Left( L value) {
        return new Either(value, null);
    }
    public static <L,R> Either<L,R> Right( R value) {
        return new Either(null, value);
    }
    public Optional<L> getLeft() {
        return Optional.ofNullable(left);
    }
    public Optional<R> getRight() {
        return Optional.ofNullable(right);
    }
    public boolean isLeft() {
        return left != null;
    }
    public boolean isRight() {
        return right != null;
    }
    public <T> Optional<T> mapLeft(Function<? super L, T> mapper) {
        if (isLeft()) {
            return Optional.of(mapper.apply(left));
        }
        return Optional.empty();
    }
    public <T> Optional<T> mapRight(Function<? super R, T> mapper) {
        if (isRight()) {
            return Optional.of(mapper.apply(right));
        }
        return Optional.empty();
    }
    public String toString() {
        if (isLeft()) {
            return "Left(" + left +")";
        }
        return "Right(" + right +")";
    }

    //只记录异常
    public static <T,R> Function<T, Either> lift(either_test1.CheckedFunction<T,R> function) {
        return t -> {
            try {
                return Either.Right(function.apply(t));
            } catch (Exception ex) {
                return Either.Left(ex);
            }
        };
    }

    //记录异常和值
    public static <T,R> Function<T, Either> liftWithValue(either_test1.CheckedFunction<T,R> function) {
        return t -> {
            try {
                return Either.Right(function.apply(t));
            } catch (Exception ex) {
                return Either.Left(Pair.of(ex,t));
            }
        };
    }
}
