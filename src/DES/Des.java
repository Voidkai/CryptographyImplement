package DES;

import DES.Function.crypSolver;

/**
 * @author: keith
 * @Des: Des Main method
 * @Date: 17/12/2019 15:56
 **/
public class Des {
    private static crypSolver solver = new crypSolver();
    public static void main(String[] args){
        String plainText = "My name is Voidkai";
        String key = "16340038";
        System.out.println("明文为：" + plainText +
                "\n密钥为：" + key);
        int l = plainText.length() % 8;
        int s = plainText.length() / 8;
        boolean lack = (l != 0);
        StringBuffer crypt = new StringBuffer();
        for (int i = 0; i < s; i++)
            crypt.append(solver.crypt(plainText.substring(i*8,(i+1)*8), key, "e", false));
        if (lack)
            crypt.append(solver.crypt(plainText.substring(s*8), key, "e", true));
        String cryptText = crypt.toString();
        System.out.println("加密为：" +cryptText);
        StringBuffer decrypt = new StringBuffer();
        for (int i = 0; i < s; i++)
            decrypt.append(solver.crypt(cryptText.substring(i*8,(i+1)*8), key, "d", false));
        if (lack)
            decrypt.append(solver.crypt(cryptText.substring(s*8), key, "d", true));
        System.out.println("解密为：" +decrypt.toString());
    }
}
