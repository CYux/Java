package databaseOfMovies;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/*
 * The main window.
 */
public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
    private JScrollPane scrollpane;

    TableModel model;
    
    private MovieData data;


    
    private void initComponents() {
        this.setLayout(new BorderLayout());
        table = new JTable(data);
        scrollpane = new JScrollPane(table);
        this.add(scrollpane, BorderLayout.CENTER);
        JPanel west = new JPanel();
        west.setLayout(new FlowLayout());

        table.setFillsViewportHeight(true);	//table occupies the entire window


        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(data);
        table.setRowSorter(sorter);

        
    }
    
    
    /*
     * Constructor of the window.
     * 
     * DO NOT MODIFY THIS CONSTRUCTOR!
     */
    @SuppressWarnings("unchecked")
    public UserFrame() {
        super("Movies client");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Load data at startup:
        try {
            data = new MovieData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("movies.dat"));
            data.movies = (List<Object[]>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Save data at shutdown:
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("movies.dat"));
                    oos.writeObject(data.movies);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Build the window:
        setMinimumSize(new Dimension(400, 300));
        initComponents();
        pack();
    }

    public static void main (String[] args) {
        // Show the window:
    	UserFrame uf = new UserFrame();
        uf.setVisible(true);
    }
    
}
