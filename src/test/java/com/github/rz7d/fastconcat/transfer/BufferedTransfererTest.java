package com.github.rz7d.fastconcat.transfer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.github.rz7d.fastconcat.transfer.BufferedTransferer;

final class BufferedTransfererTest {

    private static final byte[] EXPECTED = "testMessageIsHere"
        .getBytes(StandardCharsets.UTF_8);

    @Test
    void testIntegrity() throws Exception {
        var out = new ByteArrayOutputStream(EXPECTED.length);

        new BufferedTransferer().transferAll(
            Channels.newChannel(new ByteArrayInputStream(EXPECTED)),
            Channels.newChannel(out));

        assertArrayEquals(EXPECTED, out.toByteArray());
    }

}
