$(function(){

	$('#sslc_marks').change(function(){

	    var obtained = document.getElementById('sslc_marks').value;
	    var total = document.getElementById('sslc_totalmarks').value;


	    if(total == ''){
	      return;
	    }

	    else if(parseInt(obtained) <= parseInt(total)){

	      document.getElementById('sslc_percentage').value = (parseInt(obtained) / parseInt(total)) * 100;
	    }else{
	      alert('obtained marks cannot be more than total marks.Re-Enter your marks');
	      
	       document.getElementById('sslc_marks').value='';
	   document.getElementById('sslc_totalmarks').value='';
	   document.getElementById('sslc_marks').focus();
	    }

	  });
	  
	  //test comment

	  $('#puc_marks').change(function(){

	    var obtained = document.getElementById('sslc_marks').value;
	    var total = document.getElementById('sslc_totalmarks').value;


	    if(total == ''){
	      return;
	    }

	    else if(parseInt(obtained) <= parseInt(total)){

	      document.getElementById('sslc_percentage').value = (parseInt(obtained) / parseInt(total)) * 100;
	    }else{
	      alert('obtained marks cannot be more than total marks.Re-Enter your marks');
	      
	       document.getElementById('sslc_marks').value='';
	   document.getElementById('sslc_totalmarks').value='';
	   document.getElementById('sslc_marks').focus();
	    }

	  });
	  $('#ug_marks').change(function(){

	    var obtained = document.getElementById('sslc_marks').value;
	    var total = document.getElementById('sslc_totalmarks').value;


	    if(total == ''){
	      return;
	    }

	    else if(parseInt(obtained) <= parseInt(total)){

	      document.getElementById('sslc_percentage').value = (parseInt(obtained) / parseInt(total)) * 100;
	    }else{
	      alert('obtained marks cannot be more than total marks.Re-Enter your marks');
	      
	       document.getElementById('sslc_marks').value='';
	   document.getElementById('sslc_totalmarks').value='';
	   document.getElementById('sslc_marks').focus();
	    }

	  });
	  $('#pg_marks').change(function(){

	    var obtained = document.getElementById('sslc_marks').value;
	    var total = document.getElementById('sslc_totalmarks').value;


	    if(total == ''){
	      return;
	    }

	    else if(parseInt(obtained) <= parseInt(total)){

	      document.getElementById('sslc_percentage').value = (parseInt(obtained) / parseInt(total)) * 100;
	    }else{
	      alert('obtained marks cannot be more than total marks.Re-Enter your marks');
	      
	       document.getElementById('sslc_marks').value='';
	   document.getElementById('sslc_totalmarks').value='';
	   document.getElementById('sslc_marks').focus();
	    }

	  });
	$('#sslc_totalmarks').change(function(){

	    var obtained = document.getElementById('sslc_marks').value;
	    var total = document.getElementById('sslc_totalmarks').value;

	    if(parseInt(obtained) <= parseInt(total)){

	      document.getElementById('sslc_percentage').value = (parseInt(obtained) / parseInt(total)) * 100;
	    }else{
	      alert('obtained marks cannot be more than total marks.Re-Enter your marks');
	      
	       document.getElementById('sslc_marks').value='';
	   document.getElementById('sslc_totalmarks').value='';
	   document.getElementById('sslc_marks').focus();
	    }

	  });
	  $('#puc_totalmarks').change(function(){

	    var obtained = document.getElementById('puc_marks').value;
	    var total = document.getElementById('puc_totalmarks').value;

	    if(parseInt(obtained) <= parseInt(total)){

	      document.getElementById('puc_percentage').value = (parseInt(obtained) / parseInt(total)) * 100;
	    }else{
	      alert('obtained marks cannot be more than total marks.Re-Enter your marks');
	      
	      document.getElementById('puc_marks').value='';
	      document.getElementById('puc_totalmarks').value='';
	      document.getElementById('puc_marks').focus();

	    }

	  });
	  $('#ug_totalmarks').change(function(){

	    var obtained = document.getElementById('ug_marks').value;
	    var total = document.getElementById('ug_totalmarks').value;

	    if(parseInt(obtained) <= parseInt(total)){

	      document.getElementById('ug_percentage').value = (parseInt(obtained) / parseInt(total)) * 100;
	    }else{
	      alert('obtained marks cannot be more than total marks.Re-Enter your marks');
	       document.getElementById('ug_marks').value='';
	      document.getElementById('ug_totalmarks').value='';
	      document.getElementById('ug_marks').focus();
	    }

	  });
	  $('#pg_totalmarks').change(function(){

	    var obtained = document.getElementById('pg_marks').value;
	    var total = document.getElementById('pg_totalmarks').value;

	    if(parseInt(obtained) <= parseInt(total)){

	      document.getElementById('pg_percentage').value = (parseInt(obtained) / parseInt(total)) * 100;
	    }else{
	      alert('obtained marks cannot be more than total marks.Re-Enter your marks');
	     document.getElementById('pg_marks').value='';
	      document.getElementById('pg_totalmarks').value='';
	      document.getElementById('pg_marks').focus(); 
	    }

	  });
	  
    $('#changetabbutton1').click(function(e){
      
      
      var flag = 0;
      
      var mobileNumber = document.getElementById('mobile_number').value;
      var poc = document.getElementById('poc').value;
      var name = document.getElementById('name').value;
      var f_name = document.getElementById('father_name').value;
      var m_name = document.getElementById('mother_name').value;
     
      var sex = document.getElementById('sex').value;
      var dob = document.getElementById('dob').value;
      var age = document.getElementById('age').value;
      var religion = document.getElementById('religion').value;
      var caste = document.getElementById('caste').value;
      var nationality = document.getElementById('nationality').value;
      var address = document.getElementById('address').value;
      var pincode = document.getElementById('pincode').value;
      var email = document.getElementById('email').value;

      
      console.log('address '+ address.length);
      console.log('mobile number '+ document.getElementById('mobile_number').value.length);
      console.log('dob '+ document.getElementById('dob').value);

      if(document.getElementById('email').value == ''){
        document.getElementById('religion').focus();
        document.getElementById('e_email').innerHTML = "**Email is required";
        document.getElementById('e_email').style.color = 'red';
        flag = 1;
      }
      
        else if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
          document.getElementById('email').focus();
        document.getElementById('e_email').innerHTML = "**Enter a valid Email";
        document.getElementById('e_email').style.color = 'red';
        flag = 1;
        }
      
      else {
        document.getElementById('e_email').innerHTML="";
      }


      if(document.getElementById('pincode').value == ''){
        document.getElementById('pincode').focus();
        document.getElementById('e_pincode').innerHTML = "**pincode is required";
        document.getElementById('e_pincode').style.color = 'red';
        flag = 1;
      }

      else if(document.getElementById('pincode').value.length != 6 || !(/^[1-9][0-9]{5}$/.test(document.getElementById('pincode').value))){
        document.getElementById('pincode').focus();
        document.getElementById('e_pincode').innerHTML = "**Enter a valid pincode";
        document.getElementById('e_pincode').style.color = 'red';
        flag = 1;
      }
      else 
        document.getElementById('e_pincode').innerHTML = "";
 


      if(address == ''){

        console.log('inside address validation ');
        document.getElementById('address').focus();
        document.getElementById('e_address').innerHTML = "**address is required";
        document.getElementById('e_address').style.color = 'red';
        flag = 1;
      }
      else
        document.getElementById('e_address').innerHTML = "";

      if(document.getElementById('nationality').value == ''){
        document.getElementById('nationality').focus();
        document.getElementById('e_nationality').innerHTML = "**nationality is required";
        document.getElementById('e_nationality').style.color = 'red';
        flag = 1;
      }
      else
        document.getElementById('e_nationality').innerHTML = "";

      if(document.getElementById('caste').value == '' ){
        document.getElementById('caste').focus();
        document.getElementById('e_caste').innerHTML = "**caste is required";
        document.getElementById('e_caste').style.color = 'red';
        flag = 1;
      }
      else
        document.getElementById('e_caste').innerHTML = "";



      if(document.getElementById('other').style.visibility == 'visible' ){
        if( document.getElementById('other').value == '' ){
        console.log('inside other caste');
        document.getElementById('caste').focus();
        document.getElementById('e_caste').innerHTML = "**caste is required";
        document.getElementById('e_caste').style.color = 'red';
        flag = 1;
      }
      
      else
        document.getElementById('e_caste').innerHTML = "";
    }


      if(document.getElementById('religion').value == ''){
        document.getElementById('religion').focus();
        document.getElementById('e_religion').innerHTML = "**Enter religion";
        document.getElementById('e_religion').style.color = 'red';
        flag = 1;
      }
      else
        document.getElementById('e_religion').innerHTML = "";


      if(document.getElementById('age').value == ''){
        document.getElementById('age').focus();
        document.getElementById('e_age').innerHTML = "**Enter age";
        document.getElementById('e_age').style.color = 'red';
        flag = 1;
      }
      else if(document.getElementById('age').value < 15 || document.getElementById('age').value >= 60){
        document.getElementById('e_age').innerHTML = "**Not within proper age limit"; 
          document.getElementById('e_age').style.color = 'red';
        flag = 1;

      }else
        document.getElementById('e_age').innerHTML = "";

      if(document.getElementById('dob').value == ''){
        document.getElementById('dob').focus();
        document.getElementById('e_dob').innerHTML = "**Enter D.O.B";
        document.getElementById('e_dob').style.color = 'red';
        flag = 1;
      }
      
      else
        document.getElementById('e_dob').innerHTML = "";

      if(document.getElementById('sex').value == ''){
        document.getElementById('sex').focus();
        document.getElementById('e_sex').innerHTML = "**Choose Gender";
        document.getElementById('e_sex').style.color = 'red';
        flag = 1;
      }
      else
        document.getElementById('e_sex').innerHTML = "";

      if(document.getElementById('father_name').value == ''){
        document.getElementById('father_name').focus();
        document.getElementById('e_father').innerHTML = "**Enter Father's Name";
        document.getElementById('e_father').style.color = 'red';
        flag = 1;
		}else if( !(/^[A-Za-z. ]{3, 30}$/.test(document.getElementById('father_name').value))){
        document.getElementById('e_father').innerHTML = "**Father's Name cannot be a special character or a number";				
      }
      
      else
        document.getElementById('e_father').innerHTML = "";

      if(document.getElementById('name').value == ''){
        document.getElementById('name').focus();
        document.getElementById('e_name').innerHTML = "**Enter name";
        document.getElementById('e_name').style.color = 'red';
        flag = 1;
      }
      else
        document.getElementById('e_name').innerHTML = "";

      if(document.getElementById('poc').value == 'Choose...'){
        document.getElementById('poc').focus();
        document.getElementById('e_poc').innerHTML = "**Choose an Option";
        document.getElementById('e_poc').style.color = 'red';
      }
      
      else
        document.getElementById('e_poc').innerHTML = "";


      if(document.getElementById('mobile_number').value == ''){
        document.getElementById('mobile_number').focus();

        document.getElementById('e_mobile').innerHTML = "**Enter Mobile Number";
        document.getElementById('e_mobile').style.color = 'red';
        
        flag = 1;
      }

      else if(document.getElementById('mobile_number').value.length != 10 || !(/^[1-9][0-9]{9}$/.test(document.getElementById('mobile_number').value))){
          document.getElementById('mobile_number').focus();

          document.getElementById('e_mobile').innerHTML = "**Enter a valid Mobile Number";
          document.getElementById('e_mobile').style.color = 'red';
          
          flag = 1;
        }
      
      else
        document.getElementById('e_mobile').innerHTML = "";


      if(flag ==0){
       $('#mytabs a[href="#academic"]').tab('show');
       $('#centerContent').scrollTop(0);
      }

    });

    $('#changetabbutton2').click(function(e){
      scrollTo(0,0);

       var institution = document.getElementById('sslc_institution').value;
       var pass = document.getElementById('sslc_pass').value;
       var marks = document.getElementById('sslc_marks').value;
       var percentage = document.getElementById('sslc_percentage').value;
       var file = document.getElementById('sslc_certificate').value;

       var flag = 0;

       console.log('file '+ file +' '+ file.length);
       
       if( institution == ''){
        document.getElementById('sslc_institution').focus();

        document.getElementById('e_sslc_institution').innerHTML = "**Enter name of the Instituiton";
        document.getElementById('e_sslc_institution').style.color = 'red';
        
        flag = 1;
      }
      else {
        document.getElementById('e_sslc_institution').innerHTML = "";
        

      }

      if( pass == ''){
        document.getElementById('sslc_pass').focus();

        document.getElementById('e_sslc_pass').innerHTML = "**Enter Passing Year and Month";
        document.getElementById('e_sslc_pass').style.color = 'red';
        
        flag = 1;
      }
      else {
    
        document.getElementById('e_sslc_pass').innerHTML = "";

      }

      if( marks == ''){
        document.getElementById('sslc_marks').focus();

        document.getElementById('e_sslc_marks').innerHTML = "**Enter Marks";
        document.getElementById('e_sslc_marks').style.color = 'red';
        
        flag = 1;
      }
      else {
        document.getElementById('e_sslc_marks').innerHTML = "";

      }

      if( percentage == ''){
        document.getElementById('sslc_percentage').focus();

        document.getElementById('e_sslc_percentage').innerHTML = "**Enter Percentage";
        document.getElementById('e_sslc_percentage').style.color = 'red';
        
        flag = 1;
      }else {
        document.getElementById('e_sslc_percentage').innerHTML = "";


      }

      if( file == ''){
        document.getElementById('sslc_certificate').focus();

        document.getElementById('e_sslc_certificate').innerHTML = "**Choose a file";
        document.getElementById('e_sslc_certificate').style.color = 'red';
        
        flag = 1;
      }
      else {
        document.getElementById('e_sslc_certificate').innerHTML = "";

      }

       if (flag == 0){
        
          $('#mytabs a[href="#professional"]').tab('show');
		  $('#centerContent').scrollTop(0);
       }
    });

    $('#doa').click(function(){

        var today = new Date();
  var dd = today.getDate();
  var mm = today.getMonth()+1; //January is 0!
  var yyyy = today.getFullYear();
   if(dd<10){
          dd='0'+dd
      } 
      if(mm<10){
          mm='0'+mm
      } 

  today = yyyy+'-'+mm+'-'+dd;
  document.getElementById("doa").setAttribute("max", today);
      })
      
      $('#doa').change(function(){

        console.log(document.getElementById('doa').value);
      


        var year_entered = parseInt(document.getElementById('doa').value.substr(0,4));
        console.log('year'+' '+year_entered+typeof(year_entered));

        var month_entered = parseInt(document.getElementById('doa').value.substr(5,7));
        console.log('month'+ ' '+month_entered+typeof(month_entered));


        var date = new Date();
        
        if (month_entered > (date.getMonth() + 1)){

          document.getElementById('period_of_service').value = (date.getFullYear() - year_entered -1) + 'y ' + (12+( (date.getMonth() +1)-month_entered))+ 'm';

        } 
        else {
          document.getElementById('period_of_service').value = (date.getFullYear() - year_entered) + 'y ' + ((date.getMonth() +1)-month_entered)+ 'm';


        }
      });
    
    $('#changetabbutton3').click(function(e){
        e.preventDefault();


      var deputy = document.getElementById('deputy_scoiety').value;
      var designation = document.getElementById('designation').value;
      var doa = document.getElementById('doa').value;
      var period_of_service = document.getElementById('period_of_service').value;
      var office_address = document.getElementById('office_address').value;
      var phone_number_office = document.getElementById('phone_number_office').value;
      var pincode_office = document.getElementById('pincode_office').value;
      var flag = 0;

      if(deputy == ''){
        document.getElementById('deputy_scoiety').focus();

        document.getElementById('e_deputy_scoiety').innerHTML = "Enter Deputy Society";
        document.getElementById('e_deputy_scoiety').style.color = 'red';

        
        flag = 1;

      }
      else{
        document.getElementById('e_deputy_scoiety').innerHTML = "";
      }



      if(designation == ''){
        document.getElementById('designation').focus();

        document.getElementById('e_designation').innerHTML = "Enter designation";
        document.getElementById('e_designation').style.color = 'red';
        
        
        flag = 1;

      }
      else {
        
        document.getElementById('e_designation').innerHTML = "";
      }


      if(doa == ''){
        document.getElementById('doa').focus();

        document.getElementById('e_doa').innerHTML = "Enter Date of Joining";
        document.getElementById('e_doa').style.color = 'red';
        
        
        flag = 1;

      }
      else{
        document.getElementById('e_doa').innerHTML = "";
      }

      if(period_of_service == ''){
        document.getElementById('period_of_service').focus();

        document.getElementById('e_period_of_service').innerHTML = "Enter Period of service";
        document.getElementById('e_period_of_service').style.color = 'red';
        
        
        flag = 1;
      }
      else{
        document.getElementById('e_period_of_service').innerHTML = "";
      }

      if(office_address == ''){
        document.getElementById('office_address').focus();

        document.getElementById('e_office_address').innerHTML = "Enter Address";
        document.getElementById('e_office_address').style.color = 'red';
        
        
        flag = 1;

      }
      else {
        
        document.getElementById('e_office_address').innerHTML = "";
      }
      if(phone_number_office == ''){
        document.getElementById('phone_number_office').focus();

        document.getElementById('e_phone_number_office').innerHTML = "Enter PhoneNumber";
        document.getElementById('e_phone_number_office').style.color = 'red';
        
        
        flag = 1;

      }
      else{
        
        document.getElementById('e_phone_number_office').innerHTML = "";
      }
      if(pincode_office == ''){
        document.getElementById('pincode_office').focus();

        document.getElementById('e_pincode_office').innerHTML = "Enter Pincode";
        document.getElementById('e_pincode_office').style.color = 'red';
        
        
        flag = 1;

      }
      else if(pincode_office.length != 6 || !(/^[1-9][0-9]{5}$/.test(pincode_office))){
          document.getElementById('pincode_office').focus();
          document.getElementById('e_pincode_office').innerHTML = "Enter a valid pincode";
          document.getElementById('e_pincode_office').style.color = 'red';
          flag = 1;
        }
        else {

        
          document.getElementById('e_pincode_office').innerHTML = "";
      }

      
        if(flag == 0) {
  

          $('#mytabs a[href="#upload"]').tab('show');
        }
    });

    $('#changetabbutton4').click(function(e){
        e.preventDefault();
        var flag = 0;
        var photoUp = document.getElementById('photo').value;
        var nocUp = document.getElementById('noc_certificate').value;
        var addressUp = document.getElementById('address_proof').value;
        var certUp = document.getElementById('e_certificate').value;
        var signatureUp = document.getElementById('signature').value;
        
        if( photoUp == ''){
          document.getElementById('photo').focus();
          // change 5 e_photo
          document.getElementById('e_photo').innerHTML = '**Photo is required ';
          document.getElementById('e_photo').style.color = 'red';
          console.log('inside_photo');
          
          flag = 1;
        }
        else {
          document.getElementById('e_photo').innerHTML = "";
  
        }

        if( nocUp == ''){
          document.getElementById('noc_certificate').focus();
          // change 5 e_photo
          document.getElementById('e_noc').innerHTML = '**NOC is required ';
          document.getElementById('e_noc').style.color = 'red';
          console.log('inside_noc');
          
          flag = 1;
        }
        else {
          document.getElementById('e_noc').innerHTML = "";
  
        }

        if( addressUp == ''){
          document.getElementById('address_proof').focus();
          // change 5 e_photo
          document.getElementById('e_address12').innerHTML = '**Address Proof is required ';
          document.getElementById('e_address12').style.color = 'red';
          console.log('inside_address');
          flag = 1;
        }
        else {
          document.getElementById('e_address12').innerHTML = "";
  
        }
        
        if( certUp == ''){
          document.getElementById('e_certificate').focus();
          // change 5 e_photo
          document.getElementById('e_empolyee').innerHTML = '**Certificate is required ';
          document.getElementById('e_empolyee').style.color = 'red';
          console.log('inside_cert');
          flag = 1;
        }
        else {
          document.getElementById('e_empolyee').innerHTML = "";
  
        }

        if( signatureUp == ''){
          document.getElementById('signature').focus();
          // change 5 e_photo
          document.getElementById('e_sig').innerHTML = '**Signature is required ';
          document.getElementById('e_sig').style.color = 'red';
          console.log('inside_sign');
          
          flag = 1;
        }
        else {
          document.getElementById('e_sig').innerHTML = "";
  
        }



        if(flag == 0) {
        $('#mytabs a[href="#declaration"]').tab('show');
        }
    });
    
    

  $('#changetabbutton6').click(function(e){
      e.preventDefault();

      $('#mytabs a[href="#personal"]').tab('show');
  });

  $('#changetabbutton7').click(function(e){
    e.preventDefault();

    $('#mytabs a[href="#academic"]').tab('show');
});

$('#changetabbutton8').click(function(e){
  e.preventDefault();

  $('#mytabs a[href="#professional"]').tab('show');
});

$('#changetabbutton9').click(function(e){
  e.preventDefault();

  $('#mytabs a[href="#upload"]').tab('show');
});

});


