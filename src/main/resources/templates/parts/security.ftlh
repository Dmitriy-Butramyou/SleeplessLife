<#assign
know = Session.SPRING_SECURITY_CONTEXT??
>
<#if know>
    <#assign
    currentUser = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = currentUser.getName()
    isAdmin = currentUser.isAdmin()
    userLink = currentUser.getCustomerLink()
    >
<#else>
    <#assign
    name = "Регистрация"
    isAdmin = false
    userLink = -1
    >
</#if>