package DES.Util;


/**
 * @author: keith
 * @Des:
 * @Date: 17/12/2019 16:03
 **/
public class ToBinaryString {
    public StringBuffer toBinaryString(String key){
        StringBuffer keyBinary = new StringBuffer();
        for(int i=0;i<key.length();i++){
            StringBuffer mSubKeyTemp = new StringBuffer(Integer.toBinaryString(key.charAt(i)));
            while(mSubKeyTemp.length()<8){
                mSubKeyTemp.insert(0,0);
            }
            keyBinary.append(mSubKeyTemp);
        }

        return keyBinary;
    }
}
