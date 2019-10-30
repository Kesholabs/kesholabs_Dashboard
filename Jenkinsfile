pipeline {
    agent {
       	label 'pb-webapp-slave'
    }
    stages {

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
