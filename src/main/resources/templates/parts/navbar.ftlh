<#include "security.ftlh">
<#import "login.ftlh" as l>

<div style="background-color: #0b245d;">
<nav class="navbar navbar-expand-lg navbar-dark col-sm-10 mx-auto" style="background-color: #0b245d;">
    <a class="navbar-brand" href="/">SleeplessLife</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
<#--            todo-->
        </ul>
    </div>

    <#if user??>
        <div class="nav-item dropdown">
            <button class="btn btn-outline-light" href="#" id="navbarDropdown" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                ${name}
            </button>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <#if user??>
                    <a class="dropdown-item" href="#">Profile</a>
                </#if>
                <#if isAdmin>
                    <a class="dropdown-item" href="/user/list">User List</a>
                </#if>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Help</a>

                <@l.logout />
            </div>
        </div>
    <#else >
        <a href="/registration">
            <button class="btn btn-outline-light">${name}</button>
        </a>
    </#if>
</nav>
</div>