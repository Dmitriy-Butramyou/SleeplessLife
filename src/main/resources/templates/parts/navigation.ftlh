<#include "security.ftlh">
<#macro navigation>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li class="active"><a href="/user/${userLink}">Моя страница</a></li>
                    <li><a href="#">Новости</a></li>
                    <li><a href="#">Контакты</a></li>
                    <li><a href="#">Документы</a></li>
                    <li><a href="#">Сообщения</a></li>
                    <li><a href="#">Группы</a></li>
                    <li><a href="#">Календарь</a></li>
                </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2">
                <#nested>
            </div>
        </div>
    </div>
</#macro>