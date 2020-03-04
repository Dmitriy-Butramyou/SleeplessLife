<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="mt-4">
        <h4 class="text-center">Добавление нового пользователя</h4>
        <div class="row mt-3">
            <div class="col-4">
                <small class="text-center"><#if message??>${message}</#if></small>
            </div>
            <div class="col-4">
                <form action="/registration" method="post">
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label"> User Name: </label>
                        <div class="col-sm-8">
                            <input type="text" name="name"
                                   class="form-control"
                                   placeholder="User name"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label"> Email: </label>
                        <div class="col-sm-8">
                            <input type="text" name="email"
                                   class="form-control"
                                   placeholder="User name"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label"> Password: </label>
                        <div class="col-sm-8">
                            <input type="password" name="password"
                                   class="form-control"
                                   placeholder="Password"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label"> Password: </label>
                        <div class="col-sm-8">
                            <input type="password" name="password2"
                                   class="form-control"
                                   placeholder="Retype password"/>
                        </div>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-primary" type="submit">Создать</button>
                </form>
            </div>
            <div class="col-4">
            </div>
        </div>
    </div>
    </div>

</@c.page>




