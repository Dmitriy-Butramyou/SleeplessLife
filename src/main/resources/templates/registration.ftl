<#import "parts/common.ftl" as c>

<@c.page>

    <h1>Create user</h1>
    <h6><#if message??>${message}</#if></h6>
    <form action="/registration" method="post">
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Password</th>
                <th>Email</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input type="text" name="username"></td>
                <td><input type="text" name="password"></td>
                <td><input type="text" name="email"></td>
            </tr>

            </tbody>
        </table>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Save</button>
    </form>

</@c.page>