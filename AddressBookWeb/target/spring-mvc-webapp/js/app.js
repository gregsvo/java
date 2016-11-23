



$(document).ready(function () {

    $(document).on("submit", "#create-form", function (e) {

        e.preventDefault();

        var addressData = JSON.stringify({
            firstName: $("#add-first-name").val(),
            lastName: $("#add-last-name").val(),
            address: $("#add-address").val(),
            city: $("#add-city").val(),
            state: $("#add-state").val(),
            zip: $("#add-zip").val()
        });

        $.ajax({
            type: "POST",
            url: contextRoot + "/address",
            data: addressData,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            }
        }).success(function (data, status) {

            //Clear the form
            $("#add-first-name").val("");
            $("#add-last-name").val("");
            $("#add-address").val("");
            $("#add-city").val("");
            $("#add-state").val("");
            $("#add-zip").val("");
            $('#validationErrors').empty();
            var tableRow = buildAddressRow(data);

            $("#contactsTable").append($(tableRow));

        }).error(function (data, status) {

            $.each(data.responseJSON.errors, function (index, item) {
                $('#' + item.fieldName + '-adderror').replaceWith(item.message);
                
            });
        });

    });

    $("#showAddressModal").on("show.bs.modal", function (e) {

        var link = $(e.relatedTarget);

        var addressId = link.data("address-id");

        var modal = $(this);

        $.ajax({
            type: "GET",
            url: contextRoot + "/address/" + addressId,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
        }).success(function (address, status) {

            modal.find("#address-firstName").text(address.firstName);
            modal.find("#address-lastName").text(address.lastName);
            modal.find("#address-address").text(address.address);
            modal.find("#address-city").text(address.city);
            modal.find("#address-state").text(address.state);
            modal.find("#address-zip").text(address.zip);
        });
    });

    $("#editAddressModal").on("show.bs.modal", function (e) {

        var link = $(e.relatedTarget);

        var addressId = link.data("address-id");

        var modal = $(this);

        $.ajax({
            type: "GET",
            url: contextRoot + "/address/" + addressId,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            }
        }).success(function (address, status) {

            modal.find("#edit-first-name").val(address.firstName);
            modal.find("#edit-last-name").val(address.lastName);
            modal.find("#edit-address").val(address.address);
            modal.find("#edit-city").val(address.city);
            modal.find("#edit-state").val(address.state);
            modal.find("#edit-zip").val(address.zip);

            $("#edit-id").val(address.id);
        });
    });

    $(document).on("click", "#edit-address-button", function (e) {
         $('.text-warning').empty();

        e.preventDefault();

        var addressId = $("#edit-id").val();

        $.ajax({
            type: "PUT",
            url: contextRoot + "/address/" + addressId,
            data: JSON.stringify({
                id: addressId,
                firstName: $("#edit-first-name").val(),
                lastName: $("#edit-last-name").val(),
                address: $("#edit-address").val(),
                city: $("#edit-city").val(),
                state: $("#edit-state").val(),
                zip: $("#edit-zip").val()
            }),
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            }
        }).success(function (data, status) {


            $("#editAddressModal").modal("hide");

            var tableRow = buildAddressRow(data);

            $("#address-row-" + data.id).replaceWith($(tableRow));
        })
                .error(function (data, status) {
                    console.log();

                    $.each(data.responseJSON.errors, function (index, item) {
                        $('#' + item.fieldName + '-error').replaceWith(item.message);
                    });
                });
    });

    $(document).on("click", ".delete-link", function (e) {

        e.preventDefault();

        var addressId = $(e.target).data("address-id");

        $.ajax({
            type: "DELETE",
            url: contextRoot + "/address/" + addressId
        }).success(function (data, status) {
            $("#address-row-" + addressId).remove();
        });
    });

    function buildAddressRow(data) {

        return "<tr id='address-row-" + data.id + "'> \n\
               <td><a data-address-id='" + data.id + "' data-toggle='modal' data-target='#showAddressModal'>" + data.firstName + " " + data.lastName + "</a></td> \n\
               <td> " + data.state + "</td> \n\
               <td> <a data-address-id='" + data.id + "' data-toggle='modal' data-target='#editAddressModal'>Edit</a> </td> \n\
               <td> <a data-address-id='" + data.id + "' class='delete-link'>Delete</a> </td> \n\
               </tr> ";

    }


});