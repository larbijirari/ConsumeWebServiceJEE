/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author larix
 */
@WebServlet(urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get the action of jsp page
      String action = request.getParameter("action");
      //link to forward to
      String forwardTo="index.jsp";
      //the webservice that we will use
      String webService="http://127.0.0.1:8888/api/users";
      
        //show user
      if("afficher".equals(action)){
          // call a methode that extract the users
      Collection<User> usersListe=GetWebServiceData(webService);
          
      request.setAttribute("listeDesUsers", usersListe);  
               forwardTo = "index.jsp?action=listerLesUtilisateurs";  
                
      }
      
       if("ajouter".equals(action)){
        String name = request.getParameter("name");
           
        String age = request.getParameter("age");
        //create a user 
        User u = new User(name, age);
           PostUserToWebService(u.getName(),u.getAge(), webService);  
              forwardTo = "index.jsp";
       }
        if("supprimer".equals(action)){
             
            String userId=request.getParameter("idUser");
        DeleteUser("http://127.0.0.1:8888/api/user/"+userId);
       
       
       }
      
       RequestDispatcher dp = request.getRequestDispatcher(forwardTo);  
        dp.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
public ArrayList<User> GetWebServiceData(String uri){
        //declare a list of users
      ArrayList<User> liste=new ArrayList<User>();
      try {
          
                //the url of the webservice
		URL url = new URL(uri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //implement the code CRUD 
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
 
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
 
		String output;
                // a string that will cintaine the json object
		String mainJSON="";
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			mainJSON=mainJSON+output;
		}
 
		conn.disconnect();
		System.out.println(mainJSON);
		JSONObject json=null;
                //converting the json array to javaobjects
		try {
		
		
			 JSONArray allItems= (JSONArray)new JSONParser().parse(mainJSON);
			 
			 System.out.println(""+allItems.size());
			
			 Iterator i = allItems.iterator();
			 
			 
			             while (i.hasNext()) {
			            	 
			                 JSONObject innerObj = (JSONObject) i.next();
			                 System.out.println("name "+ innerObj.get("name") +
			 
			                         " age " + innerObj.get("age"));
			 
                                         
                                         liste.add(new User(""+innerObj.get("id"),""+innerObj.get("name"),""+innerObj.get("age")));
                                         
			             }

		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		
		
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	  }
return liste;}
// create a user
public void PostUserToWebService(String name,String age,String uri){

 try {
                 // the url of the webservice
		URL url = new URL(uri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
                //implement the CRUD METHODE
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
                
		String input = "{\"name\":\""+name+"\",\"age\":\""+age+"\"}";
                //push the object to the webService
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();
 
		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
 
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}
 
		conn.disconnect();
 
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	 }
}
public void DeleteUser(String uri){

	try {
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("DELETE");
		int responseCode = connection.getResponseCode();
		 connection.disconnect();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
