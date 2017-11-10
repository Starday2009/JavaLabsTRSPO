//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ClientHandler implements Runnable {
    private Socket mySocket;
    public List<Socket> clients = new ArrayList();

    public ClientHandler() {
    }

    public ClientHandler(Socket socket) {
        this.mySocket = socket;
    }

    public ClientHandler(Socket socket, List<Socket> clients) {
        this.mySocket = socket;
        this.clients = new ArrayList(clients);
    }

    public List<?> getClients() {
        return this.clients;
    }

    public void setClients(List<Socket> clients) {
        this.clients = new ArrayList(clients);
    }

    public String XmlToString(String xmlData) {
        String ansver = "Error";

        try {
            ansver = "";
            DocumentBuilderFactory e = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = e.newDocumentBuilder();
            StringReader sr = new StringReader(xmlData);
            InputSource is = new InputSource();
            is.setCharacterStream(sr);
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("student");
            System.out.println("----------------------------");

            for(int temp = 0; temp < nList.getLength(); ++temp) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if(nNode.getNodeType() == 1) {
                    Element eElement = (Element)nNode;
                    ansver = ansver + "Student roll no : " + eElement.getAttribute("rollno");
                    ansver = ansver + "First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent();
                    ansver = ansver + "Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent();
                    ansver = ansver + "Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent();
                    ansver = ansver + "Marks : " + eElement.getElementsByTagName("marks").item(0).getTextContent();
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        return ansver;
    }

    public void run() {
        while(true) {
            try {
                DataInputStream ex = new DataInputStream(this.mySocket.getInputStream());
                String s = ex.readUTF();

                for(int out = 0; out < this.clients.size(); ++out) {
                    Socket temp = (Socket)this.clients.get(out);
                    if(temp != this.mySocket) {
                        System.out.println("Others");
                        DataOutputStream out1 = new DataOutputStream(temp.getOutputStream());
                        out1.writeUTF(this.XmlToString(s) + "\n");
                    }
                }

                DataOutputStream var7 = new DataOutputStream(this.mySocket.getOutputStream());
                var7.writeUTF(this.XmlToString(s) + "\n");
            } catch (IOException var6) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, (String)null, var6);
            }
        }
    }
}
