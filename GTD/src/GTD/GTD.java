/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GTD;

import GTD.PL.GtdGUI;

/**
 *
 * @author damell
 */
public class GTD {

	private static GtdGUI  gui;

	public static void main(String[] args) {
		gui = new GtdGUI();
		gui.setVisible(true);
	}
	
}
