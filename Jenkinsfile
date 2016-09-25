node {
    def name = java.net.URLDecoder.decode(env.JOB_NAME, "UTF-8");
    try{

        // Mark the code checkout 'stage'....
        stage ('Checkout') {
                slackSend color: 'good', message: "Downloading ${name}"
                checkout scm
        }
        stage ('Build') {
                def gradleHome = tool 'Gradle 2.12'
                slackSend color: 'good', message: "Building ${name}"
                def tasks = [:]

                tasks["Analyse"] = {
                     sonarScanner properties: "sonar.projectKey=arescommons:${name}" +
                                              "sonar.projectName=AresCommons" +
                                              "sonar.projectVersion=0.7"
                 }

                tasks["Compile"] ={
                    sh "${gradleHome}/bin/gradle clean build"
                    junit '**/build/test-results/TEST-*.xml'
                }

                parallel tasks
        }
        stage ('Publish') {
                slackSend color: 'good', message: "Publishing ${name}"
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'Artifactory', passwordVariable: 'arespass', usernameVariable: 'aresuser']]) {
                    sh "${gradleHome}/bin/gradle publish"
                }
                slackSend color: 'good', message: "Published ${name}"
        }
    }catch(err){
        echo "Caught: ${err}"
        currentBuild.result = 'FAILURE'
        slackSend color: 'danger', message: "Error while building ${name}"
    }finally {     	
        step($class: 'InfluxDbPublisher')     	
        stage ('clean') {
             step([$class: 'WsCleanup'])
        }
    }
}
