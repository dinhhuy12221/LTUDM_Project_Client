package Client.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.security.PublicKey;

import Client.ui.ClientUI;
import Object.Code;

public class client {
	private Socket socket;
	private int port;
	private String hostName = "localhost";
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;
	private encryption encryption;

	public client(String hostName, int port) {
		this.hostName = hostName;
		this.port = port;
		this.connect();
	}
	
	public client() {
		this.hostName = "localhost";
		this.port = 0;
	}

	public void connect() {
		try {
			this.socket = new Socket(this.hostName, this.port);
			this.out = new ObjectOutputStream(this.socket.getOutputStream());
			this.in = new ObjectInputStream(this.socket.getInputStream());
			generateKey();
		} catch (ConnectException | java.net.UnknownHostException e) {
			System.out.println("[Notification] Diconnect to server");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	private void generateKey() {
		try {
			encryption = new encryption();
			encryption.setPublicKey((PublicKey) this.in.readObject());

			encryption.encryptKey();

			this.out.writeObject(encryption.getEncryptedKey());
			this.out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (socket != null) {
				this.in.close();
				this.out.close();
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send(Object object) {
		try {
			byte[] bytes = encryption.encryptData((Code) object);
			this.out.writeObject(bytes);
			this.out.flush();
		} catch (java.net.SocketException e) {
			System.out.println("Lost connection");
		} catch (java.lang.NullPointerException e){
			System.out.println("Error in encryption");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object receive() {
		try {
			byte[] bytes = (byte[]) this.in.readObject();
			Object object = (Object) encryption.decryptData(bytes);
			return object;
		} catch (java.net.SocketException e) {
			System.out.println("Lost connection");
		} catch (java.lang.NullPointerException e){
			System.out.println("Error in encryption");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isConnected() {
		try {
			if (this.socket.isConnected() && !this.socket.isClosed())
				return true;
		} catch (Exception e) {
		}
		return false;
	}
}
