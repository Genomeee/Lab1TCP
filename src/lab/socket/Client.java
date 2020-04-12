package lab.socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import static lab.socket.Server.PORT;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", PORT)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));

            String text = "";

            do {

                System.out.println("Enter text: ");

                text = inputUser.readLine();

                writer.println(text);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String time = reader.readLine();

                System.out.println(time);

            } while (!text.equals("bye"));

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
