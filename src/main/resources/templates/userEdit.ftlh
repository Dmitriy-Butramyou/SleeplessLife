<#import "parts/common.ftlh" as c>

<@c.page>
    <form action="/edit/${user.customerLink}" method="post">
    <table>
        <thead>
        <tr>
            <th>ID</th><td>${user.id}</td>
        </tr>

        <tr>
            <th>UUID</th><td><input type="text" name="newLink" value="${user.customerLink}"></td>
        </tr>
        <tr>
            <th>Name</th><td><input type="text" name="name" value="${user.name}"></td>
        </tr>
        <tr>
            <th>Email</th><td><input type="text" name="email" value="${user.email}"></td>
        </tr>
        <tr>
            <th>Password</th><td><input type="text" name="password"></td>
        </tr>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </thead>
    </table>
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <button type="submit">Save</button>
    </form>
</@c.page>