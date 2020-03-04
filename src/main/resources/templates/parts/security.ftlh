<#assign
    know = Session.SPRING_SECURITY_CONTEXT??
>
<#if know>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getName()
    isAdmin = user.isAdmin()
    userLink = user.getCustomerLink()
    >
<#else>
    <#assign
    name = "Регистрация"
    isAdmin = false
    userLink = -1
    >
</#if>