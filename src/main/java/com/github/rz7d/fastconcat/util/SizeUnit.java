package com.github.rz7d.fastconcat.util;

public enum SizeUnit {
    B(1),
    KiB(1024),
    MiB(1024 * 1024),
    GiB(1024 * 1024 * 1024),
    TiB(1024 * 1024 * 1024 * 1024L);

    private final long scale;

    private SizeUnit(long scale) {
        this.scale = scale;
    }

    public long toBytes(long value) {
        return value * scale;
    }

}
