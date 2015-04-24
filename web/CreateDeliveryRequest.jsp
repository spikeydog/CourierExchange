<%-- 
    Document   : createDelivery
    Created on : Apr 23, 2015, 1:27:05 AM
    Author     : Prasun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <link href="./CSS/Styling.css" rel="stylesheet">
        <title>Online freeLance courier Exchange</title>
         <%@ include file="header.jsp" %> 
         <script>
             function foo(date, time) {
                 var datetime = date.value + " " + time.value;
                 var date = new Date(datetime);
                 var millis = date.getTime();
                 document.getElementById("dropOffDateTime").value=millis;
             }
             function bar(date, time) {
                 var datetime = date.value + " " + time.value;
                 var date = new Date(datetime);
                 var millis = date.getTime();
                 document.getElementById("pickUpDateTime").value=millis;
             }
         </script>
    </head>
    <body>
        <form action="CreateDeliveryRequest" method="post" 
              onchange="foo(this.dropOffDate, this.dropOffTime);
              bar(this.pickUpDate, this.pickUpTime)">
    <table id="ContentTab">    
        <h1><b><u>Create Delivery Request</u></b></h1><br>
        
        <tr>
            <td>    
    <label>Description:</label>
            </td>
            <td>
                <input type="text" name="description"><br><br>
            </td>
    </tr>
    <tr>
            <td>    
    <label>Package weight in lbs</label>
            </td>
            <td>
                <input type="text" name="weight"><br><br>
            </td>
    </tr>
    
    <tr>
            <td>    
    <label>Pick-Up Address:</label>
            </td>
            <td>
                <input type="text" name="pickUpAddress"><br><br>
            </td>
    </tr>
   
    
    <input id="pickUpDateTime" type="hidden" name="pickUpDateTime"/>
    <input id="dropOffDateTime" type="hidden" name="dropOffDateTime"/>
    
    
    
    <tr>
            <td>    
    <label>Drop Off Address:</label>
            </td>
            <td>
                <input type="text" name="dropOffAddress"><br><br>
            </td>
    </tr>
    
    <tr>
            <td>    
    <label>Pick-Up Date:</label>
            </td>
            <td>
                <input type="date" name="pickUpDate" 
                       onchange="document.getElementById('dropOffDate').value=this.value;"><br><br>
            </td>
    </tr>
   
    
    <tr>
            <td>    
    <label>Ideal Pick-Up Time:</label>
            </td>
            <td>
                <input type="time" name="pickUpTime"
                       onchange="document.getElementById('dropOffTime').value=this.value;"><br><br>
            </td>
    </tr>
   
    
    <tr>
            <td>    
    <label>Drop Off Date:</label>
            </td>
            <td>
                <input type="date" name="dropOffDate" id="dropOffDate"><br><br>
            </td>
    </tr>
   
    
    <tr>
            <td>    
    <label>Ideal Drop Off Time:</label>
            </td>
            <td>
                <input type="time" name="dropOffTime" id="dropOffTime"><br><br>
            </td>
    </tr>
        <tr>
        <td>
   <button class="button" type="submit" name="action">Create</button>
        </td>
        <td>
   <button class="button" type="cancel" name="action" >Cancel</button>
        </td>
    </tr>
       
   </table> 
        </form>
    </body>
    <%@ include file="/footer.jsp" %>
</html>