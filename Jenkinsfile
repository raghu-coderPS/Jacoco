node {

 	
	  
stage('SCM Checkout'){
     git 'https://github.com/raghu-coderPS/Jacoco.git'
   }
   stage('Compile-Package'){
      // Get maven home path
      //def mvnHome =  tool name: 'maven-3', type: 'maven'   
      bat "mvn package"
   }
   
   stage('SonarQube Analysis') {
       // def mvnHome =  tool name: 'maven-3', type: 'maven'
        withSonarQubeEnv('SonarQube') { 
          bat "mvn sonar:sonar"
        }
    }
    

        

        
      
	  
        

    
	 
     
   

  

}
