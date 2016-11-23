$(document).ready(function () {

    $(document).on('submit', '#createForm', function (e) {

        e.preventDefault();

        var orderData = JSON.stringify({
            customerName: $('#add-customer-name').val(),
            state: $('#add-state').val(),
            productType: $('#add-product-type').val(),
            area: $('#add-area').val(),
            date: $('#dateHandle').val()
        });

        $.ajax({
            type: 'POST',
            url: contextRoot + "/flooring",
            data: orderData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            }
        }).success(function (data, status) {

            //Clear the form
            $('#add-customer-name').val("");
            $('#add-state').val(""); //As drop-downs, these might be a problem
            $('#add-product-type').val(""); //As drop-downs, these might be a problem
            $('#add-area').val("");


            var tableRow = buildOrderRow(data);

            $('#orderTable').append($(tableRow));

        }).error(function (data, status) {

        });

    });

    $("#showOrderModal").on("show.bs.modal", function (e) {

        console.log('got here');

        var link = $(e.relatedTarget);

        var orderId = link.data("order-id");

        var orderDate = $('#dateHandle').val();

        var orderDate2 = orderDate.replace(/\//g, '');

        var modal = $(this);

        $.ajax({
            type: "GET",
            url: contextRoot + "/flooring/" + orderId + "/" + orderDate2,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
        }).success(function (order, status) {

            modal.find("#order-customerName").text(order.customerName);
            modal.find("#order-state").text(order.state);
            modal.find("#order-productType").text(order.productType);
            modal.find("#order-area").text(order.area);
            modal.find("#order-date").text(orderDate);
        });
    });

    $("#editOrderModal").on("show.bs.modal", function (e) {

        var link = $(e.relatedTarget);

        var orderId = link.data("order-id");

        var modal = $(this);

        var orderDate = $('#dateHandle').val();

        var orderDate2 = orderDate.replace(/\//g, '');

        console.log(orderId);

        $.ajax({
            type: 'GET',
            url: contextRoot + '/flooring/' + orderId + "/" + orderDate2,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
        }).success(function (order, status) {
            modal.find("#edit-id").val(order.orderNumber);
            modal.find("#edit-customer-name").val(order.customerName);
            modal.find("#edit-state").val(order.state);
            modal.find("#edit-product-type").val(order.productType);
            modal.find("#edit-area").val(order.area);
            modal.find("#edit-date").val(orderDate);
        });
    });

    $(document).on('click', '#edit-order-button', function (e) {

            e.preventDefault(); 

        var orderId = $("#edit-id").val();

        var orderDate = $('#dateHandle').val();

        var orderDate2 = orderDate.replace(/\//g, '');

        $.ajax({
            type: "PUT",
            url: contextRoot + "/flooring/" + orderId + "/" + orderDate2,
            data: JSON.stringify({
                id: orderId,
                customerName: $("#edit-customer-name").val(),
                state: $("#edit-state").val(),
                productType: $("#edit-product-type").val(),
                area: $("#edit-area").val()
            }),
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            }
        }).success(function (data, status) {
            $('#editOrderModal').modal('hide');
            var tableRow = buildOrderRow(data);
            $('#newOrderRow-' + orderId).replaceWith($(tableRow));
         
        });
    });

    $(document).on('click', '.delete-link', function (e) {

        e.preventDefault();

        var orderId = $(e.target).data("order-id");

        var orderDate = $('#dateHandle').val();

        var orderDate2 = orderDate.replace(/\//g, '');

        $.ajax({
            type: "DELETE",
            url: contextRoot + "/flooring/" + orderId + "/" + orderDate2,
        }).success(function (data, status) {
            $("#newOrderRow-" + orderId).remove();
        });

    });

    function buildOrderRow(data) {

        return "<tr id='newOrderRow-" + data.orderNumber + "'>  \n\
                <td> <a data-order-id='" + data.orderNumber + "'data-toggle='modal' data-target='#showOrderModal'>" + data.orderNumber + " </a></td > \n\
                <td> <a data-order-id='" + data.orderNumber + "' data-toggle='modal' data-target='#showOrderModal'> " + data.customerName + "</a></td > \n\
                <td> <a data-order-id='" + data.orderNumber + "' data-toggle='modal'data-target='#editOrderModal'> Edit </a></td > \n\
                <td> <a data-order-id='" + data.orderNumber + "' class='delete-link'> Delete </a>  </td> \n\
                </tr>";

    }

});




