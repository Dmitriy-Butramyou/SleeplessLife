<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="row mt-5">
        <div class="col-8">
            <h1>Описание проекта</h1>
        </div>
        <div class="col-4">
            <h5>Введите данные аккаунта</h5>
            <form action="/login" method="post">
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label"> Email: </label>
                    <div class="col-sm-5">
                        <input type="text" name="username"
                               class="form-control"
                               placeholder="User name"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label"> Password: </label>
                    <div class="col-sm-5">
                        <input type="password" name="password"
                               class="form-control"
                               placeholder="Password"/>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="col-7 mx-auto"><input class="btn btn-outline-dark" type="submit" value="Войти"/></div>
            </form>
            <p><small>Если вы забыли пароль, нажмите <a href="#"><em>здесь.</em></a></small><br>
            <small>Для регистрации нажмите <a href="/registration"><em>РЕГИСТРАЦИЯ.</em></a></small></p>
        </div>
    </div>

</@c.page>

