import javax.swing.SwingUtilities;


public class project {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
        		controller c = new controller();
        		c.init();
                GUI gui = new GUI();
                gui.setController(c);
                gui.setVisible(true);
            }
        });
	}

}
