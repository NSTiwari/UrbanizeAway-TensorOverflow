      // Your web app's Firebase configuration
      // var firebaseConfig = {
      //   apiKey: "AIzaSyD6rdr3LaXyMTcmP_M2cmqQhwO2JOZEuyc",
      //   authDomain: "test-dae29.firebaseapp.com",
      //   databaseURL: "https://test-dae29.firebaseio.com",
      //   projectId: "test-dae29",
      //   storageBucket: "test-dae29.appspot.com",
      //   messagingSenderId: "195245932266",
      //   appId: "1:195245932266:web:e90f2d7214384be9436193"
      // };
      // // Initialize Firebase
      // firebase.initializeApp(firebaseConfig);
       
    var firebaseConfig = {
    apiKey: "AIzaSyDE-TKjW1RHjJevP9sNE-euBoWJKfD1RfA",
    authDomain: "wasteai.firebaseapp.com",
    databaseURL: "https://wasteai.firebaseio.com",
    projectId: "wasteai",
    storageBucket: "wasteai.appspot.com",
    messagingSenderId: "396828542394",
    appId: "1:396828542394:web:52231347274587a3f94983",
    measurementId: "G-V0EF2SPYJ9"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  
      var firebaseRef = firebase.database().ref().child('Admins').child('9988776655');
      
      firebaseRef.once("value").then(function(snapshot){
        var login1Info=snapshot.val();
        document.getElementById("login1").addEventListener("click",function(){
          var name = document.getElementById("name");
          var password = document.getElementById("pass");
          if (name.value==login1Info.name && password.value==login1Info.password){
            location.replace("index.html");
          }else if(name.value!=login1Info.name){
            name.style.borderColor="red";
          }else if(password.value!=login1Info.password){
            password.style.borderColor="red";
          }
        });
      });

      document.getElementById('pass').addEventListener("input",function(){
        document.getElementById("pass").style.borderColor="#ccc";
      });
      document.getElementById('name').addEventListener("input",function(){
        document.getElementById("name").style.borderColor="#ccc";
      });
