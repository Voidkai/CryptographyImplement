package DES.Util;

import DES.Table.PTable;
import DES.Table.ShiftTable;

/**
 * @author: keith
 * @Des: roundkey generation algorithm
 * @Date: 17/12/2019 16:00
 **/
public class RoundKey {
    private static int round = 16;
    private static int key_length = 56;
    private static PTable ptable = new PTable();
    private static ShiftTable sTable = new ShiftTable();
    public StringBuffer[] getRoundKey(String key){
        StringBuffer[] roundkey = new StringBuffer[round];
        StringBuffer keyBinary = new ToBinaryString().toBinaryString(key);

        StringBuffer  substitutekey = new StringBuffer(); //store the key after permulation via pc_1
        for(int i =0;i<key_length; i++){
            substitutekey.append(keyBinary.charAt(ptable.getPcTable_1()[i] -1));
        }

        //divide the key into two parts L_0 and R_0
        StringBuffer L0 = new StringBuffer();
        StringBuffer R0 = new StringBuffer();
        L0.append(substitutekey.substring(0,28));
        R0.append(substitutekey.substring(28));

        // generate 16-rounds key
        for(int i=0;i<round;i++){
            for(int j=0;j< sTable.getShiftTable()[i]; j++){
                char mTemp;
                mTemp = L0.charAt(0);
                L0.deleteCharAt(0);
                L0.append(mTemp);
                mTemp = R0.charAt(0);
                R0.deleteCharAt(0);
                R0.append(mTemp);
            }

            // compose L0 and R0
            StringBuffer newkey = new StringBuffer(L0.toString()+R0.toString());

            StringBuffer keytemp = new StringBuffer();
            for(int j=0;j<48;j++){
                keytemp.append(newkey.charAt(ptable.getPcTable_2()[j] -1 ));
            }

            roundkey[i] = keytemp;
        }
        return roundkey;
    }
}
