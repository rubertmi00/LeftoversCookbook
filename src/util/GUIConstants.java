package util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class GUIConstants {
//	public static final Color BODY_COLOR = new Color(255, 153, 153);
//	public static final Color HEADER_COLOR = new Color(151, 240, 225);
//	public static final Color HEADER_TEXT_COLOR = new Color(210, 29, 29);
	public static final Color BODY_COLOR = new Color(234, 231, 220);
	public static final Color HEADER_COLOR = new Color(233, 128, 116);
	public static final Color HEADER_TEXT_COLOR = new Color(42, 41, 39);
	public static final Border BODY_BORDER = BorderFactory.createLineBorder(BODY_COLOR, 5);
	public static final Border HEADER_BORDER = BorderFactory.createLineBorder(HEADER_COLOR, 5);
	public static final Font HEADER_FONT_1 = new Font("Helvetica", Font.BOLD, 20);
	public static final int PREF_W = 620;
	public static final int PREF_H_PANEL = 450;
	public static final int PREF_H_HEADER = 100;
}
