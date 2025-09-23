pipeline {
    agent any   // run on any available Jenkins agent

    options {
        skipDefaultCheckout(true)  // optional: skip automatic checkout
        timestamps()               // show timestamps in logs
    }

    environment {
        JAVA_HOME = tool name: 'jdk17', type: 'jdk'   // use your JDK installation name
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
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
                // Run Gradle build
                sh './gradlew clean build --info --stacktrace'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
            post {
                always {
                    junit '**/build/test-results/test/*.xml' // publish test results
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
                branch 'main'   // deploy only when building main branch
            }
            steps {
                echo 'Deploying application...'
                // Example: copy to server, run Docker, etc.
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
