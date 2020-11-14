"use strict";
document.addEventListener("DOMContentLoaded", () => {
	const userId = document.querySelector('#userId').value;
	const csrf = document.querySelector('#csrf').value;

	const popUp = document.querySelector("#pop-up"),
		closePopUpCross = popUp.querySelector('#close-pop-up-cross'),
		center = popUp.querySelector('.center'),
		content = popUp.querySelector('.content');

	closePopUpCross.addEventListener('click', () => {
		popUp.classList.add('hide');
	});
	center.addEventListener('click', (event) => {
		if (event.target !== center) return;
		popUp.classList.add('hide');
	});

	/**
	 * Отображаем pop-up для загрузки аватарки
	 */
	document.querySelector('#uploadPersonalPhoto').addEventListener("click", () => {
		content.innerHTML = `
			<form enctype="multipart/form-data" action="/user/uploadPersonalPhoto/${userId}" method="post">
    		<label data-label for="select-personal-photo">Choose Image</label>
      	<input type="file" name="photo" id="select-personal-photo" accept="image/*">
      	<div class="preview">
      		<img id="personal-photo-preview" class="hide">
      	</div>
      	<input type="hidden" name="_csrf" value='${csrf}'/>
      </form>
    `;

		popUp.classList.remove('hide');

		const btnSelectPersonalPhoto = document.querySelector('#select-personal-photo');
		btnSelectPersonalPhoto.addEventListener("change", (event) => {
			showPreviewPhoto(event);
		});

		//создание блока div с кнопками
		const photoUploadBtn = document.createElement('div');
		photoUploadBtn.classList.add('display-flex');
		photoUploadBtn.innerHTML = `<label id="replace-personal-photo" for="select-personal-photo">Replace</label>
                 <label id="upload-personal-photo">Upload</label>`;


		/**
		 * Заменяет кнопки в форме, отображает preview изображения
		 * @param event улемент страницы
		 */
		function showPreviewPhoto(event) {
			if (event.target.files.length > 0) {
				const label = content.querySelector('[data-label]');
				if (label) {
					label.replaceWith(photoUploadBtn);
					content.querySelector('#upload-personal-photo').addEventListener('click', () =>	{
						content.firstElementChild.submit();
					});
				}
				const src = URL.createObjectURL(event.target.files[0]);
				const preview = content.querySelector("#personal-photo-preview");
				preview.src = src;
				preview.classList.add('show');
				preview.classList.remove('hide');
			}
		}
	});
});