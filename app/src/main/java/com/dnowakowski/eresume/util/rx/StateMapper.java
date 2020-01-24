package com.dnowakowski.eresume.util.rx;

public interface StateMapper<S> {
    S map(S oldState);
}
