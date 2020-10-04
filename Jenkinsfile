def myVariable = "foo"
pipeline {

  agent any

  stages {	
	  

	stage('Maven Compile'){

		steps{
			script{
			 myVariable = "Maven Compile"

			echo 'Project compile stage'

			bat label: 'Compilation running', script: '''mvn compile'''

	       	}
		}

	}

	

	stage('Unit Test') {

	   steps {
		   script{
		     myVariable = "Unit Test"

			echo 'Project Testing stage'

			bat label: 'Test running', script: '''mvn test'''

		   }
		   

       }

   	}

	

	

        

    stage('Jacoco Coverage Report') {

        steps{

            jacoco()

		}

	}

        

	stage('SonarQube'){

         steps{
		 script{
		 myVariable = "Sonar"
		 withSonarQubeEnv('SonarQube') {

            bat label: '', script: '''mvn sonar:sonar'''

          }
		 }
	 }

	}
	   stage("Quality Gate") {
            steps {
		    script{
		    myVariable = "Sonar Quality gate"
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
	    }
        }
	   stage('Jmeter'){
         steps{
		 script{
		 myVariable = "Jmater"
	    // cd 	 C:\Program Files\apache-jmeter-5.3\bin
            bat label: 'jmeter',script:'C:\\apache-jmeter-5.3\\bin\\jmeter -n -Jjmeter.save.saveservice.output_format=xml -t C:\\Users\\kanram\\Desktop\\POD2\\employee-report.jmx -l C:\\Users\\kanram\\Desktop\\POD2\\results\\Test-emp.jtl'
          perfReport filterRegex: '', sourceDataFiles: 'C:\\Users\\kanram\\Desktop\\POD2\\results\\Test.jtl'
	 }
	}
	   }

	

	stage('Maven Package'){

		steps{
			script{
		   myVariable = "Maven Package"

			echo 'Project packaging stage'

			bat label: 'Project packaging', script: '''mvn package'''

		}
		}

	} 	
	  stage('Ok') {
            steps {
		    script{
		    myVariable = "ok"
                echo "Ok"
            }
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
             body: "Something is wrong with ${env.BUILD_URL} and ${myVariable}"
    }
    }
	 
     
   

}
