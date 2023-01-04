package org.mln.utils;

import org.mln.customexceptions.FileIOException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class JSONUtil {

    private JSONUtil(){}
    public static String readAsJsonString(String path)  {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new FileIOException(path);
        }
    }

}
