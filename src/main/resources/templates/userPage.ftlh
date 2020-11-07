<#import "parts/common.ftlh" as c>
<#import "parts/navigation.ftlh" as n>

<@c.page>
    <@n.navigation>

			<h2 class="text-center">Страница пользователя</h2>

			<div class="display-flex">
				<div class="display-grid">
					<img src="/img/default.jpg" alt="Фото" class="personalPhoto">
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

			<div class="popup">
				<div class="popup_content">
					<form enctype="multipart/form-data" action="/user/uploadPersonalPhoto/{customerLink}" method="post">
						<input type="file" accept="image/*">
						<div class="close_popup_content">+</div>
						<div class="button_to_down">
							<input type="hidden" name="_csrf" value="${_csrf.token}"/>
							<button type="submit" id="complete_upload_personal_photo">Upload Photo</button>
						</div>
					</form>
				</div>
			</div>

			<script>
          document.getElementById("uploadPersonalPhoto").addEventListener("click", function () {
              document.querySelector(".popup").style.display = "flex";
          });

          document.querySelector('.close_popup_content').addEventListener('click', function () {
              document.querySelector(".popup").style.display = "none";
          })
			</script>

    </@n.navigation>
</@c.page>