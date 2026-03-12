pipeline {
    agent any

    parameters {
        choice(
            name: 'ACTION',
            choices: ['Deploy', 'Remove'],
            description: 'Deploy or Remove AI Chatbot application'
        )
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Spring Boot') {
            when {
                expression { params.ACTION == 'Deploy' }
            }
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Compose Deploy') {
            when {
                expression { params.ACTION == 'Deploy' }
            }
            steps {
                sh 'docker compose up -d --build'
            }
        }

        stage('Docker Compose Remove') {
            when {
                expression { params.ACTION == 'Remove' }
            }
            steps {
                sh 'docker compose down'
            }
        }
    }

    post {
        success {
            echo "Deployment successful"
        }
        failure {
            echo "Pipeline failed"
        }
    }
}
