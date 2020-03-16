node('master'){

	stage('PULLING CHANGES'){
		checkout([$class: 'GitSCM',
		branches: [[name: '*/master']],
		doGenerateSubmoduleConfigurations: false,
		extensions: [], submoduleCfg: [],
		userRemoteConfigs: [[credentialsId: 'f874b751-b909-44bd-a0a6-cf4edb49fd36', url: 'https://github.com/Kesholabs/kesholabs_Dashboard.git']]])
	}

    stage('BUILDING THE APP') {
        sh '''
		echo "RUNNING BUILDING"
		sudo mvn package
		sudo ln -s /var/lib/jenkins/workspace/kesholabs_Dashboard/target/kesholabsDash-0.0.1-SNAPSHOT.jar /etc/init.d/kesholabDash
		'''
    }

    stage('TESTING THE APP') {
		sh '''
		echo "RUNNING TEST"
		'''
    }

    stage('DEPLOY THE APPLICATION') {
        sh '''
		pwd
		echo "DEPLOYING APPLICATION"
		sudo /etc/init.d/kesholabDash restart
		'''
    }
 }


