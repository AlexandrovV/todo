$(function() {
	
	// Adding date to the form
	$(document).on("click", ".addButton", function () {
	     var date = $(this).data('id');
	     $('#showDate').text(date);
	     $("#date").val(date);
	    $('#showDate').modal('show');
	});
	
	var $todoForm = $('#todoForm');
	
	if($todoForm.length) {
		$todoForm.validate({
			rules : {
				todo : {
					required: true
				},
				till : {
					required: true
				}
			},
			messages : {
				todo : {
					required: 'You cant leave this line blank',
					minlength: 1
				},
				till : {
					required: 'You cant leave this line blank'
				}
			},
			errorElement: 'em',
			errorPlacement : function(error, element) {
				// add the class of help-block
				error.addClass('help-block');
				// add the error element after the input element
				error.insertAfter(element);
			}
		});
	}

	$('#myModal').on('hidden.bs.modal', function () {
		$('#myModal').modal('hide');
		$('body').removeClass('modal-open');
		$('.modal-backdrop').remove();
	})
	

});