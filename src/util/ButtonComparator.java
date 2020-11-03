package util;

import java.util.Comparator;

import javax.swing.JButton;

public class ButtonComparator implements Comparator<JButton> {

	@Override
	public int compare(JButton o1, JButton o2) {
		return o1.getText().compareTo(o2.getText());
	}

}
