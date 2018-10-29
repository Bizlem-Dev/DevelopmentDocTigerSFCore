<html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<head>
<title>File Upload Example in Java web application</title>
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery-ui.theme.min.css">
<style>
.message {
	color: red;
	font-size: 1.00em;
}
</style>
</head>
<body>
	<div class="row">
		<div class="col-md-10">

			<div class="panel with-nav-tabs panel-default">
				<div class="panel-heading">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#tab1success" data-toggle="tab">Template Upload</a></li>
						<li><a href="#tab2success" data-toggle="tab">Download/Delete Templates</a></li>
					</ul>
				</div>
				<div class="panel-body">
					<div class="tab-content">
						<div class="tab-pane fade in active" id="tab1success">
							<div class="panel panel-success">
								<div class="panel-heading">
									<i class="fa fa-upload">&nbsp;<b>Template Upload</b></i>
								</div>
								<div class="panel-body">
									<p class="message">${requestScope["message"]}</p>
									<h3>Choose Template to be Upload</h3>
									<form action="FileUpload" method="post"	enctype="multipart/form-data">
										<div class="form-group">
											<label for="file">Choose (only docx, rtf):</label> <input type="file" name="file" class="form-control" accept=".rtf,.docx,.xlsx" required/>
										</div>
										<div class="form-group">
										  <label for="outputFormats">Select Output Formats:</label>
										  <select class="form-control" id="outputFormats" name="outputFormats">
										    <option value="pdf" >PDF</option>
										    <option value="rtf">RTF</option>
										    <option value="docx">DOCX</option>
										  </select>
										 </div>
										 <div class="form-group">
										  <label for="uploadDocumentLanguageType">Select Document Type:</label>
										  <select class="form-control" id="uploadDocumentLanguageType" name="uploadDocumentLanguageType">
										    <option value="English" >English</option>
										    <option value="Arabic">Arabic</option>
										  </select>
										 </div>
										<input type="hidden" name="pageName" id="pageName" value="TemplateUpload" />
										<input type="submit" value="Upload" class="btn btn-success" />
									</form>
								</div>
							</div>
						</div>
						<div class="tab-pane fade" id="tab2success">
							<div class="panel panel-success">
								<div class="panel-heading">
									<i class="fa fa-pencil-square-o">&nbsp;<b>Download/Delete Templates</b></i>
								</div>
								<div class="panel-body">
									<p><span class="message" id="message"></span></p>
									<h3>Choose Template to be Download/Delete</h3>
									<form id="downloadDeleteTemplates"  method="post">
										<div class="form-group">
										  <label for="templates">Select Templates:</label>		
										  <select name="templates" id="templates" class="form-control" required>
										  	  <option value="-1">-- Select Option --</option>
											  <c:forEach items="${templateList}" var="templateName">
											    <option value="${templateName}">${templateName}</option>
											  </c:forEach>
										   </select>
										</div>
										<input type="hidden" name="pageName" id="pageName" value="TemplateDelete" />
										<a href="javascript:downloadTemplate();" class="btn btn-success"> Download </a>
										<!-- <input type="button" value="Download" class="btn btn-success" onClick="downloadTemplate();"/>-->
										<input type="button" value="Delete" class="btn btn-danger" onClick="deleteSelectedTemplate();"/>													
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script>

function downloadTemplate(){
	if($('#templates').val() == '-1'){
		$('#message').html('Please select template from dropdown before proceeding');
		return;
	}else{
		var URL='';
		URL +='/DocGeneration/FileUpload?pageName=TemplateDownload&templates='+$('#templates').val();
		window.location= URL;
	}
	
}

function deleteSelectedTemplate(){
	if($('#templates').val() == '-1'){
		$('#message').html('Please select template from dropdown before proceeding');
	}else{
		var r = confirm("Do you want to delete the selected template.");
	    if (r == true) {
			var postData = {};
			postData.pageName = 'TemplateDelete';
			postData.templates = $('#templates').val();
			
			   $.ajax({
			        type: "POST",
			        url: '/DocGeneration/FileUpload',
			        data : postData,
			        success: function(data) {
			        	$('#message').html(data);
			        	$("#templates option[value='"+postData.templates+"']").remove();
			        },  
			        error: function(msg) {
			        	$('#message').html(msg);
			        }
			    });
	    } else {
	        return;
	    }
	}
	
}
</script>