$(document).ready(function () {
//ADD DVD function
    $(document).on("submit", "#create-form", function (e) { //SUBMIT button press on index page, below data is read from inputs, and stringified into JSON object.

        e.preventDefault(); //prevent the default behavior of the HTML from firing.

        //////////////////////////////////////////////////////////////////////////CREATE DVD

        //begin creation of JSON stringify object
        var dvdData = JSON.stringify({//Variable "dvdData" created
            title: $('#add-title').val(), //title added to string from text input box on index.jsp
            director: $('#add-director').val(), //director added to string from text input box on index.jsp
            studio: $('#add-studio').val(), //studio added to string from text input box on index.jsp
            mpaaRating: $('#add-mpaaRating').val(), //MPAA Rating added to string from text input box on index.jsp
            releaseYear: $('#add-releaseYear').val() //Release Year added to string from text input box on index.jsp
        });
        $.ajax({//begin Ajax code
            type: "POST", //This REST command will set the type to "POST" and goes to the URL below
            url: contextRoot + "/dvd", //This url will be given the "POST" command, with the dvdData Stringified JSON object above (line 7-13)
            data: dvdData, //the data to be sent to /dvd will be dvdData, defined above.
            dataType: "json", //sets the datatype to JSON
            beforeSend: function (xhr) { //A function that is defined by Jquery, that sets request headers. Spring needs this.
                xhr.setRequestHeader("Accept", "application/json"); //"accept" is what javascript is commanding the behavior to accept: "application/json"
                xhr.setRequestHeader("Content-type", "application/json"); //"Content-type" is what it expects back: "application/json"
            }
        }).success(function (data, status) {//If the above goes through on the controller, clear the form with ".success", if fails, runs ".error".

//Below code runs, clearing each row
            $('#add-title').val(""); //row cleared
            $('#add-director').val(""); //row cleared
            $('#add-studio').val(""); //row cleared
            $('#add-mpaaRating').val(""); //row cleared
            $('#add-releaseYear').val(""); //row cleared
            $('#validationErrors').empty();
            //Form has been cleared

            var tableRow = buildDvdRow(data); //Another row is appended to display the new dvd, added to the DvdRow

            $('#dvdTable').append($(tableRow)); //row appended


        }).error(function (data, status) {//error function
            $.each(data.responseJSON.fieldErrors, function (index,
                    validationError) {
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
            });
        }); //END "Error" function
    }); //END "ADD DVD (create form)" function


    /////////////////////////////////////////////////////////////////////VIEW DVD function

    $('#showDvdModal').on('show.bs.modal', function (e) {//#-Hashtag refers to an ID of an element on our Index page, .on(or when) click the bs(bootstrap) modal runs, the function will run. 
        var link = $(e.relatedTarget); //Variable "link" is assigned to whatever the value of e.relatedTarget is.

        var dvdId = link.data('dvd-id'); // var "dvdId" set

        var modal = $(this); //this modal is set to equal $('#showDvdModal').on('show.bs.modal', function (e)

        $.ajax({
            type: "GET",
            url: contextRoot + "/dvd/" + dvdId,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json"); //"accept" is what javascript is commanding the behavior to accept: "application/json"
                xhr.setRequestHeader("Content-type", "application/json"); //"Content-type" is what it expects back: "application/json"
            }
        }).success(function (dvd, status) {//If the above goes through on the controller, clear the form with ".success", if fails, runs ".error".

//We use .text because we're not putting this in an input form, we're putting it in a <td>
            modal.find('#dvd-title').text(dvd.title); //Finds that element within our modal, and sets it to the title.
            modal.find('#dvd-director').text(dvd.director); ////Finds that element within our modal, and sets it to the director.
            modal.find('#dvd-studio').text(dvd.studio); //Finds that element within our modal, and sets it to the studio.
            modal.find('#dvd-mpaaRating').text(dvd.mpaaRating); //Finds that element within our modal, and sets it to the MPAA Rating.
            modal.find('#dvd-releaseYear').text(dvd.releaseYear); //Finds that element within our modal, and sets it to the Release Year.

        }); //END Success function

    }); //END Show Function


    ///////////////////////////////////////////////////////////////////////EDIT DVD function

    
    $('#editDvdModal').on('show.bs.modal', function(e) {//#-Hashtag refers to an ID of an element on our Index page, .on(or when) click the bs(bootstrap) modal runs, the function will run. 
        var link = $(e.relatedTarget); //Variable "link" is assigned to whatever the value of e.relatedTarget is.

        var dvdId = link.data('dvd-id'); // var "dvdId" set

        var modal = $(this); //this modal is set to equal $('#editDvdModal').on('edit.bs.modal', function (e)

        $.ajax({
            type: "GET",
            url: contextRoot + "/dvd/" + dvdId,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json"); //"accept" is what javascript is commanding the behavior to accept: "application/json"
                xhr.setRequestHeader("Content-type", "application/json"); //"Content-type" is what it expects back: "application/json"
            }
        }).success(function (dvd, status) {//If the above goes through on the controller, clear the form with ".success", if fails, runs ".error".

//We use .val because we ARE putting this in an input field.
            modal.find('#edit-title').val(dvd.title); //Finds that element within our modal, and sets it to the title.
            modal.find('#edit-director').val(dvd.director); ////Finds that element within our modal, and sets it to the director.
            modal.find('#edit-studio').val(dvd.studio); //Finds that element within our modal, and sets it to the studio.
            modal.find('#edit-mpaaRating').val(dvd.mpaaRating); //Finds that element within our modal, and sets it to the MPAA Rating.
            modal.find('#edit-releaseYear').val(dvd.releaseYear); //Finds that element within our modal, and sets it to the Release Year.

            $('#edit-id').val(dvdId);
        });
    });
    
    $(document).on('click', '#edit-dvd-button', function (e) {
        
        e.preventDefault();
        
        var dvdId = $('#edit-id').val();
        $.ajax({
            type: 'PUT',
            url: contextRoot + "/dvd/" + dvdId,
            data: JSON.stringify({
                id: dvdId,

                title: $('#edit-title').val(), 
                director: $('#edit-director').val(),
                studio: $('#edit-studio').val(), 
                mpaaRating: $('#edit-mpaaRating').val(), 
                releaseYear: $('#edit-releaseYear').val() 


            }),
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json"); //"accept" is what javascript is commanding the behavior to accept: "application/json"
                xhr.setRequestHeader("Content-type", "application/json"); //"Content-type" is what it expects back: "application/json"
            }
        }).success(function (data, status) {//If the above goes through on the controller, clear the form with ".success", if fails, runs ".error".
            $('#editDvdModal').modal('hide');
            var tableRow = buildDvdRow(data);
            $('#dvd-row-' + data.id).replaceWith($(tableRow));
            $('#editValidationErrors').empty();
        });
        
        }).error(function (data, status) {
 // #2 - Go through each of the fieldErrors and display the associated error message in the validationErrors div
 $.each(data.responseJSON.fieldErrors, function (index,validationError) {
 var errorDiv = $('#editValidationErrors');
 errorDiv.append(validationError.message).append($('<br>'));
 });

    });
    //This function is buildiing HTML that gets populated on the index.jsp page when we are adding or editing a DVD object. It is called in the "edit" and "add" functions above, and the new
    //or updated DVD is displayed within.

    $(document).on('click', '.delete-link', function (e) {

        e.preventDefault();
        var dvdId = $(e.target).data('dvd-id');
        $.ajax({
            type: 'DELETE',
            url: contextRoot + "/dvd/" + dvdId
        }).success(function (data, status) {
            $('#dvd-row-' + dvdId).remove();
        });
    });
    function buildDvdRow(data) {
        return "<tr id='dvd-row-" + data.id + "'> \n\
    <td><a data-dvd-id='" + data.id + "'data-toggle='modal' data-target='#showDvdModal' style=\"cursor:pointer;\">" + data.title + " " + data.releaseYear + "</a></td>\n\
    <td> " + data.director + "</td>\n\
    <td><a data-dvd-id='" + data.id + "'data-toggle='modal' data-target='#editDvdModal' style=\"cursor:pointer;\">Edit</a></td> \n\
    <td><a data-dvd-id='" + data.id + "'class='delete-link' style=\"cursor:pointer;\">Delete</a></td> \n\</tr> ";
    }//end "buildDvdRow" function



}); //app end

