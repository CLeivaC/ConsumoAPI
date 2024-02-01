package consumoAPIS;

import java.awt.EventQueue;

public class ClasePrincipal {
	
	public static void main(String[] args) {
		
		
		//ConectorAPI.consumirAPI();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
					frame.initListeners();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
