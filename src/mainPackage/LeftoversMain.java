package mainPackage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.GUIMain;

public class LeftoversMain {
	private static void createAndShowGUI() {
		GUIMain panel = new GUIMain();

		JFrame frame = new JFrame("Leftovers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
