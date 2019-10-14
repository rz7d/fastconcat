package com.github.rz7d.fastconcat.transfer;

import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public interface CopyStrategy<S extends ReadableByteChannel, T extends WritableByteChannel> {

    void transferAll(S from, T to) throws IOException;

}
