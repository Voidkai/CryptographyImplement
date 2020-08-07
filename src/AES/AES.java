package AES;


public class AES {
    public static void main(String[] args) throws Exception {
        String content = "Username";
        String password = "password";
        AESUtil aesUtil = new AESUtil();
        System.out.println("需要加密的内容：" + content);
        byte[] encrypt = aesUtil.encrypt(content, password);
        System.out.println("加密后的密文：" + encrypt);
        byte[] decrypt = aesUtil.decrypt(encrypt, password);
        System.out.println("解密后的内容：" + new String(decrypt,"utf-8"));
    }
}
