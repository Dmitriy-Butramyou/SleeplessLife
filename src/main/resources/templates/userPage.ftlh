<#import "parts/common.ftlh" as c>
<#import "parts/navigation.ftlh" as n>

<@c.page>
    <@n.navigation>

			<h2 class="text-center">Страница пользователя</h2>

			<div class="display-flex">
				<div class="display-grid">
					<img src="/img<#if pathToPersonalPhoto??>${pathToPersonalPhoto}<#else>/default.jpg</#if>"
							 onerror="this.onerror=null; this.src='/img/default.jpg'" alt="Фото" class="personal-photo">
					<button type="button" id="uploadPersonalPhoto">Replace photo</button>
				</div>
				<div>
					<h4 class="text-center">Table with information</h4>
					<table>
						<tr>
							<th>Firstname</th>
							<th>Lastname</th>
							<th>Age</th>
						</tr>
						<tr>
							<td>Jill</td>
							<td>Smith</td>
							<td>50</td>
						</tr>
					</table>
				</div>
			</div>

    </@n.navigation>
</@c.page>