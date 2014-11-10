ConsumeWebServiceJEE
====================
A project that was released for universal purposes, 
the main idea is about a client developed in NATIVE JAVA
how it works ?
we have a web Service created in NODEJS 
in the project, we have a servlet that has 4 main functions
=====src/java/MainServlet.java====
public ArrayList<User> GetWebServiceData(String uri)===> fetch the data from a uri that we give in param.
public void PostUserToWebService(String name,String age,String uri===> create a user and pass it to the webService who will store it
public void DeleteUser(String uri)===>delete a user by passing his uri example "127.0.0.1:888/api/user/1"
protected void processRequest(HttpServletRequest request, HttpServletResponse response)=> exists by default in the new versions of netbeans
in this function we de handle all the actions created by the JSP page (index.jsp)



