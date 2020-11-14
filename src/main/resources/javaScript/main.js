"use strict";
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