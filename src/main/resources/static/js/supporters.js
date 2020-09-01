let headerhtml= `
<span id="modalHTml"></span>
<span id="waringId"></span>
    <div class="row">
    <div class="col-md-12 d-none d-sm-none d-md-block headerDiv">
      <img src="img/logo1.jpg">
       <p class="font-weight-normal" style="font-size:7ch; text-align:center;"> Karnataka State co-operative Federation Ltd.,</p>
      <p style="text-align:center; font-size: medium; color: rgb(121, 171, 236);"> Diploma in Co-operative Management (Distance Education)</p>
     </div>
  </div>

 <!--Nav bar-->
		<nav class="navbar navbar-expand-lg navbar-dark bg-warning orange">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/">Home
							Page</a></li>
					<li class="nav-item"><a class="nav-link" data-toggle="modal" data-target="#modalAbandonedCart" href="/new">Apply
							Online</a></li>
					<li class="nav-item"><a class="nav-link" href="/status">Status</a></li>
					<li class="nav-item"><a class="nav-link" href="/contact">Contact
							Us</a></li>
				</ul>
			</div>
			<button type="button" id="changetabbutton9" onclick="location.href='/admin';" class="btn btn-success ">Admin Login</button>
		</nav>`;
let footerhtml= `<hr class="mt-2 mb-3"/> 
  <div class="bs-example">
    <div class="bg-warning d-flex justify-content-between">
        <div>&nbsp&nbspOwned And Maintained By: Karnataka State co-operative Federation Ltd., </div>
        <div>Designed and Deveploed By: www.apito.in&nbsp&nbsp</div>
    </div>
</div>`;
let righthtml= `<ul>
    <li>
      Phone no: +91 8892075276
       ,+91 8660193339  
    </li>
    <li>
      E-mail: apitotechnologies@gmail.com
    </li>
   
  </ul>`;
let lefthtml= `<ul>
	<li><a href="./resource/Noc or Deputing Letter.pdf" rel="nofollow">
			NOC template</a></li>
	<li><a href="./resource/Service Certificate.pdf" rel="nofollow">
			Service Certificate template</a></li>
	<li><a href="/offline">Offline Application Form</a></li>
</ul>`;
let warningHtml= `<div class="modal fade right" id="modalAbandonedCart" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="false">
		<div
			class="modal-dialog modal-side modal-bottom-right modal-notify modal-info"
			role="document">
			<!--Content-->
			<div class="modal-content">
				<!--Header-->
				<div class="modal-header">
					<p class="heading" style="padding-left: 10%;">
					<h2>WARNING!!!</h2>
					</p>

					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true" class="white-text">&times;</span>
					</button>
				</div>

				<!--Body-->
				<div class="modal-body">

					<div class="row">
						<div class="col-3">
							<p></p>
							<p class="text-center">
								<i class="fas fa-shopping-cart fa-4x"></i>
							</p>
						</div>

						<div>
							<p style="padding-left: 10%;"> Please keep the following documents
								scanned in required format and please keep the
								restricted file to 1mb</p>
								
							   <ul>
					              <li>NOC</li>
					              <li>Photo Copy</li>
					              <li>Signature</li>
					              <li>Address Proof</li>
					              <li>Employee Certificate</li>
					            </ul>	
						</div>
					</div>
				</div>

				<!--Footer-->
				<div class="modal-footer justify-content-center">
					<a type="button" class="btn btn-info" href="/new">Proceed</a>
					<a type="button" class="btn btn-outline-info waves-effect"
						data-dismiss="modal" href="#">Cancel</a>
				</div>
			</div>
			<!--/.Content-->
		</div>
	</div>`;
$('#headerId').html(headerhtml);
$('#footerId').html(footerhtml);
$('#leftId').html(lefthtml);
$('#rightId').html(righthtml);
$('#waringId').html(warningHtml);