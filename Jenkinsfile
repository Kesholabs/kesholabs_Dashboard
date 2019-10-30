pipeline {
    agent {
       	label 'pb-webapp-slave'
    }

    tools {
        maven 'Maven 3.3.9'
        jdk 'jdk8'
    }

    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage('SCM') {
            steps {
                echo 'pulling from git'
                git credentialsId: 'github', url: 'https://github.com/Kesholabs/kesholabs_Dashboard.git'
            }
        }

       stage('Build') {
          steps {
              echo 'Building..'
              sh 'su mvn clean && mvn build'
          }
          post {
               success {
                   junit 'target/surefire-reports/**/*.xml'
               }
           }
      }

      stage('Test') {
          steps {
              echo 'Testing..'
              sh 'su mvn test'
          }
      }

      stage('Deploy') {
          steps {
              echo 'Deploying....'
              sh 'su nohup java -jar mpesadashboard-0.0.1-SNAPSHOT.jar &'
          }
      }

    }
}
