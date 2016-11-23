<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="showOrderModal" class="modal fade" role="dialog">
     <div class="modal-dialog">

     <!-- Modal content-->
        <div class="modal-content">
         <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
         <h4 class="modal-title">Order Details</h4>
         </div>
         <div class="modal-body">
             
           <table class="table table-bordered">
                  
               <tr>
                   <th>Customer Name</th>
                   <td id="order-customerName"></td>
               </tr>
               <tr>
                   <th>State</th>
                   <td id="order-state"></td>
               </tr>
               <tr>
                   <th>Product Type</th>
                   <td id="order-productType"></td>
               </tr>               
               <tr>
                   <th>Area</th>
                   <td id="order-area"></td>
               </tr> 
               <tr>
                   <th>Date</th>
                   <td id="order-date"></td> 
               </tr>
           </table>    
               
             
             
           
         </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
        </div>

     </div>
    </div>             
            
             <!-- Modal -->
    <div id="editOrderModal" class="modal fade" role="dialog">
     <div class="modal-dialog">

     <!-- Modal content-->
        <div class="modal-content">
         <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
         <h4 class="modal-title">Edit Order</h4>
         </div>
         <div class="modal-body">
             
           <table class="table table-bordered">
                  
               <tr>
                   <th>Customer Name</th>
                   <td>
                       <input type="text" id="edit-customer-name" class="form-control" />
                   </td>
               </tr>
               <tr>
                   <th>State</th>
                   <td>
                        <select class="form-control" name="stateEdit" id="edit-state">
                                <option disabled selected hidden>Add state ...</option>
                                <option value="OH">OH</option>
                                <option value="PA">PA</option>
                                <option value="IN">IN</option>
                                <option value="MI">MI</option>
                        </select>
                   </td>
               </tr>
               <tr>
                   <th>Product Type</th>
                   <td>
                        <select class="form-control" name="productTypeEdit" id="edit-product-type">
                                <option disabled selected hidden>Add product ...</option>
                                <option value="Laminate">Laminate</option>
                                <option value="Carpet">Carpet</option>
                                <option value="Wood">Wood</option>
                                <option value="Tile">Tile</option>
                        </select>
                   </td>
               </tr>               
               <tr>
                   <th>Area</th>
                   <td>
                       <input type="text" id="edit-area" class="form-control"/>
                   </td>
               </tr>   
<!--               <tr>
                   <th>Date</th>
                   <td>
                       <input type="text" id="edit-date" value="${order.date}" class="form-control" />
                   </td>
               </tr>-->
           </table>    
               
             <input type="hidden" id="edit-id" />
            <button id="edit-order-button" class="btn btn-default">Edit Order</> 
             
           
         </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
        </div>

     </div>
    </div>
