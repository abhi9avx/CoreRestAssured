pipeline {
    agent any

    environment {
        BASE_URL = "https://reqres.in"
    }

    stages {
        stage('Checkout') {
            steps {
                sh '''
                    rm -rf .git
                    rm -rf *
                    git clone https://github.com/abhi9avx/CoreRestAssured.git .
                    git checkout main
                '''
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished'
        }
        success {
            echo 'Build succeeded 🎉'
        }
        failure {
            echo 'Build failed ❌'
        }
    }
}
