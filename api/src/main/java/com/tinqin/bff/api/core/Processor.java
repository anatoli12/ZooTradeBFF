package com.tinqin.bff.api.core;

public interface Processor<I extends ProcessorInput,O extends ProcessorOutput> {
    O process(I input);
}