function ValidateSize(file) {

	try{
    var FileSize = file.files[0].size / 1024 / 1024 / 1; // in MB
    //console.log(file.files[0].filePath);
    console.log('asd');
	}
	catch(e){
	}
    if (FileSize > 1) {
        alert('File size exceeds 2 MB');
        $(file).val(''); //for clearing with Jquery
       
       console.log('asd');
       return;
    } 
    
    else {

    }
      var filePath = file.value; 
      var allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif|\.pdf)$/i; 
        
      if (!allowedExtensions.exec(filePath)) { 
          alert('Invalid file type'); 
          file.value = ''; 
          return false; 
      }   
}
function ageCal(){
	  var dob = parseInt(document.getElementById('dob').value.substring(0,4));
	  var today = new Date();
	  document.getElementById('age').value = today.getFullYear() - dob;
	}

function Service(){

  var pos = parseInt(document.getElementById('doa').value.substring(0,4));

  
  var today = new Date();
 
  

  document.getElementById('period_of_service').value = today.getFullYear() - pos;

}
function changestatus(){
  var status = document.getElementById('caste');
 
  if(status.value != 'O'){
    document.getElementById("other").style.visibility="hidden";
  }else{
    document.getElementById("other").style.visibility="visible";
  }
}

function fileValidation() { 
	  var fileInput =  
	      document.getElementById('file'); 
	    
	  var filePath = fileInput.value; 

	  // Allowing file type 
	  var allowedExtensions =  
	/(\.pdf)$/i; 
	    
	  if (!allowedExtensions.exec(filePath)) { 
	    document.getElementById('e_pincode_office').innerHTML = "**Enter correct typr of file";
	      fileInput.value = ''; 
	      return false; 
	  }  
	}

function disableEnterKey(e){ 
	 
	  var key; 
	   
	      if(window.event){ 
	        
	      key = window.event.keyCode; 
	        
	      } else { 
	        
	      key = e.which;      
	      } 
	        
	      if(key == 13){ 
	        
	      return false; 
	        
	      } else { 
	        
	      return true; 
	      } 
	  } 

	  function year(){
	    var year = document.getElementById('year');
	    var today = new Date();
	    
	    for(i=today.getFullYear(); i <= 1970; i--){

	      year.add

	    }
	  }

$('#navbarNav a').click(function(){
	$('#navbarNav a').removeClass('activeClass');
	$(this).addClass("activeClass");
});