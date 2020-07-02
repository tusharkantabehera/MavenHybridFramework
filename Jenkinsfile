pipeline
{
	agent any
	stages
	{
		stage('SonarQube Analysis')
		{
			steps
			{
				echo "SonarQube Test is Started"
				bat 'mvn sonar:sonar -Dsonar.projectName=MavenHybridFramework -Dsonar.host.url=http://localhost:9000 -Dlicense.skip=true'
				echo "SonarQube Test is Successful"
			}
		}
		stage('Build')
		{
			steps
			{
				echo "Build is Started"
				bat "mvn clean package -PRegression -DskipTests=true"
				echo "Build is Successful"
			}
		}
		stage('Deploy')
		{
			steps
			{
				echo "Deployment is Started"
				echo "Deployment is Successful"
			}
		}
		stage('Chrome Tests')
		{
			parallel
			{
				stage('Smoke Suite')
				{
					steps
					{
						echo "Smoke Test Execution is Started"
						bat "mvn test -PSmoke"
						echo "Smoke Test Execution is Successful"
					}
				}
				stage('Regression Suite')
				{
					steps
					{
						echo "Regression Test Execution is Started"
						bat "mvn test -PRegression -DskipTests=true"
						echo "Regression Test Execution is Successful"
					}
				}
			}
		}
		stage('Release')
		{
			steps
			{
				echo "Release is Started"
				echo "Release is Successful"
			}
		}
		stage('Publish Reports')
		{
			parallel
			{
				stage('Extent Report')
				{
					steps
					{
						publishHTML([
						allowMissing: false, 
            					alwaysLinkToLastBuild: false, 
            					keepAll: false, 
						reportDir: 'build', 
            					reportFiles: 'CRMExtentReport*.html', 
            					reportName: 'Extent HTML Report', 
            					reportTitles: ''])
					}
				}
				stage('Allure Report')
				{
					steps
					{
						echo "Allure Report is yet to be implemented"
					}
				}
			}
		}
		stage('Notifications')
		{
			parallel
			{
				stage('Slack')
				{
					steps
					{
						slackSend channel: 'test-automation',
						color: 'good',
						message: "*${currentBuild.currentResult}:* Job Name: ${env.JOB_NAME} || Build Number: ${env.BUILD_NUMBER}\n More information at: ${env.BUILD_URL}"
					}
				}
				stage('Gmail')
				{
					steps
					{
						emailext body: "*${currentBuild.currentResult}:* Job Name: ${env.JOB_NAME} || Build Number: ${env.BUILD_NUMBER}\n More information at: ${env.BUILD_URL}",
						subject: 'Test Automation Pipeline Build Status',
						to: 'Pavankrishnan1993@gmail.com'
					}
				}
			}
		}
	}
	post
	{
		failure 
		{
            		echo 'This Job is Failed - Notifications have been sent to Slack and Gmail..!!'
			
			slackSend channel: 'test-automation',
			color: 'good',
			message: "*${currentBuild.currentResult}:* Job Name: ${env.JOB_NAME} || Build Number: ${env.BUILD_NUMBER}\n More information at: ${env.BUILD_URL}"
        		
			emailext body: "*${currentBuild.currentResult}:* Job Name: ${env.JOB_NAME} || Build Number: ${env.BUILD_NUMBER}\n More information at: ${env.BUILD_URL}",
			subject: 'Test Automation Pipeline Build Status',
			to: 'Pavankrishnan1993@gmail.com'
		}
        	unstable 
		{
           		echo 'This Job is Unstable - Notifications have been sent to Slack and Gmail..!!'
			
			slackSend channel: 'test-automation',
			color: 'good',
			message: "*${currentBuild.currentResult}:* Job Name: ${env.JOB_NAME} || Build Number: ${env.BUILD_NUMBER}\n More information at: ${env.BUILD_URL}"
        		
			emailext body: "*${currentBuild.currentResult}:* Job Name: ${env.JOB_NAME} || Build Number: ${env.BUILD_NUMBER}\n More information at: ${env.BUILD_URL}",
			subject: 'Test Automation Pipeline Build Status',
			to: 'Pavankrishnan1993@gmail.com'
		}
	}
}
