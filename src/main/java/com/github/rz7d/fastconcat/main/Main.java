package com.github.rz7d.fastconcat.main;

import static java.nio.file.StandardOpenOption.*;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.List;

import com.github.rz7d.fastconcat.transfer.BufferedTransferer;

public final class Main implements PrivilegedExceptionAction<Void> {

    private final List<String> inputs;
    private final String output;

    private Main(List<String> inputs, String output) {
        this.inputs = inputs;
        this.output = output;
    }

    public static void main(String[] args) throws Exception {
        var arguments = Arrays.asList(args);
        if (arguments.isEmpty()) {
            System.out.println("java -jar fastconcat.jar <in files...> <out>");
            return;
        }

        var inFiles = arguments.subList(0, arguments.size() - 1);
        var outFile = arguments.get(arguments.size() - 1);

        AccessController.doPrivileged(new Main(inFiles, outFile));
    }

    @Override
    public Void run() throws Exception {
        var transfer = new BufferedTransferer();
        try (var out = FileChannel.open(Paths.get(output), CREATE_NEW, WRITE);
            var outLock = out.tryLock()) {
            if (outLock == null) {
                throw new IOException("出力ファイル " + output + " をロックできませんでした");
            }
            for (var input : inputs) {
                try (var in = FileChannel.open(Paths.get(input), READ, WRITE);
                    var inLock = in.tryLock()) {
                    if (inLock == null) {
                        throw new IOException("入力ファイル " + input + " をロックできませんでした");
                    }
                    transfer.transferAll(in, out);
                }
                try {
                    Files.deleteIfExists(Paths.get(input));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return null;
    }

}
