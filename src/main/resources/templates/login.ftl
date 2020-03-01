<#import "parts/common.ftl" as c>

<@c.page>
    <div class="col-sm-10 mx-auto">
        <h4>Login page</h4>
        <form action="/login" method="post">
            <div><label> Email: <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div><input class="btn btn-outline-dark" type="submit" value="Sign In"/></div>
        </form>
        <a href="/registration">
            <button class="btn btn-outline-dark mt-1">Registration</button>
        </a>
    </div>


</@c.page>