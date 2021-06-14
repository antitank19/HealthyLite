<%-- 
    Document   : accountForm
    Created on : Jan 31, 2021, 10:03:50 AM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="a"  value="${account}"></c:set>
<c:set var="action" value="${action}"></c:set>
<c:set var="isReadonly" value='${action == "viewAccount"? " readonly " : " "}'></c:set>

    <html>
        <head>
            <script src="JS/checkAccountForm.js"></script>
        </head>
            <table class="accountForm">
                <tr> 
                    <td>Full name</td> 
                    <td><input type="text" name="accFullName" placeholder="Enter your full name" value="${a.accFullName}" ${isReadonly} /></td> 
            </tr>
            <tr> 
                <td>Username</td> 
                <td><input type="text" name="accUserName" id="${action}-accUserName" placeholder="Must be at least six characters" value="${a.accUserName}" ${action == "viewAccount"? " readonly " : (action=="updateAccount"? " readonly ":" ")} onblur="checkUserName('${action}-accUserName')" required /></td> 
            </tr>
            <c:choose>
                <c:when test='${action == "createAccount"}'>
                    <tr> 
                        <td>Password</td> 
                        <td><input type="password" name="accPassword" id="${action}-accPassword" placeholder="Must be at least six characters" required onblur="checkPassword('${action}-accPassword')"/></td> 
                    </tr>
                    <tr> 
                        <td>Confirm password</td> 
                        <td><input type="password" name="confirmPassword" id="${action}-confirmPassword" placeholder="Reenter your password" required onblur="checkConfirmPassword('${action}-accPassword', '${action}-confirmPassword');"></td> 
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:if test='${action == "updateAccount"}'>
                        <tr>
                            <td>Change password?</td>
                            <td>
                                <label onclick="changePassword()"><input type="radio" id="changePasswordYes" name="changePassword" value="Yes">Yes</label>
                                <label onclick="changePassword()"><input type="radio" id="changePasswordNo" name="changePassword" value="No" checked>No</label>
                            </td>
                        </tr>
                        <tr class="displayNone" id="passwordRow"> 
                            <td>Password</td> 
                            <td><input type="password" name="accPassword" id="${action}-accPassword" placeholder="Must be at least six characters" onblur="checkPassword('${action}-accPassword')" /></td> 
                        </tr>
                        <tr class="displayNone" id="confirmRow"> 
                            <td>Confirm password</td> 
                            <td><input type="password" name="confirmPassword" id="${action}-confirmPassword" placeholder="Reenter your new password" onblur="checkConfirmPassword('${action}-accPassword', '${action}-confirmPassword');" /></td> 
                        </tr>
                    </c:if>
                </c:otherwise>
            </c:choose>
            <tr> 
                <td>Phone</td> 
                <td><input type="text" name="accPhone" id="${action}-accPhone" placeholder="This is optional" value="${a.accPhone}" ${isReadonly}></td> 
            </tr>
            <tr> 
                <td>Address</td> 
                <td><textarea name="accAddress" id="${action}-accAddress" placeholder="This is optional. This will be the default delivery location" ${isReadonly}>${a.accAddress}</textarea></td> 
            </tr>
            <tr>
                <td>Gender</td>
                <td>
                    <c:choose>
                        <c:when test="${action == 'viewAccount'}" >
                            <input type="text" name="accGender" value="${a.accGender}" ${isReadonly}></td> 
                        </c:when>
                        <c:otherwise>
                    <label><input type="radio" name="accGender" id="${action}-accGender1" value="Male" ${a.accGender=="Male"? " checked='checked' " : ""} ${action == "viewAccount"? " disabled " : " "}>Male</label>
                    <label><input type="radio" name="accGender" id="${action}-accGender2" value="Female" ${a.accGender=="Female"? " checked='checked' " : ""} ${action == "viewAccount"? " disabled " : " "}>Female</label>
                    <label><input type="radio" name="accGender" id="${action}-accGender3" value="Other" ${a.accGender=="Other"? " checked='checked' " : ""} ${action == "viewAccount"? " disabled " : " "}>Other</label>
                    </c:otherwise>    
                </c:choose>
        </td>
    </tr>
    <tr>
        <td>Birthday</td>
        <td><input type="date" name="accDob" id="${action}-accDob" value="${a.accDob}" ${isReadonly}></td>
    </tr>
</table>
</body>
</html>
