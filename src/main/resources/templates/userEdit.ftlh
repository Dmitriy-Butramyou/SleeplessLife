<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
	<div class="mt-4">
		<h4 class="text-center">Изменение данных регистрации</h4>
		<div class="row mt-3">
			<div class="col-4">
          <#--                todo - можно рекламу запихать)))-->
			</div>
			<div class="col-4">
				<form action="/user/edit/${user.customerLink}" method="post">
					<div class="form-group row">
						<label class="col-sm-4 col-form-label"> User Name: </label>
						<div class="col-sm-8">
							<input type="text" name="name"
										 class="form-control"
										 value="${user.name}"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label"> Custom Link: </label>
						<div class="col-sm-8">
							<input type="text" name="newLink"
										 class="form-control"
										 value="${user.customerLink}"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label"> Email: </label>
						<div class="col-sm-8">
							<input type="text" name="email"
										 class="form-control"
										 value="${user.email}"/>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label"> Password: </label>
						<div class="col-sm-8">
							<input type="password" name="password"
										 class="form-control""/>
						</div>
					</div>
            <#if isAdmin>
                <#list roles as role>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="checkbox" id="inlineCheckbox1"
													 name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
										<label class="form-check-label" for="inlineCheckbox1">${role}</label>
									</div>
                </#list>
            </#if>
					<div>
						<input type="hidden" name="_csrf" value="${_csrf.token}"/>
						<button class="btn btn-primary" type="submit">Сохранить</button>
					</div>
				</form>
			</div>
			<div class="col-4">
          <#--                todo - можно рекламу запихать)))-->
			</div>
		</div>
	</div>
	</div>
</@c.page>