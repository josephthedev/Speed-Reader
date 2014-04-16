package BackEnd;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.*;
import java.awt.image.*;

public class DrawTest extends Applet {
	public static void main(String[] args) {
		Frame DrawTextLayout = new Frame("Draw TextLayout Example");
		DrawTextLayout.setSize(350, 250);
		Applet DrawTextLayoutExample = new Applet();
		DrawTextLayout.add(DrawTextLayoutExample);
		DrawTextLayout.setVisible(true);
		DrawTextLayout.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void paint(Graphics g) {
		/*
		g.setColor(Color.blue);
		g.setFont(new Font("Arial", Font.BOLD, 14));
		g.drawString("Draw TextLayout Java Example", 50, 40);
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString("http://ecomputernotes.com", 200, 205);
		super.paint(g);
		*/
		Graphics2D G2D = (Graphics2D) g;
		FontRenderContext FontRC = G2D.getFontRenderContext();
		Font font = new Font("Arial", Font.ITALIC, 22);
		TextLayout TextL = new TextLayout("Welcome to Java World", font, FontRC);
		G2D.setColor(Color.BLUE);
		TextL.draw(G2D, 60, 120);
	}
}
