<%-- 
    Document   : index
    Created on : Nov 9, 2014, 11:59:56 PM
    Author     : larix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Project</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="lib/angular/angular.js"></script>
<script src="lib/angular-resource/angular-resource.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>
    <body>
         <center>
<div class="jumbotron">
  <h1>Consume WEBSERVICE</h1> <h2>With JEE <img src="http://www.yoann-ciabaud.fr/wp-content/uploads/2011/03/java-logo.jpg" width="90">
</h2> 
  </center>
 
</div>



   <div class="container" ng-controller="AppCtrl" >
    
    <div class="hero-unit">
    <h2>Add new User:</h2>
   
    <div id="CreateUser">
        <form action="MainServlet" method="GET">
             <div class="form-group">
      <label>Name</label>
    <input type="text" placeholder="name"  name="name" class="form-control">
  </div>
  <div class="form-group">
     <label>Age</label>
    <input type="text" placeholder="age"  name="age" class="form-control">
  </div>
    <button class="btn btn-primary">Ajouter</button>
    <input type="hidden" name="action" value="ajouter"/>  
</form>
</div>
    <div id="showUsers"> 
        <form action="MainServlet" method="GET">
              <button class="btn btn-primary">Afficher</button>
    <input type="hidden" name="action" value="afficher"/> 
        </form>
        
    </div>
</div>

<div>
     <c:if test="${param['action'] == 'listerLesUtilisateurs'}">  
<table   class="table table-bordered">
  <thead>
                <tr> 
                    <th><b>Id</b></th>
                    <th><b>Name</b></th>  
                    <th><b>Age</b></th>  
                      
                </tr>  
                </thead>
                <!-- Ici on affiche les lignes, une par utilisateur -->  
                <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer --> 
                <tbody>
                <c:forEach var="u" items="${requestScope['listeDesUsers']}">  
                    <tr>  
                        <td>${u.id}</td>
                        <td>${u.name}</td>  
                        <td>${u.age} 
                            <form action="MainServlet" method="GET"> 
                                <button class="btn btn-danger">X</button>
    <input type="hidden" name="action" value="supprimer"/> 
     <input type="hidden" name="idUser" value="${u.id}"/> 
                            </form> 
                        </td>  
                        <!-- On compte le nombre de users --> 
                    </tr>  
                </c:forEach>  
                
  
                <!-- Affichage du solde total dans la dernière ligne du tableau -->  
              
                
               
                
                </tbody>
                
 
</table>
</c:if>
</div>

</div>

    </body>
</html>