pipeline {
    agent any
    stages {
    
    	
		stage('Maven Compile'){
			steps{
	
				echo 'Project compile stage'
	
				sh 'mvn compile'
	
		       	}
	
		}

		stage('Unit Test') {
	
		   steps {
	
				echo 'Project Testing stage'
	
				sh 'mvn test'
	
		       
	
	       		}
	
	   	}

	  
		stage('Jacoco Coverage Report') {
	
	        steps{
	        	
	        	sh 'mvn verify'
	            jacoco()
	
			}
	
		}
	    
	    stage('SonarQube'){

	         steps{
			 withSonarQubeEnv('SonarQube2') {
	
	            sh 'mvn sonar:sonar'
          			}	
	   		 }

		}
	    
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
          
                    waitForQualityGate abortPipeline:true
                }
            }
        }

        
        stage('Maven Package'){

			steps{
	
				echo 'Project packaging stage'
	
				sh 'mvn package'
	
			}

		} 	
    }
}
