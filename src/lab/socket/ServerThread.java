package lab.socket;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);


            String text;

            do {
                text = reader.readLine();
                writer.println("Server: " + messageTransformation(text.toLowerCase()));

            } while (!text.equals("bye"));

            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    private static String messageTransformation(String message) {
        String temp1 = tripleVowel(message);
        return doubleConsonants(temp1);
    }

    static String tripleVowel(String str) {
        return str.replaceAll("([aAeEiIoOuU])", "$1$1$1");
    }

    static String doubleConsonants(String str) {
        return str.replaceAll("([qQwWrRtTpPsSdDgGhHjJkKzZxXcCvVbBnNmM])", "$1$1");
    }
}
