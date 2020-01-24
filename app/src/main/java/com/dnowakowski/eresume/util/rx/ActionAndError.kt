package com.dnowakowski.eresume.util.rx

data class ActionAndError<A>(val action: A?, val error: Throwable)