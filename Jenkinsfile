pipeline {

  agent any

  stages {	
	  

	stage('Maven Compile'){

		steps{

			echo 'Project compile stage'

			bat label: 'Compilation running', script: '''mvn compile'''

	       	}

	}

	

	stage('Unit Test') {

	   steps {

			echo 'Project Testing stage'

			bat label: 'Test running', script: '''mvn test'''

	       
		   

       }

   	}   

    stage('Jacoco Coverage Report') {

        steps{

            jacoco()

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
         stage("Quality Gate") {
            steps {
		    timeout(time: 1, unit: 'HOURS'){
                    waitForQualityGate abortPipeline: true
		    }
            }
        }
      
	  
        
	  
	

	stage('Maven Package'){

		steps{

			echo 'Project packaging stage'

			bat label: 'Project packaging', script: '''mvn package'''

		}

	} 
  }
	  
    post {
        success {
            emailext body: 'A Test EMail', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Test'
        }
	     failure {
        mail to: 'mithunputhusseri@gmail.com',
             subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
             body: "Something is wrong with ${env.BUILD_URL}"
    }
    }
	 
     
   

  

}
