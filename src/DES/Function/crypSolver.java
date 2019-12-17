package DES.Function;

import DES.Table.ExtendTable;
import DES.Table.IpTable;
import DES.Table.PTable;
import DES.Table.SBox;
import DES.Util.RoundKey;
import DES.Util.ToBinaryString;

/**
 * @author: keith
 * @Des:
 * @Date: 17/12/2019 16:29
 **/
public class crypSolver {

    private static SBox sBox = new SBox();
    private static IpTable ipTable = new IpTable();
    private static PTable pTable = new PTable();
    private static RoundKey roundKey = new RoundKey();
    private static ExtendTable extendTable = new ExtendTable();
    public String crypt(String plaintext, String key, String type, boolean lack){
        if(type.equals("e") && lack){
            int l = 8 - plaintext.length();
            StringBuffer tt = new StringBuffer(plaintext);
            for(int i=0;i<l;i++){
                tt.append(String.valueOf(l));
            }
            plaintext = tt.toString();
        }
        StringBuffer ciphertext = new StringBuffer();//最终密文结果
        StringBuffer originTextBinary = new ToBinaryString().toBinaryString(plaintext);//二进制形式的明文
        StringBuffer substituteOrigintext = new StringBuffer();//置换后的明文
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; j++)
                substituteOrigintext.append(originTextBinary.charAt(ipTable.getIpTable()[i][j] - 1));
        }//ip置换

        StringBuffer L = new StringBuffer(substituteOrigintext.substring(0, 32));
        StringBuffer R = new StringBuffer(substituteOrigintext.substring(32));
        //明文M分为L(前32)和R(后32)两块

        StringBuffer[] subKey = roundKey.getRoundKey(key);//获得子密钥

        if (type.equals("d")) {
            StringBuffer[] mTemp = roundKey.getRoundKey(key);
            for (int i = 0; i < 16; ++i) {
                subKey[i] = mTemp[15 - i];
            }
        }//解密需要倒序使用子密钥

        // 16轮迭代T
        for (int i = 0; i < 16; ++i) {
            StringBuffer mLTemp = new StringBuffer(L);
            L.replace(0, 32, R.toString());

            // 按E位选择表扩展右边
            StringBuffer mRTemp = new StringBuffer(); // 存储扩展后的右边
            for (int j = 0; j < 48; ++j) {
                mRTemp.append(R.charAt(extendTable.getExtendTable()[j] - 1));
            }

            // 扩展后的右边和子密钥异或
            for (int j = 0; j < 48; ++j) {
                if (mRTemp.charAt(j) == subKey[i].charAt(j)) {
                    mRTemp.replace(j, j + 1, "0");
                } else {
                    mRTemp.replace(j, j + 1, "1");
                }
            }

            // 进行S盒压缩
            R.setLength(0);
            for (int j = 0; j < 8; ++j) {
                String mSNumber = mRTemp.substring(j * 6, (j + 1) * 6);
                int row = Integer.parseInt(Character.toString(mSNumber.charAt(0)) + mSNumber.charAt(5), 2);
                int column = Integer.parseInt(mSNumber.substring(1, 5), 2);
                int number = sBox.getsBox()[j][row * 16 + column];
                StringBuffer numberString = new StringBuffer(Integer.toBinaryString(number));
                while (numberString.length() < 4) {
                    numberString.insert(0, 0);
                }
                R.append(numberString);
            }

            // 将压缩后的R通过P_Table置换
            StringBuffer mRTemp1 = new StringBuffer(); // 存储置换后的R
            for (int j = 0; j < 32; ++j) {
                mRTemp1.append(R.charAt( pTable.getpTable()[j] - 1));
            }
            R.replace(0, 32, mRTemp1.toString());

            // 将置换后的R与最开始的L异或
            for (int j = 0; j < 32; ++j) {
                if (R.charAt(j) == mLTemp.charAt(j)) {
                    R.replace(j, j + 1, "0");
                } else {
                    R.replace(j, j + 1, "1");
                }
            }
        }

        // 合并迭代完的L和R
        StringBuffer LR = new StringBuffer(R.toString() + L.toString());

        //根据IPR_Table置换LR
        StringBuffer mLRTemp = new StringBuffer(); // 存储置换后的LR
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; j++)
                mLRTemp.append(LR.charAt(ipTable.getIprTable()[i][j] - 1));
        }

        // 2.8 把二进制转为字符串
        for (int i = 0; i < 8; ++i) {
            String mCharTemp = mLRTemp.substring(i * 8, (i + 1) * 8);
            ciphertext.append((char) Integer.parseInt(mCharTemp, 2));
        }
        String result = ciphertext.toString();
        int x= result.charAt(result.length()-1)-'0';
        if (lack && type.equals("d")) {
            result = result.substring(0,result.length()-x);
        }
        return result;
    }
}
