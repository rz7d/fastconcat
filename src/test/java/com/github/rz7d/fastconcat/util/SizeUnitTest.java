package com.github.rz7d.fastconcat.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.github.rz7d.fastconcat.util.SizeUnit;

final class SizeUnitTest {

    @Test
    void testScalar() {
        assertEquals(1073741824, SizeUnit.B.toBytes(1073741824));
        assertEquals(1073741824, SizeUnit.KiB.toBytes(1048576));
        assertEquals(1073741824, SizeUnit.MiB.toBytes(1024));
        assertEquals(1073741824, SizeUnit.GiB.toBytes(1));

        assertEquals(2147483648L, SizeUnit.B.toBytes(2147483648L));
        assertEquals(2147483648L, SizeUnit.KiB.toBytes(2097152));
        assertEquals(2147483648L, SizeUnit.MiB.toBytes(2048));
        assertEquals(2147483648L, SizeUnit.GiB.toBytes(2));

        assertEquals(1099511627776L, SizeUnit.B.toBytes(1099511627776L));
        assertEquals(1099511627776L, SizeUnit.KiB.toBytes(1073741824));
        assertEquals(1099511627776L, SizeUnit.MiB.toBytes(1048576));
        assertEquals(1099511627776L, SizeUnit.GiB.toBytes(1024));
        assertEquals(1099511627776L, SizeUnit.TiB.toBytes(1));
    }

}
