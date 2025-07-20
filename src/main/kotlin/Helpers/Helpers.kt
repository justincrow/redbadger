package com.mindfulbytes.Helpers

fun <T, R> Result<T>.flatMap(f: (T) -> Result<R>): Result<R> = fold({ f(it) }, { Result.failure(it) })
