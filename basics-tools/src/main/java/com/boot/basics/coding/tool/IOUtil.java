package com.boot.basics.coding.tool;


/**
 * @Author cherrishccl
 * @Date 2021/1/25 13:37
 * @Version 1.0
 * @Description IOUtil
 */
public class IOUtil {
    /**
     * close Closeable
     * @param closeables the closeables
     */
    public static void close(AutoCloseable... closeables) {
        if (CollectionUtils.isNotEmpty(closeables)) {
            for (AutoCloseable closeable : closeables) {
                close(closeable);
            }
        }
    }

    /**
     * close Closeable
     * @param closeable the closeable
     */
    public static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception ignore) {
            }
        }
    }

}
