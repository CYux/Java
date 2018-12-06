package databaseOfMovies;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/*
 * The main window.
 */
public class MovieFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
    private JScrollPane scrollpane;
    private JTextField nameField;
    private JTextField directorField;
    private JTextField yearField;
    private JTextField typeField;
    private JButton button,deButton;
    /*
     * This object stores the student data.
     * When the program is started it loads the data from the file,
     * when it is closed, it saves the data.
     * 
     * DO NOT MODIFY THIS ATTRIBUTE!
     */
    public MovieData data;

    /*
     * This function should create the components of the window.
     * E.g. table, labels, text fields, etc.
     */
	public class OkButtonActionListener implements ActionListener{
		JTextField t;
		JTextField c;
		JTextField d;
		JTextField g;
		public OkButtonActionListener(JTextField t, JTextField c,JTextField d,JTextField g)
		{
			this.t=t;
			this.c=c;
			this.d=d;
			this.g=g;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button)
			{
					data.addMovie(t.getText(), c.getText(),d.getText(),g.getText());	
			}
			if(e.getSource()==deButton)
			{
				int index = table.getSelectedRow();
				if (-1 == index) {
					JOptionPane.showMessageDialog(null, "Please selet one row.");
					return;
				}
					data.deleteMovie(index);
					
			}
		}
		
	}
    private void initComponents() {
        this.setLayout(new BorderLayout());
        table = new JTable(data);
        scrollpane = new JScrollPane(table);
        this.add(scrollpane, BorderLayout.CENTER);
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        button = new JButton("New Movie");
        deButton =new JButton("Delete");
        JLabel Name = new JLabel("Name:");
        JLabel Director = new JLabel("Director:");
        JLabel Year = new JLabel("Year");
        JLabel ttype = new JLabel("Type:");
        nameField = new  JTextField(20);
        directorField = new JTextField(20);
        yearField = new  JTextField(4);
        typeField = new  JTextField(10);
        table.setFillsViewportHeight(true);	//table occupies the entire window
        
     
        
        south.add(Name);
        south.add(nameField);
        south.add(Director);
        south.add(directorField);
        south.add(Year);
        south.add(yearField);
        south.add(ttype);
        south.add(typeField);
        
        south.add(button);
        south.add(deButton);
        this.add(south, BorderLayout.SOUTH);
       
		ActionListener listener = new OkButtonActionListener(nameField,directorField,yearField,typeField);
		button.addActionListener(listener);
		deButton.addActionListener(listener);
    }
    
    
    /*
     * Constructor of the window.
     * 
     * DO NOT MODIFY THIS CONSTRUCTOR!
     */
    @SuppressWarnings("unchecked")
    public MovieFrame() {
        super("Movies administration");
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

    /*
     * Entry point of the program.
     * 
     * DO NOT MODIFY THIS FUNCTION!
     */
    public static void main(String[] args) {
        // Show the window:
    	MovieFrame mf = new MovieFrame();
        mf.setVisible(true);
    }
    
}
