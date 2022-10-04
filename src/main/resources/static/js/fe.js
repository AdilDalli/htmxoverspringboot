function confirm(el) {
	Swal.fire({
		title: 'Confirm',
		text: 'Do you want to continue?',
		showDenyButton: true,
		showCancelButton: false,
		confirmButtonText: 'Confirm',
	}).then((result) => {
		/* Read more about isConfirmed, isDenied below */
		if (result.isConfirmed)
			var event = new Event('confirmed');
		el.dispatchEvent(event);
		console.log("confirmed!!!!");
	})
};



//function closeModal() {
//	var container = document.getElementById("modals-here")
//	var backdrop = document.getElementById("modal-backdrop")
//	var modal = document.getElementById("modal")
//
//	modal.classList.remove("show")
//	backdrop.classList.remove("show")
//
//	setTimeout(function() {
//		container.removeChild(backdrop)
//		container.removeChild(modal)
//	}, 200)
//}


//modam behaviour




//var openModal = document.getElementById("modal");
//openModal.addEventListener('htmx:afterOnLoad', event => {
//	setTimeout(function() {
//		var backdrop = document.getElementById("modal-backdrop")
//		var modal = document.getElementById("modal")
//
//		modal.classList.add("show")
//		backdrop.classList.add("show")
//	}, 500)
//});
