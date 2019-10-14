package com.github.rz7d.fastconcat.transfer;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public final class MappedCopy implements CopyStrategy<FileChannel, FileChannel> {

    public MappedCopy() {
    }

    @Override
    public void transferAll(FileChannel from, FileChannel to) throws IOException {
        long position = 0;
        long size = from.size();

        long chunk = size > Integer.MAX_VALUE ? size - Integer.MAX_VALUE : size;

        while (size > 0) {
            var in = from.map(MapMode.READ_ONLY, position, chunk);

            in.load();
            while (in.hasRemaining()) {
                to.write(in);
            }

            size -= chunk;
            position += chunk;
        }

        // disposing mapped buffers
        System.gc();
        System.runFinalization();
    }

}
