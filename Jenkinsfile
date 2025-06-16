pipeline {
    agent any

    environment {
        BASE_URL = "https://reqres.in"
    }

    stages {
        stage('Checkout') {
            steps {
                sh '''
                    echo "Current directory: $(pwd)"
                    echo "Listing current directory:"
                    ls -la
                    echo "Cleaning workspace..."
                    # Remove all files and directories except . and ..
                    rm -rf .[^.]* *
                    echo "Listing after cleanup:"
                    ls -la
                    echo "Cloning repository..."
                    git clone https://github.com/abhi9avx/CoreRestAssured.git .
                    git checkout main
                '''
            }
        }

        stage('Build & Test') {
            steps {
                sh '''
                    echo "Using system Maven"
                    /opt/homebrew/Cellar/maven/3.9.10/libexec/bin/mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
                '''
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
