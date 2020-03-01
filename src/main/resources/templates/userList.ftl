<#import "parts/common.ftl" as c>

<@c.page>
List of users
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td><a href="/user/${user.customerLink}">${user.username}</a></td>
            <td>${user.email}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/delete/${user.customerLink}">Удалить</a> </td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>
