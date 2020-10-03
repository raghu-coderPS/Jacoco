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
		 withSonarQubeEnv('SonarQube') {

            bat label: '', script: '''mvn sonar:sonar\
	     -Dsonar.host.url=http://3.238.72.11:9000 \
	    -Dsonar.login=cdfee438103a46648e147d285ab7d182'''

          }
	 }

	}
	   
	   stage('Jmeter'){
         steps{
	    // cd 	 C:\Program Files\apache-jmeter-5.3\bin
            bat label: 'jmeter',script:'C:\\apache-jmeter-5.3\\bin\\jmeter -n -Jjmeter.save.saveservice.output_format=xml -t C:\\Users\\kanram\\Desktop\\POD2\\employee-report.jmx -l C:\\Users\\kanram\\Desktop\\POD2\\results\\Test-emp.jtl'
          perfReport filterRegex: '', sourceDataFiles: 'C:\\Users\\kanram\\Desktop\\POD2\\results\\Test.jtl'
	 }
	}

	

	stage('Maven Package'){

		steps{

			echo 'Project packaging stage'

			bat label: 'Project packaging', script: '''mvn package'''

		}

	} 	
	 
    
   
  }
	environment {
        EMAIL_TO = 'raghuramkanagarla123@gmail.com'
    }
post {
        failure {
		emailext body: 'Failed Stage name:$FAILED_STAGE', 
                    to: "${EMAIL_TO}", 
                    subject: 'Build failed in Jenkins: $PROJECT_NAME - #$BUILD_NUMBER'
        }
        unstable {
            emailext body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, maxLines=100, escapeHtml=false}', 
                    to: "${EMAIL_TO}", 
                    subject: 'Unstable build in Jenkins: $PROJECT_NAME - #$BUILD_NUMBER'
        }
        changed {
            emailext body: 'Check console output at $BUILD_URL to view the results.', 
                    to: "${EMAIL_TO}", 
                    subject: 'Jenkins build is back to normal: $PROJECT_NAME - #$BUILD_NUMBER'
        }
    }

	 
     
   

  

}
