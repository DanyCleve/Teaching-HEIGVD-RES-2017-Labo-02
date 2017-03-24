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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    boolean connected = false;
    //valeur d'entrée serveur vers le client
    BufferedReader input;
    //valeur de sortie client vers serveur
    PrintWriter output;
    //liste des etudians
    List<Student> studentList = new ArrayList<>();

  @Override
  public void connect(String server, int port) throws IOException {
   //To change body of generated methods, choose Tools | Templates.
    clientSocket = new Socket(server, port);
    input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    output = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    connected = true;
  }

  @Override
  public void disconnect() throws IOException {
   //To change body of generated methods, choose Tools | Templates.
    //avant de se deconnecter nous avons fermer nos interfaces entrées sorties ensuite fermer le tube
    if(connected){
      input.close();
      output.close();
      clientSocket.close();
      connected = false;
    }
  }

  @Override
  public boolean isConnected() {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return connected;
  }

  @Override
  public void loadStudent(String fullname) throws IOException {
  //To change body of generated methods, choose Tools | Templates.DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
   studentList.add(new Student(fullname));
   loadStudents(studentList);
  }

  @Override
  public void loadStudents(List<Student> students) throws IOException {
   //To change body of generated methods, choose Tools | Templates.

   //test si le tableau est vide
    if(students ==null || students.isEmpty()){
      return;
    }

    for(Student student : students){

      if(student != null && !student.getFullname().isEmpty()){
        output.println(student.getFullname());
        output.flush();
      }
    }

  }

  @Override
  public Student pickRandomStudent() throws EmptyStoreException, IOException {
    //To change body of generated methods, choose Tools | Templates.
    output.println(RouletteV1Protocol.CMD_RANDOM);
    output.flush();
    RandomCommandResponse randomCommandResponse = JsonObjectMapper.parseJson(input.readLine(), RandomCommandResponse.class);
    return new Student(randomCommandResponse.getFullname());
  }

  @Override
  public int getNumberOfStudents() throws IOException {
   //To change body of generated methods, choose Tools | Templates.
    return studentList.size();
  }

  @Override
  public String getProtocolVersion() throws IOException {
    //To change body of generated methods, choose Tools | Templates.
    return RouletteV1Protocol.VERSION;
  }



}
