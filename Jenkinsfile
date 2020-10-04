pipeline {

  agent any

  stages {	
	  

	stage('Maven Compile'){

		steps{
			def myVariable = "foo"

			echo 'Project compile stage'

			bat label: 'Compilation running', script: '''mvn compile'''

	       	}

	}

	

	stage('Unit Test') {

	   steps {
		   def myVariable = "foo"

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
		 def myVariable = "foo"
		 withSonarQubeEnv('SonarQube') {

            bat label: '', script: '''mvn sonar:sonar'''

          }
	 }

	}
	   stage("Quality Gate") {
            steps {
		    def myVariable = "foo"
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
        }
	   stage('Jmeter'){
         steps{
		 def myVariable = "foo"
	    // cd 	 C:\Program Files\apache-jmeter-5.3\bin
            bat label: 'jmeter',script:'C:\\apache-jmeter-5.3\\bin\\jmeter -n -Jjmeter.save.saveservice.output_format=xml -t C:\\Users\\kanram\\Desktop\\POD2\\employee-report.jmx -l C:\\Users\\kanram\\Desktop\\POD2\\results\\Test-emp.jtl'
          perfReport filterRegex: '', sourceDataFiles: 'C:\\Users\\kanram\\Desktop\\POD2\\results\\Test.jtl'
	 }
	}

	

	stage('Maven Package'){

		steps{
			def myVariable = "foo"

			echo 'Project packaging stage'

			bat label: 'Project packaging', script: '''mvn package'''

		}

	} 	
	  stage('Ok') {
            steps {
		    def myVariable = "foo"
                echo "Ok"
            }
        }
    }
    post {
        success {
            emailext body: 'A Test EMail ${myVariable}', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Test'
        }
	     failure {
        mail to: 'mithunputhusseri@gmail.com',
             subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
             body: "Something is wrong with ${env.BUILD_URL}"
    }
    }
	 
     
   

}
