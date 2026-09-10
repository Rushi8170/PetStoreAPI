pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                echo "Compiling project..."
                bat 'mvn clean compile -DskipTests'
            }
        }

        stage('Execute Tests') {
            steps {
                echo 'Running all test cases from testng.xml...'
			    bat 'mvn clean test -DsuiteXmlFiles=testng.xml'
            }
        }
    }

    post {
		
		always {
            echo "Publishing reports..."

            // Publish Extent Report
            publishHTML(target: [
                allowMissing: true,
           		alwaysLinkToLastBuild: true,
            	keepAll: true,
            	reportDir: 'reports',
            	reportFiles: '*.html',
            	reportName: 'Extent Report'
            ])
        }
		
        success {
            echo 'HTTPRequests test cases executed successfully.'
        }

        failure {
            echo 'Test execution failed — check the Jenkins console log.'
        }
    }
}