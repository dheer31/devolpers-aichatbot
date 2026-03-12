pipeline {

```
agent any

parameters {
    choice(
        name: 'ACTION',
        choices: ['Deploy', 'Remove'],
        description: 'Deploy or Remove AI Chatbot application'
    )
}

environment {
    APP_NAME = "ai-chatbot"
    COMPOSE_FILE = "docker-compose.yml"
}

stages {

    stage('Checkout Source Code') {
        when {
            expression { params.ACTION == 'Deploy' }
        }
        steps {
            echo "📥 Cloning project from repository..."
            checkout scm
        }
    }

    stage('Build Spring Boot Application') {
        when {
            expression { params.ACTION == 'Deploy' }
        }
        steps {
            echo "⚙️ Building Spring Boot application..."
            sh '''
               ./mvnw clean package -DskipTests
            '''
        }
    }

    stage('Docker Compose Deploy') {
        when {
            expression { params.ACTION == 'Deploy' }
        }
        steps {
            echo "🚀 Building and starting Spring Boot + Ollama containers..."
            sh '''
               docker compose -f ${COMPOSE_FILE} up -d --build
            '''
        }
    }

    stage('Verify Containers') {
        when {
            expression { params.ACTION == 'Deploy' }
        }
        steps {
            echo "🔍 Checking running containers..."
            sh '''
               docker ps
            '''
        }
    }

    stage('Docker Compose Remove & Cleanup') {
        when {
            expression { params.ACTION == 'Remove' }
        }
        steps {
            echo "🧹 Stopping and removing containers..."
            sh '''
               docker compose -f ${COMPOSE_FILE} down --rmi all --volumes --remove-orphans
               docker system prune -af
            '''
        }
    }
}

post {
    success {
        echo "✅ AI Chatbot deployment completed successfully 🚀"
        echo "👨‍💻 Designed and Developed by dhee31"
    }
    failure {
        echo "❌ Deployment failed. Check Jenkins logs!"
    }
}
```

}
