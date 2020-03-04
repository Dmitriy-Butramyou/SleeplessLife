<#macro logout>
    <#include "security.ftlh">
    <form action="/logout" method="post">
        <button class="dropdown-item" type="submit">Sign Out</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</#macro>