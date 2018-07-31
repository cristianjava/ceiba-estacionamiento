pipeline{
	agent {
		label 'Slave_Induccion'
	}
	options{
		buildDiscarder(logRotator(numToKeepStr: '3'))
		disableConcurrentBuilds()
	}
	tools{
		jdk 'JDK8_Centos'
		gradle 'Gradle4.5_Centos'
	}
	stages{
		stage('Checkout'){
			steps{
			echo "------------>Checkout<------------"
			checkout([$class: 'GitSCM', branches: [[name: '*/master']],doGenerateSubmoduleConfigurations: false, extensions: [], gitTool:'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId:'GitHub_cristianjava', url:'https://github.com/cristianjava/ceiba-estacionamiento.git']]])
			}
		}
		stage('Compile'){
			steps{
				 sh 'gradle --b ./build.gradle compileJava'
			}
		}
		stage('Unit Tests'){
			steps{
				echo"-------------> These are Unit Test !! <------------"
				sh 'gradle --b ./build.gradle test'
			}
		}
		stage('Integration Test'){
			steps{
				echo"--------> These are Integration Test !! <----------"
			}
		}
		stage('Static Code Analysis'){
			steps{
				echo"-------> This is Static Code Analysis !! <---------"
				withSonarQubeEnv('Sonar') {
                   sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
               }
			}
		}
		stage('Build'){
			steps{
				echo"--------> This is Build !! <----------"
				sh 'gradle --b ./build.gradle build -x test'
			}
		}
	}
	post {
		always {
			echo 'This will always run !! '
		}
		success {
			echo 'This will run only if all is successful !! :)'
		}
		failure {
			echo 'This will run only if failed !! :('
			mail (to: 'cristian.castano@ceiba.com.co', subject: "Failed Pipeline:${currentBuild.fullDisplayName}", body: "Something is wrong with ${env.BUILD_URL}")
		}
		unstable {
			echo 'This will run only if the run was marked as unstable !! :S'
		}
		changed {
			echo 'This will run only if the state of the Pipeline has changed'
			echo 'For example, if the Pipeline was previously failing but is now successful'
		}
	}
}