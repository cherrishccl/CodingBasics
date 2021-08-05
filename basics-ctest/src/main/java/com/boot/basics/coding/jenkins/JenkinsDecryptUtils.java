package com.boot.basics.coding.jenkins;

import org.apache.commons.io.IOUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;
/**
 * @Author cherrishccl
 * @Date 2021/8/5 15:10
 * @Version 1.0
 * @Description jenkins解密
 */
public class JenkinsDecryptUtils {
    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    /** secrets文件夹路径 */
    private final static String rootDir = "D:\\jk_home\\secrets";
    private static final byte[] MAGIC = "::::MAGIC::::".getBytes();
    // 密文密码-带括号{}
    private static final String data = "XXX";

    public static void main(String[] args) {
        decrypt();
    }

    public static void decrypt() {

        byte[] payload;
        try {
            payload = Base64.getDecoder().decode(data.substring(1, data.length()-1));
        } catch (IllegalArgumentException e) {
            return;
        }
        switch (payload[0]) {
            case 1:
                // For PAYLOAD_V1 we use this byte shifting model, V2 probably will need DataOutput
                int ivLength = ((payload[1] & 0xff) << 24)
                        | ((payload[2] & 0xff) << 16)
                        | ((payload[3] & 0xff) << 8)
                        | (payload[4] & 0xff);
                int dataLength = ((payload[5] & 0xff) << 24)
                        | ((payload[6] & 0xff) << 16)
                        | ((payload[7] & 0xff) << 8)
                        | (payload[8] & 0xff);
                if (payload.length != 1 + 8 + ivLength + dataLength) {
                    // not valid v1
                    return;
                }
                byte[] iv = Arrays.copyOfRange(payload, 9, 9 + ivLength);
                byte[] code = Arrays.copyOfRange(payload, 9+ivLength, payload.length);
                String text;
                try {
                    text = new String(decrypt(iv).doFinal(code), UTF_8);
                    System.out.println("密码明文:" + text);
                } catch (GeneralSecurityException e) {
                    // it's v1 which cannot be historical, but not decrypting
                    return;
                }
//                return new Secret(text, iv);
            default:
                return;
        }

    }

    public static Cipher decrypt(byte[] iv) {
        try {
            Cipher cipher = getCipher(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getKey(), new IvParameterSpec(iv));
            return cipher;
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    private static synchronized SecretKey getKey() throws Exception {
        SecretKey secret  = null;
        try {
            byte[] payload = load();
            if (payload == null) {
                payload = randomBytes(256);
//                    store(payload);
            }
            // Due to the stupid US export restriction JDK only ships 128bit version.
            secret = new SecretKeySpec(payload, 0, 128 / 8, KEY_ALGORITHM);
        } catch (IOException e) {
            throw e;
        }
        return secret;
    }

    protected static byte[] load() throws Exception {
        try {
            File f = new File(rootDir,"hudson.util.Secret");
            if (!f.exists()) {   return null;}

            Cipher sym = getCipher("AES");
            sym.init(Cipher.DECRYPT_MODE, getMasterKey());
            try (InputStream fis= Files.newInputStream(f.toPath());
                 CipherInputStream cis = new CipherInputStream(fis, sym)) {
                byte[] bytes = IOUtils.toByteArray(cis);
                return verifyMagic(bytes);
            }
        } catch (Exception x) {
            if (x.getCause() instanceof BadPaddingException) {
                throw x; // broken somehow
            } else {
                throw x;
            }
        }
    }

    public static Cipher getCipher(String algorithm) throws GeneralSecurityException {
        return Cipher.getInstance(algorithm);
    }

    private static SecretKey getMasterKey() throws Exception {
        File file = new File(rootDir,"master.key");
        SecretKey masterKey = toAes128Key(read(file).trim());

        return masterKey;
    }

    public static String read(File file) throws Exception {
        StringWriter out = new StringWriter();
        PrintWriter w = new PrintWriter(out);
        BufferedReader in = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
        String line;
        while ((line = in.readLine()) != null){
            w.println(line);
        }
        return out.toString();
    }

    public static byte[] randomBytes(int size) {
        byte[] random = new byte[size];
        new SecureRandom().nextBytes(random);
        return random;
    }

    private static byte[] verifyMagic(byte[] payload) {
        int payloadLen = payload.length-MAGIC.length;
        if (payloadLen<0) {
            return null;    // obviously broken
        }

        for (int i=0; i<MAGIC.length; i++) {
            if (payload[payloadLen+i]!=MAGIC[i]) {
                return null;    // broken
            }
        }
        byte[] truncated = new byte[payloadLen];
        System.arraycopy(payload,0,truncated,0,truncated.length);
        return truncated;
    }

    public static String toHexString(byte[] bytes) {
        int start = 0;
        int len = bytes.length;

        StringBuilder buf = new StringBuilder();
        for( int i=0; i<len; i++ ) {
            int b = bytes[start+i]&0xFF;
            if(b<16)    buf.append('0');
            buf.append(Integer.toHexString(b));
        }
        return buf.toString();
    }

    public static SecretKey toAes128Key(String s) {
        try {
            // turn secretKey into 256 bit hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(s.getBytes(StandardCharsets.UTF_8));

            // Due to the stupid US export restriction JDK only ships 128bit version.
            return new SecretKeySpec(digest.digest(),0,128/8, "AES");
        } catch (NoSuchAlgorithmException e) {
            throw new Error(e);
        }
    }

}

