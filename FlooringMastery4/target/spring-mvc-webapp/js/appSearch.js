$(document).ready(function () {


    $(document).on('submit', '#searchForm', function (e) {

        e.preventDefault();


        $.ajax({
            type: 'POST',
            url: contextRoot + "/flooring/search?search1=" + $('#add-search').val(),
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader('Content-type', 'multipart/form-data');
            }
        }).success(function (data, status) {

            //Clear the form
            $('#add-search').val("");
            $('#searchResults').empty();

            $('#searchResults').append("<tr><th class=\"col-md-1\">OrderNumber</th>\n\
                                            <th>Name</th>\n\
                                            <th>State</th>\n\
                                            <th>Date</th>\n\
                                            <th>Area</th>\n\
                                            <th>Total</th>\n\
                                            </tr>");
            $.each(data, function (index, Order) {
                var tableRow = buildShowRow(Order);

                $('#searchResults').append($(tableRow));

            });

        }).error(function (data, status) {

        });

    });
   

    function buildShowRow(data) {
        var firstDate = data.date;
        var mo = firstDate.substr(0,2);
        var dd = firstDate.substr(2,2);
        var yyyy = firstDate.substr(4);
        var newDate = mo +"/" +dd +"/" +yyyy;
        
        return "<tr>\n\
                    <td>" +data.order_id +"</td>\n\
                    <td>" +data.customer_name +"</td>\n\
                    <td>" +data.state +"</td>\n\
                    <td>" +newDate +"</td>\n\
                    <td>" +data.area +" ft<sup>2</sup></td>\n\
                    <td>$ " +data.total +"</td>\n\
                </tr>";
    }










});