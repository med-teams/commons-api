// Jenkinsfile - commons-api
// Multibranch Pipeline. PR -> build+test+sonar (validation).
// main / release/* -> build+test puis "mvn install" pour publier le jar
// dans le .m2 local de l'agent (voir note plus bas si vous passez a Nexus).

pipeline {
    agent { label 'build-agent' }   // meme agent que msname-service pour partager le cache .m2

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn -B clean verify'
            }
        }

        stage('Sonar (PR uniquement)') {
            when { changeRequest() }
            steps {
                withSonarQubeEnv('SonarQube-Server') {
                    sh 'mvn -B sonar:sonar -Dsonar.projectKey=commons-api'
                }
            }
        }

        stage('Quality Gate (PR uniquement)') {
            when { changeRequest() }
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Publier (main / release uniquement)') {
            when {
                anyOf {
                    branch 'main'
                    branch pattern: 'release/.*', comparator: 'REGEXP'
                }
            }
            steps {
                // Publie dans le .m2 local de l'agent. Pour du multi-agent,
                // remplacer par: mvn -B -s settings.xml clean deploy -DskipTests
                // avec un <distributionManagement> pointant vers Nexus/Artifactory.
                sh 'mvn -B clean install -DskipTests'
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
        }
    }
}
