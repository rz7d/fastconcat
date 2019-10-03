package com.github.rz7d.fastconcat.transfer;

import java.io.IOException;
import java.nio.channels.FileChannel;

public final class UnbufferedTransfer implements TransferMethod<FileChannel, FileChannel> {

    public UnbufferedTransfer() {
    }

    @Override
    public void transferAll(FileChannel from, FileChannel to) throws IOException {
        var transferred = 0L;

        while (transferred < from.size()) {
            transferred += from.transferTo(transferred,
                from.size() - transferred,
                to);
        }
    }

}
