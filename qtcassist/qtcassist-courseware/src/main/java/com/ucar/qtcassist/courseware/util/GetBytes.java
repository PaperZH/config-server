package com.ucar.qtcassist.courseware.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * inputStream转换为byte[]
 *
 * @author shijie.xu
 * @since 2018年08月20日
 */
public class GetBytes {
    public static byte[] getByteArrayInputStreamResource(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = 1024;
        byte[] temp = new byte[length];
        int i;
        while((i = inputStream.read(temp, 0, length)) > 0) {
            byteArrayOutputStream.write(temp, 0, i);
        }
        byte[] data = byteArrayOutputStream.toByteArray();
        return data;
    }
}
