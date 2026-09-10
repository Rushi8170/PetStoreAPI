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
                echo "Running all test cases from HTTPRequests.java..."
                bat 'mvn test -Dtest=HTTPRequests'
            }
        }
    }

    post {
        success {
            echo 'HTTPRequests test cases executed successfully.'
        }

        failure {
            echo 'Test execution failed — check the Jenkins console log.'
        }
    }
}