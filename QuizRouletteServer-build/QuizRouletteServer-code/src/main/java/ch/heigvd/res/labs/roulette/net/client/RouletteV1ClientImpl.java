package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.JsonObjectMapper;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.InfoCommandResponse;
import ch.heigvd.res.labs.roulette.net.protocol.RandomCommandResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
/**
 * This class implements the client side of the protocol specification (version 1).
 * 
 * @author Olivier Liechti
 */
public class RouletteV1ClientImpl implements IRouletteV1Client {

  private static final Logger LOG = Logger.getLogger(RouletteV1ClientImpl.class.getName());

  //socket du client
    Socket clientSocket;

  @Override
  public void connect(String server, int port) throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    clientSocket = new Socket(server, port);
  }

  @Override
  public void disconnect() throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    clientSocket.close();
  }

  @Override
  public boolean isConnected() {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return !clientSocket.isConnected();
  }

  @Override
  public void loadStudent(String fullname) throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      outToServer.writeBytes(fullname);
  }

  @Override
  public void loadStudents(List<Student> students) throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

  }

  @Override
  public Student pickRandomStudent() throws EmptyStoreException, IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return null;
  }

  @Override
  public int getNumberOfStudents() throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return 0;
  }

  @Override
  public String getProtocolVersion() throws IOException {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return null;
  }



}
