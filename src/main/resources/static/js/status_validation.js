$(function(){

    $('#changetabbutton1').click(function(e){
    var flag = 0;

if(document.getElementById('mobile_number_verify').value == ''){
    document.getElementById('mobile_number_verify').focus();

    document.getElementById('e_mobile_verify').innerHTML = "**Enter Mobile Number";
    // document.getElementById('e_mobile').style.color = 'red';
    
    flag = 1;
  }

  else if(document.getElementById('mobile_number_verify').value.length != 10 || !(/^[1-9][0-9]{9}$/.test(document.getElementById('mobile_number_verify').value))){
      document.getElementById('mobile_number_verify').focus();

      document.getElementById('e_mobile_verify').innerHTML = "**Enter a valid Mobile Number";
      // document.getElementById('e_mobile').style.color = 'red';
      
      flag = 1;
    }
  
  else
    document.getElementById('e_mobile_verify').innerHTML = "";

    if(document.getElementById('password').value == ''){
    document.getElementById('password').focus();

    document.getElementById('e_password').innerHTML = "**Enter Password Accordingly to the instruction/Cannot be blank";
    // document.getElementById('e_mobile').style.color = 'red';
    
    flag = 1;
  }

  else if(document.getElementById('password').value.length != 8){
    document.getElementById('password').focus();

    document.getElementById('e_password').innerHTML = "**Enter password, field cannot be blank";
    // document.getElementById('e_mobile').style.color = 'red';
    
    flag = 1;
  }

  else
    document.getElementById('e_password').innerHTML = "";

    if(flag == 0)
    {
        console.log(document.getElementById('stat'));
    }
});

});


