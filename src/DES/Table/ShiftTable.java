package DES.Table;

/**
 * @author: keith
 * @Des:
 * @Date: 17/12/2019 15:53
 **/
public class ShiftTable {
    private int[] shiftTable = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };

    public int[] getShiftTable() {
        return shiftTable;
    }

    public void setShiftTable(int[] shiftTable) {
        this.shiftTable = shiftTable;
    }
}
