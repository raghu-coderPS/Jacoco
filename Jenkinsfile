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

        

	stage('SonarQube'){

         steps{
		 withSonarQubeEnv('SonarQube2') {

           bat label: '', script: '''mvn sonar:sonar \
		 -Dsonar.host.url=http://35.175.103.228:9000 \
 		-Dsonar.login=5623afa01d36ee21531aade59a92bcf60e4c212d'''

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
    
} 
     
   
