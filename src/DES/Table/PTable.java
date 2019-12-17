package DES.Table;

/**
 * @author: keith
 * @Des:
 * @Date: 17/12/2019 15:50
 **/
public class PTable {
    private int[] pTable = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9,
            19, 13, 30, 6, 22, 11, 4, 25 };
    // PC-1置换表
    private int[] pcTable_1= {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4 };
    // PC-2置换表
    private int[] pcTable_2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };

    public int[] getpTable() {
        return pTable;
    }

    public void setpTable(int[] pTable) {
        this.pTable = pTable;
    }

    public int[] getPcTable_1() {
        return pcTable_1;
    }

    public void setPcTable_1(int[] pcTable_1) {
        this.pcTable_1 = pcTable_1;
    }

    public int[] getPcTable_2() {
        return pcTable_2;
    }

    public void setPcTable_2(int[] pcTable_2) {
        this.pcTable_2 = pcTable_2;
    }
}
