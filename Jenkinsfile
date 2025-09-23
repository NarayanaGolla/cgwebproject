pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
        timestamps()
    }

    tools {
        jdk 'JAVA_HOME'   // must match Global Tool Configuration name
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'feature/java8features',
                    url: 'https://github.com/NarayanaGolla/cgwebproject.git'
            }
        }

        stage('Build') {
            steps {
                bat  './gradlew clean build --info --stacktrace'
            }
        }

        stage('Test') {
            steps {
                bat  './gradlew test'
            }
            post {
                always {
                    junit '**/build/test-results/test/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
            }
        }

        stage('Deploy') {
            when {
                branch 'main'   // change to 'feature/java8features' if you want to deploy that branch
            }
            steps {
                echo 'Deploying application...'
                // Example: scp to server, docker build/push, helm upgrade, etc.
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully ✅'
        }
        failure {
            echo 'Pipeline failed ❌'
        }
    }
}