node {

 	
	  
stage('SCM Checkout'){
     git 'https://github.com/raghu-coderPS/Jacoco.git'
   }
   stage('Compile-Package'){
      // Get maven home path
      def mvnHome =  tool name: 'maven-3', type: 'maven'   
      bat "${mvnHome}/bin/mvn package"
   }
   
   stage('SonarQube Analysis') {
        def mvnHome =  tool name: 'maven-3', type: 'maven'
        withSonarQubeEnv('SonarQube') { 
          bat "${mvnHome}/bin/mvn sonar:sonar"
        }
    }
    

        

          stage('build & SonarQube analysis') 
	  {
	  
            steps {
              withSonarQubeEnv('SonarQube') {
                bat label: '', script: '''mvn sonar:sonar \
		 -Dsonar.host.url=http://localhost:9000 \
 		-Dsonar.login=a05a193de838bc0d4b86d07800da08f5e2053343'''
              
            }
          }
	  }
         stage("Quality Gate Statuc Check"){
          timeout(time: 1, unit: 'HOURS') {
              def qg = waitForQualityGate()
		  if (qg.status != 'OK') {
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
              }
          }
      }    
      
	  
        

    
	 
     
   

  

}
