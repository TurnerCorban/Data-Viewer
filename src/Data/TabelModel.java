package src.Data;

import javax.swing.table.AbstractTableModel;
import java.io.Serializable;

public class TabelModel extends AbstractTableModel{
    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
