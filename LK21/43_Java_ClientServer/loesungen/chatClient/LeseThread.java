import java.io.InputStreamReader;

public class LeseThread extends Thread {

	ChatClient main;
	InputStreamReader in;

	public LeseThread(ChatClient main, InputStreamReader in) {
		this.main = main;
		this.in = in;
	}

	@Override
	public void run() {
		int zeichen;
		try {
			while ((zeichen = in.read()) != -1) {
				System.out.println("Received: " + (char) zeichen);
				main.taChat.append("" + (char) zeichen);
			}

		} catch (Exception e) {
			System.out.println("Client Lesethread: Verbindung wurde getrennt ...");
		}
		System.out.println("Client LeseThread beendet");	
		main.btnVerbinden.setText("verbinden");
		main.btnVerbinden.setEnabled(true);
		main.tfNachricht.setEditable(false);
		main.btnSenden.setEnabled(false);
		main.verbindungsstatus = ChatClient.GETRENNT;
	}
}
