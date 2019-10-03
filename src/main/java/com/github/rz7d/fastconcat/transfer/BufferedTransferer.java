package com.github.rz7d.fastconcat.transfer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import com.github.rz7d.fastconcat.util.SizeUnit;

public final class BufferedTransferer implements TransferMethod<ReadableByteChannel, WritableByteChannel> {

    private final ByteBuffer buffer = ByteBuffer.allocateDirect((int) SizeUnit.MiB.toBytes(1536));

    public BufferedTransferer() {
    }

    @Override
    public void transferAll(ReadableByteChannel from, WritableByteChannel to) throws IOException {
        var buffer = this.buffer;
        var last = false;
        while (!last) {
            buffer.clear();
            while (buffer.hasRemaining()) {
                if (from.read(buffer) == -1) {
                    last = true;
                    break;
                }
            }

            buffer.flip();
            while (buffer.hasRemaining()) {
                to.write(buffer);
            }
        }
    }

}
