$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

//SAVE============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateInquiryForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "InquiryAPI",
 type : type,
 data : $("#formInquiry").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onInquiryComplete(response.responseText, status);
 }
 });
});

function onInquirySaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divUsersGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
 $("#hidUserIDSave").val("");
 $("#formUser")[0].reset();
}

// UPDATE==========================================

$(document).on("click", ".btnUpdate", function(event)
{
$("#hidUserIDSave").val($(this).data("id"));
 $("#refno").val($(this).closest("tr").find('td:eq(0)').text());
 $("#uname").val($(this).closest("tr").find('td:eq(1)').text());
 $("#email").val($(this).closest("tr").find('td:eq(2)').text());
 $("#pnum").val($(this).closest("tr").find('td:eq(3)').text());
 $("#des").val($(this).closest("tr").find('td:eq(4)').text());

});


//REMOVE==============================================

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "InquiryAPI",
 type : "DELETE",
 data : "id=" + $(this).data("id"),
 dataType : "text",
 complete : function(response, status)
 {
 onInquiryDeleteComplete(response.responseText, status);
 }
 });
});


function onInquiryDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUsersGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

// CLIENT-MODEL================================================================
function validateInquiryForm()
{
// Reference No
if ($("#refno").val().trim() == "")
 {
 return "Insert Reference No.";
 }
// User Name
if ($("#uname").val().trim() == "")
 {
 return "Insert User Name.";
 } 
 
// EMAIL-------------------------------
if ($("#email").val().trim() == "")
 {
 return "Insert Email.";
 }

// Contact No------------------------
if ($("#pnum").val().trim() == "")
 {
 return "Insert Contact No.";
 }

//  Description------------------------
if ($("#des").val().trim() == "")
 {
 return "Insert  Description.";
 }
}