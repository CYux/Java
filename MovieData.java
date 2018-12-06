package databaseOfMovies;

import java.util.List;

import javax.swing.table.AbstractTableModel;



public class MovieData extends AbstractTableModel {


	private static final long serialVersionUID = 1L;
	public List<Object[]> movies;
    public String[] columns = {"Name", "Director", "Year", "Type"};
    int r,c;
    @Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return movies.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return movies.get(rowIndex)[columnIndex];
	}
	
	public String getColumnName(int index)
	{
		return columns[index];
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		boolean t=false;
		if(columnIndex>=0) {
			t = true;
		}

		return t;
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		movies.get(rowIndex)[columnIndex]=aValue;
	}
	
	public void addMovie(String name, String director,String year, String type)
	{
		Object[] temp = {name, director, year, type};
		movies.add(temp);
		fireTableDataChanged();
	}
	public void deleteMovie(int index)
	{
		movies.remove(index);
		fireTableDataChanged();
	}
	
	
}