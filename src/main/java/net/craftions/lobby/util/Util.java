/*
 * Copyright (c) 2021 Ben Siebert. All rights reserved.
 */
package net.craftions.lobby.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Util {

    /**
     * Original: https://stackoverflow.com/questions/10308221/how-to-copy-file-inside-jar-to-outside-the-jar/44077426#44077426
     */
    public static boolean copy(InputStream source , String destination) {
        boolean succeess = true;
        try {
            Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
            succeess = false;
        }
        return succeess;
    }
}
