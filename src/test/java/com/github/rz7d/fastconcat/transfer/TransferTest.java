package com.github.rz7d.fastconcat.transfer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.github.rz7d.fastconcat.transfer.BufferedTransfer;

final class TransferTest {

    private static final byte[] EXPECTED = "testMessageIsHere テストメッセージです"
        .getBytes(StandardCharsets.UTF_8);

    @Test
    void testIntegrity() throws Exception {
        var out = new ByteArrayOutputStream(EXPECTED.length);

        new BufferedTransfer().transferAll(
            Channels.newChannel(new ByteArrayInputStream(EXPECTED)),
            Channels.newChannel(out));

        assertArrayEquals(EXPECTED, out.toByteArray());
    }

}
